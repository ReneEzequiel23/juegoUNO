/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos.tipos;

import eventos.IEvento;

/**
 *
 * @author Adrián
 */
public class EventoTiempoConfigurado implements IEvento{

    public static final String TIPO = "TIEMPO_CONFIGURADO";

    public EventoTiempoConfigurado() {
    }

    
    
    @Override
    public String getTipoEvento() {
        return TIPO;
    }

}
