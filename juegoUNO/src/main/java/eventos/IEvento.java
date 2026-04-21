/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventos;

import java.io.Serializable;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public interface IEvento extends Serializable{
    /**
     * Devuelve el nombre o identificador del evento.
     * Ejemplo: "ESTADO_MESA_ACTUALIZADO", "ERROR_JUGADA"
     */
    String getTipoEvento();
}
