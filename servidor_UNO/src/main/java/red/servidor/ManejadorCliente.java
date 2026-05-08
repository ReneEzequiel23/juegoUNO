package red.servidor;

import control.PartidaControlador;
import dtos.ComandoJugadorDTO;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import eventos.tipos.EventoEstadoMesa;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import modelo.Partida;

public class ManejadorCliente implements Runnable, IEventoListener {

    private Socket socket;
    private IEventBus eventBus;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean conectado;

    private PartidaControlador controladorCentral;
    private Partida partidaCentral;

    // ¡NUEVO! Guardamos quién es el dueño de este socket
    private String nombreJugador;

    // ¡CORREGIDO! Ahora pedimos el nombre del jugador en el constructor
    // 1. Quitamos "String nombreJugador" de los parámetros
    public ManejadorCliente(Socket socket, IEventBus eventBus, PartidaControlador controladorCentral, Partida partidaCentral) {
        this.socket = socket;
        this.eventBus = eventBus;
        this.controladorCentral = controladorCentral;
        this.partidaCentral = partidaCentral;
        this.conectado = true;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(socket.getInputStream());

            // --- ¡NUEVO! EL HANDSHAKE ---
            // El servidor pausa aquí milisegundos hasta que el cliente diga su nombre
            this.nombreJugador = (String) in.readObject();
            System.out.println("[Servidor] ¡El jugador " + this.nombreJugador + " ha entrado a la partida!");
            // ----------------------------
            // ¡NUEVO! Registramos a este jugador en la partida real (como Jugador de dominio)
            modelo.Jugador nuevoJugador = new modelo.Jugador(this.nombreJugador);
            this.partidaCentral.getJugadores().add(nuevoJugador);

            // ¡NUEVO! Nos suscribimos para escuchar tanto el Lobby como la Mesa
            this.eventBus.suscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
            this.eventBus.suscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);

            // ¡CORREGIDO! Usamos ENTRAR_LOBBY para que no se marquen con palomita automáticamente
            this.controladorCentral.procesarComandoRed(new dtos.ComandoJugadorDTO(
                this.nombreJugador, dtos.TipoAccion.ENTRAR_LOBBY, null, null, null
            ));
        } catch (IOException | ClassNotFoundException e) { // <-- IMPORTANTE AÑADIR ESTO AL CATCH
            System.err.println("Error conectando con el cliente: " + e.getMessage());
        }
    }

    // (Constructor Zombie eliminado)
    @Override
    public void run() {
        while (conectado) {
            try {
                Object mensaje = in.readObject();

                if (mensaje instanceof ComandoJugadorDTO) {
                    ComandoJugadorDTO comando = (ComandoJugadorDTO) mensaje;
                    System.out.println("Comando recibido del jugador: " + comando.getIdJugador() + " Acción: " + comando.getTipoAccion());

                    controladorCentral.procesarComandoRed(comando);
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Jugador " + nombreJugador + " desconectado.");
                desconectar();
            }
        }
    }

    @Override
    public void onEvent(IEvento evento) {
        try {
            // Si el evento es del LOBBY, lo mandamos directo al cliente
            if (evento.getTipoEvento().equals(eventos.tipos.EventoEstadoLobby.TIPO)) {
                out.writeObject(evento);
                out.reset();
                out.flush();
            } 
            // Si el evento es de la MESA, lo traducimos con la Niebla de Guerra
            else if (evento.getTipoEvento().equals(eventos.tipos.EventoEstadoMesa.TIPO)) {
                dtos.EstadoMesaDTO miEstado = red.servidor.TraductorDTO.generarEstadoParaJugador(partidaCentral, this.nombreJugador);
                out.writeObject(new eventos.tipos.EventoEstadoMesa(miEstado));
                out.reset();
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("No se pudo enviar el evento al cliente " + nombreJugador);
        }
    }

    private void desconectar() {
        conectado = false;
        // ¡CORREGIDO! Limpiamos la basura de la suscripción correcta
        eventBus.desuscribir("ACTUALIZAR_MESAS", this);
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
