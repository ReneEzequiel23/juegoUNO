/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos.tipos;

import eventos.IEvento;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class EventoNotificacion implements IEvento {
    
    private final String tipoAviso;

    public EventoNotificacion(String tipoAviso) {
        this.tipoAviso = tipoAviso;
    }

    @Override
    public String getTipoEvento() {
        // En este caso, el mismo nombre del aviso (ej. "ACTUALIZAR_MESAS") es el tipo de evento
        return tipoAviso; 
    }
}
