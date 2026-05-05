/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eventos;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public interface IEventBus {
    /**
     * Registra a un listener para que escuche un tipo específico de evento.
     */
    void suscribir(String tipoEvento, IEventoListener listener);

    /**
     * Quita a un listener de la lista para que deje de escuchar.
     */
    void desuscribir(String tipoEvento, IEventoListener listener);

    /**
     * Recibe un evento y se lo envía a todos los listeners que estén suscritos a ese tipo.
     */
    void publicar(IEvento evento);
}
