/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

/**
 *
 * @author renee, edgar
 */
public class Mano {

    private final List<Carta> cartas;
    
    public Mano(List<Carta> cartas){
        this.cartas = cartas;
    }

    public List<Carta> getCartas() {
        return cartas;
    }
    
    /**
     * Metodo para Agregar una carta a tu mano
     * @param Carta Carta a Agregar a la mano
     */
    public void agregarCarta(String Carta){
        /*Aqui seria pensar en qu8e se va a agregar, si la carta completa o
        el strihg de la carta. Sies el string Seria buscarlo por idCarta por otro metodo
        Y este va a hacer directo, en el control podemos hacer que sea el que este validado
        by Edgar Acevedo
        */
        
    }
    
    
    public void eliminarCarta(String Carta){
        /*Lo mismo para agreagar
        by Edgar Acevedo
        */
    }
    
    public int contarCartaS(){
        /*
        En el control validar si esta vacio
        by Edgar Acevedo
        */
        return cartas.size();
    }
    
}
