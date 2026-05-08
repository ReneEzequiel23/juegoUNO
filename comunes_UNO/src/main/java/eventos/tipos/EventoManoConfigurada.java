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
public class EventoManoConfigurada implements IEvento {

    public static final String TIPO = "TAMANIO_DE_MANO_CONFIGURADA";

    public EventoManoConfigurada() {
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }

}
