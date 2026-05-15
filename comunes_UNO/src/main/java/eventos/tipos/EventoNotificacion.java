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

    // 1. Agregamos la constante estática que pide NetBeans
    public static final String TIPO = "NOTIFICACION";

    // 2. Renombramos la variable para que sea más clara
    private final String mensaje;

    public EventoNotificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    // 3. Agregamos el método "getter" que pedía el Controlador
    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String getTipoEvento() {
        // Ahora devuelve la constante TIPO correctamente
        return TIPO;
    }
}
