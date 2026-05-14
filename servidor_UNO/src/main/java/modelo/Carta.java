/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renee. edgar
 */
public abstract class Carta {
    private final String idCarta;
    private final Color color;

    public Carta(String idCarta, Color color) {
        this.idCarta = idCarta;
        this.color = color;
    }

    public String getIdCarta() {
        return idCarta;
    }

    public Color getColor() {
        return color;
    }
    
    public abstract String obtenerTipo();
    public abstract void aplicarEfecto(Partida partida);
}
