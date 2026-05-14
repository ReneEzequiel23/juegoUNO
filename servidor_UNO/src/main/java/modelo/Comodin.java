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
    
    // Dentro de Comodin.java
    @Override
    public void aplicarEfecto(Partida partida) {
        switch (this.obtenerAccion()) {
            case REVERSA:
                partida.getTurno().cambiarSentido();
                break;
            case BLOQUEO:
                partida.saltarSiguienteTurno();
                break;
            case TOMA2:
                partida.penalizarSiguienteJugador(2);
                partida.saltarSiguienteTurno();
                break;
            case TOMA4:
                partida.penalizarSiguienteJugador(4);
                partida.saltarSiguienteTurno();
                break;
            case CAMBIOCOLOR:
                // El cambio de color ya se maneja al tirarla
                break;
        }
    }
}
