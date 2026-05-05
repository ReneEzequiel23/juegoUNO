
package com.mycompany.juegouno;


import control.PartidaControlador;
import eventos.EventBus;
import eventos.JugadorAbandonoEvento;
import eventos.SuscriptorEvento;
import eventos.Evento;
import modelo.*;
import vista.PantallaPartida;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonathan
 */
public class JuegoUNO {

    public static void main(String[] args) throws InterruptedException {
        boolean modoSwing = args.length == 0 || args[0].equals("swing");

        if (modoSwing) {
            lanzarModoSwing();
        } else {
            lanzarModoConsola();
        }
    }


    private static void lanzarModoSwing() {

        Jugador j1 = new Jugador("Tu", "avatar1");
        Jugador j2 = new Jugador("Luis", "avatar2");
        Jugador j3 = new Jugador("Sofia", "avatar3");
        j1.id = "j1";
        j2.id = "j2";
        j3.id = "j3";

        List<Jugador> jugadores = new ArrayList<>(List.of(j1, j2, j3));


        Turno turno = new Turno(j1, true, 30, 30);
        Mazo mazo = new Mazo();
        PilaDescartes pila = new PilaDescartes(new ArrayList<>());
        Partida partida = new Partida(jugadores, turno, pila, mazo);


        PartidaControlador controlador = new PartidaControlador(partida);


        javax.swing.SwingUtilities.invokeLater(() -> {
            PantallaPartida pantalla = new PantallaPartida(
                    "j1", "Tu", controlador, jugadores
            );
            pantalla.setVisible(true);
        });
    }


    private static void lanzarModoConsola() {
        System.out.println("========================================");
        System.out.println("  Cu:Abandonar Partida");
        System.out.println("========================================\n");

        Jugador j1 = new Jugador("Ana", "avatar1");
        Jugador j2 = new Jugador("Luis", "avatar2");
        Jugador j3 = new Jugador("Sofia", "avatar3");
        j1.id = "j1";
        j2.id = "j2";
        j3.id = "j3";

        List<Jugador> jugadores = new ArrayList<>(List.of(j1, j2, j3));
        Partida partida = new Partida(jugadores,
                new Turno(j1, true, 30, 30),
                new PilaDescartes(new ArrayList<>()),
                new Mazo());

        System.out.println("Jugadores: Ana, Luis, Sofia\n");

        EventBus.instancia().suscribir(JugadorAbandonoEvento.TIPO, new SuscriptorEvento() {
            @Override
            public void onEvento(Evento e) {
                JugadorAbandonoEvento ev = (JugadorAbandonoEvento) e;
                System.out.println("[EVENTO] " + ev.getJugadorNombre()
                        + " abandono. Restantes: " + ev.getJugadoresRestantes());
            }
        });

        PartidaControlador controlador = new PartidaControlador(partida);
        System.out.println(">>> Ana abandona...");
        controlador.procesarAbandono("j1");

        System.out.println("\n========================================");
        System.out.println("Jugadores restantes: " + partida.getJugadores().size());
        partida.getJugadores().forEach(j -> System.out.println("  - " + j.getNombre()));
        System.out.println("Debe terminar: " + partida.debeTerminar());
        System.out.println("========================================");
    }
}
