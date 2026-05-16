/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author Adrián
 */
public class EstadoConfiguracionDTO implements Serializable{
    
    //Host de la partida
    /**
     * cartasMano número máximo de cartas en la mano actuales
     * 
     */
    private String idHost; 
    private int cartasMano;
    private int tiempoTotal;
    private int numJugadores;

    /**
     * 
     * @param idHost Host de la partida
     * @param cartasMano  número máximo de cartas en la mano actuales
     * @param tiempoTotal segundos actuales máximos del turno de un jugador
     * @param numJugadores cantidad máxima de jugadores en una partida
     */
    public EstadoConfiguracionDTO(String idHost, int cartasMano, int tiempoTotal, int numJugadores) {
        this.idHost = idHost;
        this.cartasMano = cartasMano;
        this.tiempoTotal = tiempoTotal;
        this.numJugadores = numJugadores;
    }

    public String getIdHost() {
        return idHost;
    }

    public void setIdHost(String idHost) {
        this.idHost = idHost;
    }

    public int getCartasMano() {
        return cartasMano;
    }

    public void setCartasMano(int cartasMano) {
        this.cartasMano = cartasMano;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }
    
    
}
