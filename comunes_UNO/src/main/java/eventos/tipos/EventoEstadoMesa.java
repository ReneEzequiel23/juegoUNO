/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos.tipos;

import dtos.EstadoMesaDTO;
import eventos.IEvento;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class EventoEstadoMesa implements IEvento {
    
    public static final String TIPO = "ESTADO_MESA_ACTUALIZADO";
    private final EstadoMesaDTO estadoDTO;

    public EventoEstadoMesa(EstadoMesaDTO estadoDTO) {
        this.estadoDTO = estadoDTO;
    }

    public EstadoMesaDTO getEstadoDTO() {
        return estadoDTO;
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }
}