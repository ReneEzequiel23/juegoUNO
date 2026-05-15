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
import eventos.tipos.EventoComando;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.PantallaBusqueda;

/**
 *
 * @author edgar
 */
public class BusquedaVistaControlador implements IEventoListener {

    private final IEventBus eventBus;
    private final String nombreJugador;
    private PantallaBusqueda vista;

    private boolean esperandoUnirse = false;

    public BusquedaVistaControlador(IEventBus eventBus, String nombreJugador) {
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;

        this.eventBus.suscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);

    }

    public void buscarLobby(String codigo) {
        // ¡CAMBIO AQUI! Reemplazamos el primer 'null' por 'codigo'
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.BUSCAR_LOBBY, codigo, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }

    public void solicitarUnirse(String codigo) {
        this.esperandoUnirse = true; // Levantamos la bandera
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.SOLICITAR_UNIRSE, codigo, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }

    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoLobby) {
            EstadoLobbyDTO estado = ((eventos.tipos.EventoEstadoLobby) evento).getEstadoLobbyDTO();
            
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    if (esperandoUnirse) {
                        // ¡El Host dijo que sí! Hacemos la transición
                        transicionALobby(estado);
                    } else {
                        // Aún no tocamos la puerta, solo pintamos la sala verde
                        vista.actualizarBusqueda(estado);
                    }
                });
            }
        }
    }
    
    private void transicionALobby(EstadoLobbyDTO estado) {
        // Le pasamos la estafeta al controlador del Lobby
        control.LobbyVistaControlador lobbyCtrl = new control.LobbyVistaControlador(this.eventBus, this.nombreJugador);
        vista.PantallaLobby lobbyUI = new vista.PantallaLobby(lobbyCtrl, this.nombreJugador);
        
        lobbyUI.actualizarInterfazLobby(estado);
        lobbyUI.setVisible(true);
        
        vista.dispose(); // Matamos la pantalla de búsqueda
        destruir();
    }

    public void setVista(PantallaBusqueda vista) {
        this.vista = vista;
    }

    public IEventBus getEventBus() {
        return this.eventBus;
    }

    private void destruir() {
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
    }
}
