package red.servidor;

import control.DespachadorComandos;
import control.PartidaControlador;
import dtos.ComandoJugadorDTO;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import java.io.IOException;
import modelo.Partida;
import red.ConexionSocket;

public class ManejadorCliente implements Runnable, IEventoListener {

    private final ConexionSocket conexion;
    private final IEventBus eventBus;
    private final DespachadorComandos despachador;
    private final Partida partidaCentral;

    private String nombreJugador;
    private boolean conectado = true;

    public ManejadorCliente(java.net.Socket socket, IEventBus eventBus, control.DespachadorComandos despachador, Partida partidaCentral) {
        this.eventBus = eventBus;
        this.despachador = despachador;
        this.partidaCentral = partidaCentral;
        this.conexion = new ConexionSocket();

        try {
            this.conexion.vincularSocketExistente(socket);
            
            // Suscripciones a los eventos
            this.eventBus.suscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
            this.eventBus.suscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
            this.eventBus.suscribir(eventos.tipos.EventoNotificacion.TIPO, this);
            
            // ¡ELIMINAMOS la lectura obligatoria que bloqueaba el constructor!
            
        } catch (java.io.IOException e) {
            System.err.println("Error al vincular conexión: " + e.getMessage());
            this.conectado = false;
        }
    }

    @Override
    public void run() {
        while (conectado) {
            try {
                Object mensaje = conexion.recibirObjeto();

                if (mensaje instanceof ComandoJugadorDTO) {
                    ComandoJugadorDTO comando = (ComandoJugadorDTO) mensaje;
                    
                    // 1. Guardamos el nombre sin importar cuál sea el primer comando
                    if (this.nombreJugador == null) {
                        this.nombreJugador = comando.getIdJugador();
                        System.out.println("[Servidor] Enlace establecido con: " + this.nombreJugador);
                        
                        // 2. ¿Es el primero en conectarse a la partida? Entonces es el Host
                        if (comando.getTipoAccion() == dtos.TipoAccion.ENTRAR_LOBBY && partidaCentral.getJugadores().isEmpty()) {
                            partidaCentral.getJugadores().add(new modelo.Jugador(this.nombreJugador));
                            System.out.println("[Servidor] " + this.nombreJugador + " ha sido asignado como Host.");
                        }
                    }

                    // 3. Pasamos el comando al despachador
                    despachador.procesar(comando);
                }
            } catch (Exception e) {
                System.out.println("Finalizando conexión con " + (nombreJugador != null ? nombreJugador : "desconocido"));
                e.printStackTrace();
                desconectar();
                
            }
        }
    }

    @Override
    public void onEvent(IEvento evento) {
        try {
            if (evento.getTipoEvento().equals(eventos.tipos.EventoEstadoLobby.TIPO) ||
                evento.getTipoEvento().equals(eventos.tipos.EventoNotificacion.TIPO)) {
                conexion.enviarObjeto(evento);
            } 
            else if (evento.getTipoEvento().equals(eventos.tipos.EventoEstadoMesa.TIPO)) {
                dtos.EstadoMesaDTO miEstado = red.servidor.TraductorDTO.generarEstadoParaJugador(partidaCentral, this.nombreJugador);
                conexion.enviarObjeto(new eventos.tipos.EventoEstadoMesa(miEstado));
            }
        } catch (Exception e) {
            System.err.println("Error de red al notificar a " + nombreJugador);
        }
    }

    private void desconectar() {
        conectado = false;
        eventBus.desuscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        eventBus.desuscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
        eventBus.desuscribir(eventos.tipos.EventoNotificacion.TIPO, this);
        conexion.desconectar();
    }
}
