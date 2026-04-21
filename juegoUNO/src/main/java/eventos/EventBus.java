/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class EventBus implements IEventBus {

    // Nuestro archivero: Llave = Nombre del Evento, Valor = Lista de escuchas
    private final Map<String, List<IEventoListener>> mapaListeners;

    public EventBus() {
        this.mapaListeners = new HashMap<>();
    }

    @Override
    public void suscribir(String tipoEvento, IEventoListener listener) {
        // 1. Si es la primera vez que alguien se suscribe a este evento, creamos el cajón (la lista vacía)
        mapaListeners.putIfAbsent(tipoEvento, new ArrayList<>());
        
        // 2. Metemos al listener en la lista correspondiente
        mapaListeners.get(tipoEvento).add(listener);
        System.out.println("[EventBus] Nuevo suscriptor registrado para: " + tipoEvento);
    }

    @Override
    public void desuscribir(String tipoEvento, IEventoListener listener) {
        // Buscamos si existe el cajón, y si sí, sacamos al listener
        if (mapaListeners.containsKey(tipoEvento)) {
            mapaListeners.get(tipoEvento).remove(listener);
            System.out.println("[EventBus] Suscriptor eliminado de: " + tipoEvento);
        }
    }

    @Override
    public void publicar(IEvento evento) {
        String tipo = evento.getTipoEvento();
        
        System.out.println("[EventBus] PUBLICANDO evento: " + tipo);
        
        // Si hay alguien escuchando este tipo de evento...
        if (mapaListeners.containsKey(tipo)) {
            // ...hacemos una copia rápida de la lista para evitar errores si alguien se desuscribe a la mitad...
            List<IEventoListener> interesados = new ArrayList<>(mapaListeners.get(tipo));
            
            // ...y les avisamos a todos, uno por uno.
            for (IEventoListener listener : interesados) {
                listener.onEvent(evento);
            }
        } else {
            System.out.println("[EventBus] Nadie está escuchando el evento: " + tipo);
        }
    }
}
