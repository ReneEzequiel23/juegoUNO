/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author renee, edgar
 */
public class Mazo {
    private final List<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<>();
        inicializarMazo();
        //barajamos inmediatamente para que esté listo para la partida
        barajar();
    }

    private void inicializarMazo() {
        Color[] coloresBase = {Color.ROJO, Color.AZUL, Color.AMARILLO, Color.VERDE};

        for (Color color : coloresBase) {
            // Cartas Numéricas: Un '0' por color
            cartas.add(new Numerica(generarId(), color, 0));

            // Dos cartas de cada número del 1 al 9 por color
            for (int i = 1; i <= 9; i++) {
                cartas.add(new Numerica(generarId(), color, i));
                cartas.add(new Numerica(generarId(), color, i));
            }
            // Dos de cada uno por color
            for (int i = 0; i < 2; i++) {
                cartas.add(new Comodin(generarId(), color, Accion.TOMA2));
                cartas.add(new Comodin(generarId(), color, Accion.REVERSA));
                cartas.add(new Comodin(generarId(), color, Accion.BLOQUEO));
            }
        }
        // 4 de Cambio de Color y 4 de Toma 4
        for (int i = 0; i < 4; i++) {
            cartas.add(new Comodin(generarId(), Color.NEGRO, Accion.CAMBIOCOLOR));
            cartas.add(new Comodin(generarId(), Color.NEGRO, Accion.TOMA4));
        }
    }

    private String generarId() {
        // Genera un ID único como: "f47ac10b-58cc-4372-a567-0e02b2c3d479"
        return UUID.randomUUID().toString();
    }

    public void barajar() {
        Collections.shuffle(this.cartas);
    }

    /**
     * Extrae la carta de hasta arriba del mazo.
     */
    public Carta robarCarta() {
        if (cartas.isEmpty()) {
            /**Más adelante, en la PartidaControlador, si esto devuelve null 
            sabremos que hay que agarrar la PilaDescartes, barajarla y volver a llenar el mazo.**/
            return null; 
        }
        // En una ArrayList, remover el último elemento es más eficiente y simula la "cima" del mazo.
        return cartas.remove(cartas.size() - 1);
    }

    public int contarCartas() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        /**Devolvemos una copia de la lista para que nadie desde afuera 
        pueda modificar la lista original usando mazo.getCartas().clear() por error**/
        return new ArrayList<>(cartas);
    }
    
    /**
     * Recibe las cartas de la pila de descartes, las añade al mazo y las baraja.
     */
    public void reciclarCartas(List<Carta> cartasRecicladas) {
        if (cartasRecicladas != null && !cartasRecicladas.isEmpty()) {
            this.cartas.addAll(cartasRecicladas);
            barajar();
            System.out.println("¡El mazo ha sido reciclado y barajado!");
        }
    }
}
