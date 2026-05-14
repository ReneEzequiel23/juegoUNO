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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
        
        this.eventBus.suscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        
    }
    
    public void buscarLobby(String codigo){
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.BUSCAR_LOBBY, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }
    
    public void solicitarUnirse(){
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.ENTRAR_LOBBY, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }
    
    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoLobby) {
            EstadoLobbyDTO estado = ((eventos.tipos.EventoEstadoLobby) evento).getEstadoLobbyDTO();
            if (vista.txtBuscar.getText() == null) {
                JOptionPane.showConfirmDialog(vista, "Tienes la Busqueda vacia", "Error!", JOptionPane.OK_OPTION);
            } else {
                // hacer una busqueda por su codigo y devuelve una entidad partida o el codigo y numero de jugadores 
                // Y actualiza el BusquedaUI
                vista.btnBuscar.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // para solicitar unirse a la partida y unirse a la lobby, 
                        // Quien sabe si funcione
                        if (vista != null && vista.txtBuscar.getText() != null) {
                            javax.swing.SwingUtilities.invokeLater(() -> {
                                vista.actualizarBusqueda(estado);
                            });
                        }

                    }
                });
            }            
        }
    }
    

    private void destruir() {
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
//        this.eventBus.desuscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }
}
