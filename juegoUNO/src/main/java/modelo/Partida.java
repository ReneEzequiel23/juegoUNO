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
public class Partida {
    
    private final List<Jugador> jugadores;
    private final Turno turno;
    private final PilaDescartes pilaDescartes;
    private final Mazo mazo;

    public Partida(List<Jugador> jugadores, Turno turno, PilaDescartes pilaDescartes, Mazo mazo) {
        this.jugadores = jugadores;
        this.turno = turno;
        this.pilaDescartes = pilaDescartes;
        this.mazo = mazo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Turno getTurno() {
        return turno;
    }

    public PilaDescartes getPilaDescartes() {
        return pilaDescartes;
    }

    public Mazo getMazo() {
        return mazo;
    }

    @Override
    public String toString() {
        return "Partida{" + "jugadores=" + jugadores + ", turno=" + turno + ", pilaDescartes=" + pilaDescartes + ", mazo=" + mazo + '}';
    }


    
}
