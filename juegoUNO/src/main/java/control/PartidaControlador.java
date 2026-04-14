/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import modelo.*;

/**
 *
 * @author renee, edgar
 */
public class PartidaControlador {
    /*Dividan los metodos por el tipo de clase que sea, y si se puede por el
    flujo normal
    Entran Flujos, validaciones basicas, metodos
    supongan que en las clases estan el metodo directo y aqui el que va a hacer una validacion 
    basica.
    By Edgar Acevedo
     */
  
    /*
    --------------- Lista de Control ------------------
    27 -- Flujo NOrmal
    etc
    */
    
    
    //Flujo Normal
    //Partida
    //Jugador
    //Mano
    //Turno
    //Mazo
    //Carta
    //PilaDescartes
    
    private final Partida partida;

    public PartidaControlador(Partida partida) {
        this.partida = partida;
    }

    /**
     * Valida si una carta específica se puede jugar en este momento.
     */
    public boolean validarJugada(Carta cartaAJugar) {
        //Los comodines negros (Toma 4, Cambio de Color) siempre son válidos.
        if (cartaAJugar.getColor() == Color.NEGRO) {
            return true;
        }

        // coincide con el color activo actual de la mesa.
        // Usamos partida.getColorActivo() porque si alguien tiró un comodín negro antes,
        // el color a seguir no es el de la carta, sino el que el jugador eligió.
        if (cartaAJugar.getColor() == partida.getColorActivo()) {
            return true;
        }

        // regla de Coincidencia (Número o Símbolo)
        Carta cartaEnMesa = partida.getPilaDescartes().obtenerCartaSuperior();

        // Validar si ambas son numéricas y tienen el mismo número
        if (cartaAJugar instanceof Numerica && cartaEnMesa instanceof Numerica) {
            Numerica numJugar = (Numerica) cartaAJugar;
            Numerica numMesa = (Numerica) cartaEnMesa;
            return numJugar.obtenerValor() == numMesa.obtenerValor();
        }

        // Validar si ambas son cartas de acción (ej. un Toma 2 rojo sobre un Toma 2 verde)
        if (cartaAJugar instanceof Comodin && cartaEnMesa instanceof Comodin) {
            Comodin comJugar = (Comodin) cartaAJugar;
            Comodin comMesa = (Comodin) cartaEnMesa;
            return comJugar.obtenerAccion() == comMesa.obtenerAccion();
        }

        // Si no cumple ninguna de las reglas anteriores, la jugada es inválida.
        return false;
    }

    /**
     * Intenta ejecutar la jugada. Este es el método que llamarías 
     * cuando llegue el mensaje del Socket.
     */
    public boolean jugarCarta(Jugador jugador, String idCarta) {
        // 1. Verificar si es el turno del jugador (Seguridad básica)
        if (!partida.getTurno().getJugadorActual().equals(jugador)) {
            System.out.println("No es tu turno.");
            return false;
        }

        // 2. Buscar la carta en la mano del jugador
        Carta cartaAJugar = jugador.getMano().obtenerCartaPorId(idCarta);
        if (cartaAJugar == null) {
            System.out.println("El jugador no tiene esa carta.");
            return false;
        }

        // 3. Validar las reglas del juego
        if (validarJugada(cartaAJugar)) {
            // ¡Jugada válida! Extraemos la carta de la mano
            jugador.getMano().eliminarCarta(idCarta);
            
            // y la ponemos en la pila de descartes.
            partida.getPilaDescartes().agregarCarta(cartaAJugar);
            
            // Actualizamos el color activo (si tiró un 5 azul, el color activo ahora es azul)
            // Si es negra, el color activo se actualizará después cuando el jugador elija el color.
            if (cartaAJugar.getColor() != Color.NEGRO) {
                partida.actualizarColorActivo(cartaAJugar.getColor());
            }

            return true; // La carta se jugó con éxito
        }

        System.out.println("Jugada inválida. La carta no coincide.");
        return false;
    }
}
