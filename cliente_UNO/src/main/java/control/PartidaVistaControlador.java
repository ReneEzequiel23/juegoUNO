/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import dtos.ComandoJugadorDTO;
import dtos.EstadoMesaDTO;
import dtos.TipoAccion;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import red.cliente.IClienteRed;
import vista.PantallaPartida;
/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class PartidaVistaControlador implements IEventoListener {
    private final IClienteRed clienteRed;
    private final IEventBus eventBus;
    private final String nombreJugador;
    
    // Necesitamos una referencia a la pantalla para decirle cuándo actualizarse
    private PantallaPartida vista; 

    public PartidaVistaControlador(IClienteRed clienteRed, IEventBus eventBus, String nombreJugador) {
        this.clienteRed = clienteRed;
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;
        
        // El controlador se suscribe al bus de eventos, ¡ya no la pantalla!
        this.eventBus.suscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }

    public void setVista(PantallaPartida vista) {
        this.vista = vista;
    }

    // =======================================================
    // ENVÍO DE COMANDOS (HACIA EL SERVIDOR)
    // =======================================================

    public void jugarCarta(String idCarta, String colorElegido) {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(
                nombreJugador, TipoAccion.JUGAR_CARTA, idCarta, colorElegido, null
        );
        clienteRed.enviarComando(comando);
    }

    public void robarCarta() {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(
                nombreJugador, TipoAccion.ROBAR, null, null, null
        );
        clienteRed.enviarComando(comando);
    }

    public void gritarUno() {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(
                nombreJugador, TipoAccion.GRITAR_UNO, null, null, null
        );
        clienteRed.enviarComando(comando);
    }

    public void denunciar() {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(
                nombreJugador, TipoAccion.DENUNCIAR, null, null, null
        );
        clienteRed.enviarComando(comando);
    }

    // =======================================================
    // RECEPCIÓN DE EVENTOS (DESDE EL SERVIDOR)
    // =======================================================
    
    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoMesa) {
            EstadoMesaDTO mesaDTO = ((eventos.tipos.EventoEstadoMesa) evento).getEstadoDTO();
            
            // Le pasamos los datos limpios a la vista en el hilo correcto
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    vista.actualizarInterfazConDTO(mesaDTO);
                });
            }
        }
    }
    
    public void destruir() {
        // Método para limpiar la suscripción cuando se cierre la partida
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }
}
