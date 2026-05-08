/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
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

    private static int CARTAS_POR_JUGADOR = 7;
    private final List<IObserver> observadores;

    public Partida(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.mazo = new Mazo(); // El constructor de Mazo ya inicializa y baraja
        this.pilaDescartes = new PilaDescartes();
        this.observadores = new ArrayList<>();

        // Inicializamos el primer turno con el primer jugador de la lista
        this.turno = new Turno(jugadores.get(0), true, 30, 30);
    }
    
    public void agregarObservador(IObserver observador) {
        this.observadores.add(observador);
    }

    // 3. Método para "gritar" que hubo un cambio
    public void notificarObservadores() {
        for (IObserver obs : observadores) {
            obs.actualizar();
        }
    }

    /**
     * reparte cartas y coloca la primera en la pila.
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
     * Calcula quién es el siguiente en la lista basándose en el sentido del
     * turno.
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

    public void saltarSiguienteTurno() {
        turno.marcarSalto();
    }

    public void avanzarTurno() {
        Jugador siguiente = obtenerSiguienteJugador();
        turno.setJugadorActual(siguiente);

        if (turno.debeSaltar()) {
            turno.limpiarSalto(); // Limpia el castigo

            Jugador despuesDelCastigado = obtenerSiguienteJugador();
            turno.setJugadorActual(despuesDelCastigado);
        }
    }
    
    /**
     * Extrae una carta del mazo. Si el mazo está vacío, coordina automáticamente
     * el reciclaje usando la pila de descartes.
     */
    public Carta robarCartaSeguro() {
        Carta carta = mazo.robarCarta();
        
        if (carta == null) {
            // 1. El mazo está vacío, pedimos las cartas a la pila
            List<Carta> cartasParaReciclar = pilaDescartes.vaciarParaReciclar();
            
            // 2. Caso extremo: ¿Qué pasa si la pila tampoco tiene cartas? 
            // (Pasa si entre todos los jugadores tienen las 108 cartas en la mano)
            if (cartasParaReciclar.isEmpty()) {
                System.out.println("No hay cartas en el mazo ni en la pila para reciclar.");
                return null; 
            }
            
            // 3. Entregamos las cartas al mazo para que las integre y baraje
            mazo.reciclarCartas(cartasParaReciclar);
            
            // 4. Volvemos a intentar robar ahora que el mazo está lleno
            carta = mazo.robarCarta();
        }
        
        return carta;
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

    public Color getColorActivo() {
        return colorActivo;
    }

    public static int getCARTAS_POR_JUGADOR() {
        return CARTAS_POR_JUGADOR;
    }

}
