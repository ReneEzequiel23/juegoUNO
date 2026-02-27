/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renee
 */
public class Turno {
    
    private final Jugador jugadorActual;
    boolean sentido;
    int tiempoLimite;
    int tiempoRestante;

    public Turno(Jugador jugadorActual, boolean sentido, int tiempoLimite, int tiempoRestante) {
        this.jugadorActual = jugadorActual;
        this.sentido = sentido;
        this.tiempoLimite = tiempoLimite;
        this.tiempoRestante = tiempoRestante;
    }

    public boolean isSentido() {
        return sentido;
    }

    public void setSentido(boolean sentido) {
        this.sentido = sentido;
    }

    public int getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(int tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    @Override
    public String toString() {
        return "Turno{" + "jugadorActual=" + jugadorActual + ", sentido=" + sentido + ", tiempoLimite=" + tiempoLimite + ", tiempoRestante=" + tiempoRestante + '}';
    }
    
    
    
}
