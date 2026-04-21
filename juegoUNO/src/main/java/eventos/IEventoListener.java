/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventos;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public interface IEventoListener {
    /**
     * Este método se ejecuta automáticamente cuando el EventBus detecta 
     * un evento al que esta clase está suscrita.
     */
    void onEvent(IEvento evento);
}
