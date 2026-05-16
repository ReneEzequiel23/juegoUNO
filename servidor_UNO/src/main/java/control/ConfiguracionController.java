package control;

import modelo.*;

/**
 *
 * @author Adrián
 */
public class ConfiguracionController {

    /**
     * partida es la partida que se configurará cartasPartida son el número de
     * cartas máximo que un jugador puede tener en la mano
     */
    private final Partida partida;
    int cartasPartida = Partida.getCARTAS_POR_JUGADOR();

    /**
     * Constructor del configurador
     *
     * @param partida la partida que se configurará.
     */
    public ConfiguracionController(Partida partida) {
        this.partida = partida;
    }

    /**
     * Metodo para cambiar el número de jugadores máximo dentro de la partida,
     * son de un rango de 2-4 jugadores jugadoresLista, el número de jugadores
     * que hay en la partida
     *
     * @param jugadoresTotales es el número de jugadores másximo que el host
     * quiere en la partida ahora
     * @return si el valor nuevo y la lista de jugadores final anterior son
     * iguales, no se actualiza. Si son diferentes, se actualizará el número
     * máximo de jugadores.
     */
    public int cambiarNumeroJugadores(int jugadoresTotales) {
        int jugadoresLista = partida.getJugadores().size();
        if (jugadoresTotales == jugadoresLista) {
            System.out.println("El número de jugadores máximos por es igual");
            System.out.println("Ingrese otro valor");
            return jugadoresLista;
        } else {
            System.out.println("El tiempo se ha actualizado exitosamente");
            System.out.println("Tiempo anterior: " + jugadoresLista + " Tiempo actual: " + jugadoresTotales);
            jugadoresLista = jugadoresTotales;
            return jugadoresLista;
        }
    }

    /**
     * Metodo para cambiar el tiempo limite del temporizador del turno de cada
     * jugador
     *
     * @param tiempoLimite el tiempo limite nuevo que da el host tiempoPasado:
     * el tiempo limite anterior
     * @return se regresa tiempoPasado si tiempoLimite y tiempoPasado son
     * iguales. Sino, tiempoLimite pasa a ser el nuevo tiempo limite del turno y
     * se regresa tiempoLimite
     */
    public int cambiarTemporizador(int tiempoLimite) {
        int tiempoPasado = partida.getTurno().getTiempoLimite();
        if (tiempoLimite == partida.getTurno().tiempoLimite) {
            System.out.println("El tiempo limite es igual");
            System.out.println("Ingrese otro valor");
            return tiempoPasado;
        } else {
            partida.getTurno().setTiempoLimite(tiempoLimite);
            System.out.println("El tiempo se ha actualizado exitosamente");
            System.out.println("Tiempo anterior: " + tiempoPasado + " Tiempo actual: " + tiempoLimite);
            return tiempoLimite;
        }
    }

    /**
     * Metodo para cambiar el máximo de cartas en la mano de cada jugador, el
     * valor por defecto es 7 cartas máximo
     *
     * @param numCartas el número de cartas en la mano máximo nuevo
     * @return si numCartas y las cartasPartida son iguales, se retornará
     * cartasPartida sin cambios. Si no son iguales, cartasPartida se actualiza
     * con el valor de numCartas y se retorna cartasPartidas actualizado
     */
    public int cambiarNumeroCartasEnMano(int numCartas) {
        if (numCartas == cartasPartida) {
            System.out.println("El número de cartas por jugador limite es igual");
            System.out.println("Ingrese otro valor");
            return cartasPartida;
        } else {
            System.out.println("El número de cartas másximo en la mano se ha actualizado exitosamente!");
            System.out.println("Número de cartas en mano de jugadores máximo anterior: " + cartasPartida + " Número de cartas en mano de jugadores máximo actual: " + numCartas);
            cartasPartida = numCartas;
            return cartasPartida;
        }
        //int cartasPartida = partida.getJugadores().get.
        // obtienes jugadores, y a cada
    }

}
