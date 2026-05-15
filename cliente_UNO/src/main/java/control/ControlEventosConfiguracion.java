/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.EstadoConfiguracionDTO;
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

    public ControlEventosConfiguracion(IEventBus eventBus, String nombreJugador, int numeroCartas, int numeroJugadores, int temporizador) {
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;
        this.numeroCartas = numeroCartas;
        this.numeroJugadores = numeroJugadores;
        this.temporizador = temporizador;
    }

    public void cambiarNumeroJugadores(int numero) {
        EstadoConfiguracionDTO configuracion = new EstadoConfiguracionDTO(nombreJugador, numeroCartas, temporizador, numero);
        eventBus.publicar(new eventos.tipos.EventoListaJugadores(configuracion));

    }

    public void cambiarTiempoLimite(int numero) {
        EstadoConfiguracionDTO configuracion = new EstadoConfiguracionDTO(nombreJugador, numeroCartas, numero, numeroJugadores);
        eventBus.publicar(new eventos.tipos.EventoTiempoConfigurado(configuracion));
    }

    public void configurarMano(int numero) {
        EstadoConfiguracionDTO configuracion = new EstadoConfiguracionDTO(nombreJugador, numero, numeroCartas, numeroJugadores);
        eventBus.publicar(new eventos.tipos.EventoManoConfigurada(configuracion));

    }

    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoMesa) {
            EstadoConfiguracionDTO configDTO = ((eventos.tipos.EventoListaJugadores) evento).getTipoEvento();

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
