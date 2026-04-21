/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renee
 */
public class PilaDescartes {
   
    private final List<Carta> cartas;

    public PilaDescartes() {
        //pila vacía
        this.cartas = new ArrayList<>();
    }

    /**
     * Obtiene la carta que está en la cima de la pila (la que todos ven en la mesa)
     */
    public Carta obtenerCartaSuperior() {
        if (!cartas.isEmpty()) {
            return cartas.getLast();
        }
        return null;
    }
    
    /**
     * Agrega una carta jugada a la cima de la pila de descarte.
     * En una Lista, el final(add) representa la cima de la pila.
     */
    public void agregarCarta(Carta carta) {
        if (carta != null) {
            cartas.add(carta);
        }
    }

    /**
     * Cuando el Mazo se queda sin cartas, 
     * necesitamos vaciar la pila para reciclarla.
     * @return Lista de cartas listas para ser barajadas y devueltas al Mazo.
     */
    public List<Carta> vaciarParaReciclar() {
        if (cartas.size() <= 1) {
            return new ArrayList<>(); // No hay suficientes cartas para reciclar
        }

        //Guarda la carta que está hasta arriba
        Carta cartaSuperior = obtenerCartaSuperior();
        
        //Extrae todas las demás cartas
        List<Carta> cartasParaMazo = new ArrayList<>(cartas.subList(0, cartas.size() - 1));
        
        //Vacia la pila y le regresa la carta superior
        cartas.clear();
        cartas.add(cartaSuperior);
        
        return cartasParaMazo;
    }

    public List<Carta> getCartas() {
        // DDD: Devolvemos una copia para proteger el estado interno
        return new ArrayList<>(cartas);
    }
}
