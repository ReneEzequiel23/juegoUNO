package red.servidor;

import control.DespachadorComandos;
import control.PartidaControlador;
import dtos.ComandoJugadorDTO;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import modelo.Partida;
import red.ConexionSocket;

public class ManejadorCliente implements Runnable, IEventoListener {

    private final ConexionSocket conexion;
    private final IEventBus eventBus;
    private final DespachadorComandos despachador;
    private final Partida partidaCentral;
    
    private String nombreJugador;
    private boolean conectado = true;

    public ManejadorCliente(java.net.Socket socket, IEventBus eventBus, DespachadorComandos despachador, Partida partidaCentral) {
        this.eventBus = eventBus;
        this.despachador = despachador;
        this.partidaCentral = partidaCentral;
        this.conexion = new ConexionSocket();

        try {
            // Vinculamos el socket aceptado usando la lógica de Comunes
            this.conexion.vincularSocketExistente(socket);
        } catch (java.io.IOException e) {
            System.err.println("Error al vincular conexión: " + e.getMessage());
            this.conectado = false;
        }
    }

    @Override
    public void run() {
        while (conectado) {
            try {
                // Leemos el DTO de forma limpia
                Object mensaje = conexion.recibirObjeto();

                if (mensaje instanceof ComandoJugadorDTO) {
                    ComandoJugadorDTO comando = (ComandoJugadorDTO) mensaje;
                    
                    // Registro inicial del jugador
                    if (this.nombreJugador == null && comando.getTipoAccion() == dtos.TipoAccion.ENTRAR_LOBBY) {
                        this.nombreJugador = comando.getIdJugador();
                        System.out.println("[Servidor] Jugador registrado: " + this.nombreJugador);
                        
                        this.eventBus.suscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
                        this.eventBus.suscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
                    }

                    despachador.procesar(comando);
                }
            } catch (Exception e) {
                System.out.println("Finalizando conexión con " + (nombreJugador != null ? nombreJugador : "desconocido"));
                desconectar();
            }
        }
    }

    @Override
    public void onEvent(IEvento evento) {
        try {
            if (evento.getTipoEvento().equals(eventos.tipos.EventoEstadoLobby.TIPO)) {
                conexion.enviarObjeto(evento);
            } 
            else if (evento.getTipoEvento().equals(eventos.tipos.EventoEstadoMesa.TIPO)) {
                dtos.EstadoMesaDTO miEstado = red.servidor.TraductorDTO.generarEstadoParaJugador(partidaCentral, this.nombreJugador);
                conexion.enviarObjeto(new eventos.tipos.EventoEstadoMesa(miEstado));
            }
        } catch (java.io.IOException e) {
            System.err.println("Error de red al notificar a " + nombreJugador);
        }
    }

    private void desconectar() {
        conectado = false;
        eventBus.desuscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        eventBus.desuscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
        conexion.desconectar();
    }
}
