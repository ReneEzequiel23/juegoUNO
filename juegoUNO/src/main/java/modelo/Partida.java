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
public class Partida {
    private final List<Jugador> jugadores;
    private final Turno turno;
    private final PilaDescartes pilaDescartes;
    private final Mazo mazo;
    private Color colorActivo;
    
    private static final int CARTAS_POR_JUGADOR = 7;

    public Partida(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.mazo = new Mazo(); // El constructor de Mazo ya inicializa y baraja
        this.pilaDescartes = new PilaDescartes();
        
        // Inicializamos el primer turno con el primer jugador de la lista
        this.turno = new Turno(jugadores.get(0), true, 30, 30);
    }
    
    /**
     *reparte cartas y coloca la primera en la pila.
     */
    public void iniciarJuego() {
        repartirCartasIniciales();
        colocarPrimeraCarta();
    }

    private void repartirCartasIniciales() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < CARTAS_POR_JUGADOR; i++) {
                Carta carta = mazo.robarCarta();
                if (carta != null) {
                    jugador.getMano().agregarCarta(carta);
                }
            }
        }
    }

    private void colocarPrimeraCarta() {
        Carta primera = mazo.robarCarta();
        
        // Aquí la colocamos y definimos el color activo inicial.
        pilaDescartes.agregarCarta(primera);
        this.colorActivo = primera.getColor();
    }
    
    /**
     * Calcula quién es el siguiente en la lista basándose en el sentido del turno.
     */
    public Jugador obtenerSiguienteJugador() {
        int indiceActual = jugadores.indexOf(turno.getJugadorActual());
        int siguienteIndice;
        
        if (turno.isSentido()) { // Sentido Horario
            siguienteIndice = (indiceActual + 1) % jugadores.size();
        } else { // Sentido Anti-horario
            siguienteIndice = (indiceActual - 1 + jugadores.size()) % jugadores.size();
        }
        
        return jugadores.get(siguienteIndice);
    }

    public void actualizarColorActivo(Color color) {
        this.colorActivo = color;
    }

    // Getters

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

    public Color getColorActivo() {
        return colorActivo;
    }

    public static int getCARTAS_POR_JUGADOR() {
        return CARTAS_POR_JUGADOR;
    }
    
}
