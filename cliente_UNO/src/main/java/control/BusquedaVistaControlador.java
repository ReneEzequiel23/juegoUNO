/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.ComandoJugadorDTO;
import dtos.EstadoLobbyDTO;
import dtos.EstadoMesaDTO;
import dtos.TipoAccion;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import vista.PantallaBusqueda;

/**
 *
 * @author edgar
 */
public class BusquedaVistaControlador implements IEventoListener{
    private final IEventBus eventBus;
    private final String nombreJugador;
    private PantallaBusqueda vista;

    public BusquedaVistaControlador(IEventBus eventBus, String nombreJugador) {
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;
    }
    
    public void buscarLobby(String codigo){
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.BUSCAR_LOBBY, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }
    
    public void solicitarUnirse(){
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.SOLICITAR_UNIRSE, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }
    
    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoMesa) {
            EstadoMesaDTO mesaDTO = ((eventos.tipos.EventoEstadoMesa) evento).getEstadoDTO();

            // Le pasamos los datos limpios a la vista en el hilo correcto
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
//                    vista.actualizarInterfazConDTO(mesaDTO);
                });
            }
        }
    }
    private void destruir() {
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }
}
