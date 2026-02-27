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
public class Mano {
 
    private final List<Carta> cartas;
    
    public Mano(List<Carta> cartas){
        this.cartas = cartas;
    }

    public List<Carta> getCartas() {
        return cartas;
    }
    
    
    
    
}
