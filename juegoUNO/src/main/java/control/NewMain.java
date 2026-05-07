package control;

import modelo.*;

import java.util.ArrayList;
import java.util.List;

public class NewMain {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO SIMULADOR DE UNO ===");

        // 1. Crear Jugadores 
        Jugador j1 = new Jugador("René", "avatar_rene.png");
        Jugador j2 = new Jugador("Edgar", "avatar_edgar.png");

        List<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(j1);
        listaJugadores.add(j2);

        // 2. Crear Partida y Controlador
        Partida partida = new Partida(listaJugadores);
        PartidaControlador controlador = new PartidaControlador(partida);

        // 3. Iniciar el juego (Repartir cartas y poner la primera)
        partida.iniciarJuego();

        System.out.println("Cartas repartidas.");
        imprimirEstadoManos(listaJugadores);

        // 4. Bucle de simulación (Limitado a 15 turnos para la prueba)
        System.out.println("\n=== COMIENZA LA PARTIDA ===");
        
        for (int turno = 1; turno <= 15; turno++) {
            Jugador jugadorActual = partida.getTurno().getJugadorActual();
            Carta cartaEnMesa = partida.getPilaDescartes().obtenerCartaSuperior();
            
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println("Carta en mesa: " + formatearCarta(cartaEnMesa));
            System.out.println("Color activo: " + partida.getColorActivo());
            System.out.println("Le toca a: " + jugadorActual.getNombre() + " (Cartas: " + jugadorActual.getMano().contarCartas() + ")");

            boolean jugoCarta = false;

            // Simular "Inteligencia Artificial" básica: buscar la primera carta jugable
            for (Carta cartaEnMano : jugadorActual.getMano().getCartas()) {
                if (controlador.validarJugada(cartaEnMano)) {
                    
                    // Si le quedan 2 cartas y va a tirar una, ¡Grita UNO!
                    if (jugadorActual.getMano().contarCartas() == 2) {
                        controlador.gritarUNO(jugadorActual);
                    }

                    // Si es negra, elegimos ROJO por defecto para la prueba
                    Color colorElegido = (cartaEnMano.getColor() == Color.NEGRO) ? Color.ROJO : null;
                    
                    System.out.println("-> " + jugadorActual.getNombre() + " juega: " + formatearCarta(cartaEnMano));
                    
                    // Ejecutar la jugada a través del controlador
                    boolean exito = controlador.jugarCarta(jugadorActual, cartaEnMano.getIdCarta(), colorElegido);
                    
                    if (exito) {
                        jugoCarta = true;
                        // Si se quedó sin cartas, la partida terminó (el controlador ya calculó los puntos)
                        if (jugadorActual.getMano().contarCartas() == 0) {
                            System.out.println("\n🏆 ¡FIN DEL JUEGO! 🏆");
                            return; // Terminamos la simulación
                        }
                        break; // Terminó su jugada
                    }
                }
            }

            // Si no encontró ninguna carta válida, debe robar
            if (!jugoCarta) {
                System.out.println("-> " + jugadorActual.getNombre() + " no tiene cartas válidas. Roba del mazo.");
                controlador.robarCartaEnTurno(jugadorActual);
            }
        }
        
        System.out.println("\n=== FIN DE LA SIMULACIÓN (Límite de turnos alcanzado) ===");
        imprimirEstadoManos(listaJugadores);
    }

    /**
     * Método auxiliar para imprimir las cartas de forma legible en consola.
     */
    private static String formatearCarta(Carta carta) {
        if (carta instanceof Numerica) {
            return "Numérica [" + ((Numerica) carta).obtenerValor() + " " + carta.getColor() + "]";
        } else if (carta instanceof Comodin) {
            return "Comodín [" + ((Comodin) carta).obtenerAccion() + " " + carta.getColor() + "]";
        }
        return "Desconocida";
    }

    /**
     * Método auxiliar para ver cuántas cartas tiene cada quien.
     */
    private static void imprimirEstadoManos(List<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            System.out.println(j.getNombre() + " tiene " + j.getMano().contarCartas() + " cartas.");
        }
    }
}