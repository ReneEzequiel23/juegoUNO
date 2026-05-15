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
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.BUSCAR_LOBBY, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }

    public void solicitarUnirse(String codigo) {
        this.esperandoUnirse = true; // Levantamos la bandera
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.ENTRAR_LOBBY, codigo, null, null);
        eventBus.publicar(new EventoComando(comando));
    }

    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoLobby) {
            EstadoLobbyDTO estado = ((eventos.tipos.EventoEstadoLobby) evento).getEstadoLobbyDTO();

            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    if (esperandoUnirse) {
                        //estábamos esperando unirnos
                        vista.transicionALobby(estado);
                        destruir();
                    } else {
                        // Si solo estábamos buscando, actualizamos el panel
                        vista.actualizarBusqueda(estado);
                    }
                });
            }
        }
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
