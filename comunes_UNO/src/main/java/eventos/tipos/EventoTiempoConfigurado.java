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
public class EventoTiempoConfigurado implements IEvento {

    public static final String TIPO = "TIEMPO_CONFIGURADO";
    private final EstadoConfiguracionDTO configuracion;

    public EventoTiempoConfigurado(EstadoConfiguracionDTO configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }

    public int getTiempo() {
        return configuracion.getTiempoTotal();
    }

    public EstadoConfiguracionDTO getConfiguracion() {
        return configuracion;
    }
}
