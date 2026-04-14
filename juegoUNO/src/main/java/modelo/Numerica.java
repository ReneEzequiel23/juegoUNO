/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renee, edgar
 */
public class Numerica extends Carta {

    private final int numero;

    public Numerica(String idCarta, Color color, int numero) {
        super(idCarta, color);
        this.numero = numero;
    }

    public int obtenerValor() {
        return numero;
    }

    @Override
    public String obtenerTipo() {
        return "NUMERICA";
    }
}
