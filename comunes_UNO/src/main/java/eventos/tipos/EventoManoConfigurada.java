/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos.tipos;

import eventos.IEvento;
import dtos.EstadoConfiguracionDTO;

/**
 *
 * @author Adrián
 */
public class EventoManoConfigurada implements IEvento {

    public static final String TIPO = "TAMANIO_DE_MANO_CONFIGURADA";

    public EventoManoConfigurada(EstadoConfiguracionDTO configuracion) {
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }
    
    @Override 
    public int cambio(){
        return int cambio =1;
    }

}
