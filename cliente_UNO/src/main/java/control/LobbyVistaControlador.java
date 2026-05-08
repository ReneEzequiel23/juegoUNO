/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import dtos.ComandoJugadorDTO;
import dtos.EstadoLobbyDTO;
import dtos.TipoAccion;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import red.cliente.IClienteRed;
/**
 *
 * @author ReneEzequiel23
 */
public class LobbyVistaControlador implements IEventoListener{
    private final IClienteRed clienteRed;
    private final IEventBus eventBus;
    private final String nombreJugador;
    
    private vista.PantallaLobby vista; 
    private boolean partidaIniciada = false;

    public LobbyVistaControlador(IClienteRed clienteRed, IEventBus eventBus, String nombreJugador) {
        this.clienteRed = clienteRed;
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;
        
        // ¡El controlador se suscribe al nacer!
        this.eventBus.suscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        this.eventBus.suscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }

    public void setVista(vista.PantallaLobby vista) {
        this.vista = vista;
    }

    // --- MÉTODOS DE SALIDA (LOS QUE YA TENÍAS) ---
    public void solicitarInicioPartida() {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.SOLICITAR_INICIO, null, null, null);
        clienteRed.enviarComando(comando);
    }

    public void cambiarEstadoListo(boolean estaListo) {
        TipoAccion accion = estaListo ? TipoAccion.MARCAR_LISTO : TipoAccion.DESMARCAR_LISTO;
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, accion, null, null, null);
        clienteRed.enviarComando(comando);
    }

    // --- MÉTODOS DE ENTRADA (MUDADOS DESDE LA VISTA) ---
    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoLobby) {
            EstadoLobbyDTO estado = ((eventos.tipos.EventoEstadoLobby) evento).getEstadoLobbyDTO();
            
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    vista.actualizarInterfazLobby(estado);
                });
            }
        } 
        else if (evento instanceof eventos.tipos.EventoEstadoMesa) {
            if (partidaIniciada) return; // El cerrojo
            partidaIniciada = true;

            dtos.EstadoMesaDTO mesaInicial = ((eventos.tipos.EventoEstadoMesa) evento).getEstadoDTO();

            // 1. Preparamos el controlador de la siguiente pantalla
            PartidaVistaControlador partidaCtrl = new PartidaVistaControlador(this.clienteRed, this.eventBus, this.nombreJugador);

            // 2. Le decimos a la vista que haga el salto
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    vista.abrirPartidaYCerrar(partidaCtrl, mesaInicial);
                });
            }

            // 3. Nos desuscribimos para morir en paz
            destruir();
        }
    }

    private void destruir() {
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }
    
    public void entrarAlLobby() {
        System.out.println("[Controlador] Intentando enviar comando ENTRAR_LOBBY a la red...");
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                nombreJugador, 
                dtos.TipoAccion.ENTRAR_LOBBY, 
                null, null, null
        );
        clienteRed.enviarComando(comando);
    }
}
