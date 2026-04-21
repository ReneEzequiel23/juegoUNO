/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renee
 */
public class Comodin extends Carta {
    
    private final Accion accion;

    public Comodin(String idCarta, Color color, Accion accion) {
        super(idCarta, color);
        this.accion = accion;
    }
    
    public Accion obtenerAccion(){
        return accion;
    }

    @Override
    public String obtenerTipo() {
        return "COMODIN";
    }
}
