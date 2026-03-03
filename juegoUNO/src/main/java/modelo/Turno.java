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
    
<<<<<<< Updated upstream
=======
    private final Jugador jugadorActual;
    public boolean sentido;
    public int tiempoLimite;
    public int tiempoRestante;
    
    /*
    Yo digo que para el tiempo limite se un final, o con un valor definido por
    que todos van a tener el mismo tiempo hacer una jugada.
    Y el setter de este sea reiniciar su tiempo
    Para El tiempo restante no se si entre para este caso, por que va a ser mas para
    o maybe si entre para el mvc. Pero ahi vemos.
    By Edgar Acevedo
    */

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
    
    
    
>>>>>>> Stashed changes
}
