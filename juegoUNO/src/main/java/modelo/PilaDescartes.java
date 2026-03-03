/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

/**
 *
 * @author renee
 */
public class PilaDescartes {
<<<<<<< codigo
<<<<<<< Updated upstream
=======
=======
>>>>>>> main
   
    private final List<Carta> cartas;

    public PilaDescartes(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public List<Carta> getCartas() {
        return cartas;
    }
    
<<<<<<< codigo
    /**
     * Metodo para obtener la ultima carta de la pida de descarte
     * @return La carta que Este Arriba de la Pila de Descarte
     */
    public Carta obtenerCartaSuperior(){
        if(!cartas.isEmpty()){
            return cartas.getLast();
        }
        return null;
    }
    
    /**
     * Metodo para Agregar la carta a la pida de descarte p
     * @param carta
     */
    public void agregarCarta(String carta){
        /*Aqui seria pensar en que esste va a hacer el metodo para agregarlo directo
        lo que tentriamos que ver aqui es como se van a agregar las cartas el primero para que sea el ultimo
        o el ultimo sea la carta mas reciente jugada
        by Edgar Acevedo
        */
    }
>>>>>>> Stashed changes
=======
>>>>>>> main
    
}
