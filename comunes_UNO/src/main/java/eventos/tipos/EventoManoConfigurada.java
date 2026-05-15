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
    private final EstadoConfiguracionDTO configuracion;

    public EventoManoConfigurada(EstadoConfiguracionDTO configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }

    public int getManoCartas() {
        return configuracion.getCartasMano();
    }
    
    public EstadoConfiguracionDTO getConfiguracion(){
        return configuracion;
    }

}
