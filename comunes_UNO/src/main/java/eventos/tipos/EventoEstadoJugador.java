/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos.tipos;

import dtos.JugadorDTO;
import eventos.IEvento;

/**
 *
 * @author edgar
 */
public class EventoEstadoJugador implements IEvento{
    public static final String TIPO = "ESTADO_JUGADOR_ACTUALIZADO";
    private final JugadorDTO estadoDTO;

    public EventoEstadoJugador(JugadorDTO estadoDTO) {
        this.estadoDTO = estadoDTO;
    }

    public JugadorDTO getEstadoDTO() {
        return estadoDTO;
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }
}
