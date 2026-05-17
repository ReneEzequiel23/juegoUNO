/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.ComandoJugadorDTO;
import dtos.EstadoConfiguracionDTO;
import dtos.TipoAccion;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import eventos.tipos.EventoListaJugadores;
import eventos.tipos.EventoManoConfigurada;
import eventos.tipos.EventoTiempoConfigurado;
import vista.ConfigurarPartida;

/**
 *
 * @author Adrián
 */
public class ControlEventosConfiguracion implements IEventoListener {

    private final IEventBus eventBus;
    private final String nombreJugador;
    private ConfigurarPartida vista;
    private int numeroCartas;
    private int numeroJugadores;
    private int temporizador;

    public ControlEventosConfiguracion(IEventBus eventBus, String nombreJugador) {
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;
        
        this.eventBus.suscribir(eventos.tipos.EventoListaJugadores.TIPO, this);
        this.eventBus.suscribir(eventos.tipos.EventoManoConfigurada.TIPO, this);
        this.eventBus.suscribir(eventos.tipos.EventoTiempoConfigurado.TIPO, this);
    }

    public void setVista(ConfigurarPartida vista) {
        this.vista = vista;
    }

    public void cambiarNumeroJugadores(int numero) {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.CCNFIGURAR_PARTIDA, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));

    }

    public void cambiarTiempoLimite(int numero) {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.CCNFIGURAR_PARTIDA, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }

    public void configurarMano(int numero) {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.CCNFIGURAR_PARTIDA, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));

    }

    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoListaJugadores) {
            EstadoConfiguracionDTO configDTO = ((eventos.tipos.EventoListaJugadores) evento).getConfiguracion();

            // Le pasamos los datos limpios a la vista en el hilo correcto
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
//                    vista.actualizarInterfazConDTO(mesaDTO);
                });
            }
        } else if (evento instanceof eventos.tipos.EventoManoConfigurada) {
            EstadoConfiguracionDTO configDTO = ((eventos.tipos.EventoListaJugadores) evento).getConfiguracion();

            // Le pasamos los datos limpios a la vista en el hilo correcto
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
//                    vista.actualizarInterfazConDTO(mesaDTO);
                });
            }
        } else if (evento instanceof eventos.tipos.EventoTiempoConfigurado) {
            EstadoConfiguracionDTO configDTO = ((eventos.tipos.EventoListaJugadores) evento).getConfiguracion();

            // Le pasamos los datos limpios a la vista en el hilo correcto
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
//                    vista.actualizarInterfazConDTO(mesaDTO);
                });
            }
        }
    }

    private void destruir() {
        this.eventBus.desuscribir(eventos.tipos.EventoListaJugadores.TIPO, this);
        this.eventBus.desuscribir(eventos.tipos.EventoTiempoConfigurado.TIPO, this);
        this.eventBus.desuscribir(eventos.tipos.EventoManoConfigurada.TIPO, this);

    }

}
