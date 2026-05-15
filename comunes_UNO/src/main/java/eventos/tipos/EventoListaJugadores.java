/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos.tipos;

import dtos.EstadoConfiguracionDTO;
import eventos.IEvento;

/**
 *
 * @author Adrián
 */
public class EventoListaJugadores implements IEvento {

    public static final String TIPO = "LISTA_DE_JUGADORES_ACTUALIZADA";
    private final EstadoConfiguracionDTO configuracion;

    public EventoListaJugadores(EstadoConfiguracionDTO configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }

    public int getNumeroJugadores() {
        return configuracion.getNumJugadores();
    }

    public EstadoConfiguracionDTO getConfiguracion() {
        return configuracion;
    }
}
