/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.ComandoJugadorDTO;
import dtos.EstadoMesaDTO;
import dtos.OponenteDTO;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import vista.ClienteUI;

/**
 *
 * @author edgar
 */
public class ClienteVistaControlador implements IEventoListener{
    private final IEventBus eventBus;
    private final String nombreJugador;
    private vista.ClienteUI vista;

    public ClienteVistaControlador(IEventBus eventBus, String nombreJugador) {
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;
        
        this.eventBus.suscribir(eventos.tipos.EventoEstadoJugador.TIPO, this);
    }

    public void setVista(ClienteUI vista) {
        this.vista = vista;
    }

    
    
    /**
     * Estas no las Implemente por que se van a entrar a el servidor 
     * @param cliente
     * @param idJugadorLocal
     */
    public void crearJugador(String nombreJugador, int avatar){
        // enviarComando
        ComandoJugadorDTO comando = new ComandoJugadorDTO(
                nombreJugador, dtos.TipoAccion.AGREGAR_JUGADOR, null, null, null
        );
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }
    
    /**
     * Estas no las Implemente por que se van a entrar a el servidor 
     * @param cliente
     * @param idJugadorLocal
     */
    public void editarJugador(String nombreJugador, int avatar){
        ComandoJugadorDTO comando = new ComandoJugadorDTO(
                nombreJugador, dtos.TipoAccion.EDITAR_JUGADOR, null, null, null
        );
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
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoJugador.TIPO, this);
//        this.eventBus.desuscribir(eventos.tipos.TIPO, this);
    }
}
