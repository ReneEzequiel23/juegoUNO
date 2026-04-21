/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author renee, edgar
 */
public class Mano {

    private final List<Carta> cartas;
    
    public Mano() {
        //lista vacía
        this.cartas = new ArrayList<>();
    }

    /**
     * Permite recibir una carta del mazo o de una penalización.
     * @param carta El objeto Carta a añadir.
     */
    public void agregarCarta(Carta carta) {
        if (carta != null) {
            this.cartas.add(carta);
        }
    }

    /**
     * Elimina una carta de la mano cuando el jugador la juega.
     * @param idCarta El identificador único de la carta.
     * @return La carta eliminada si se encontró, o null si no estaba en la mano.
     */
    public Carta eliminarCarta(String idCarta) {
        Optional<Carta> cartaEncontrada = cartas.stream()
                .filter(c -> c.getIdCarta().equals(idCarta))
                .findFirst();

        if (cartaEncontrada.isPresent()) {
            Carta c = cartaEncontrada.get();
            cartas.remove(c);
            return c;
        }
        return null;
    }

    /**
     * Útil para que el controlador valide si la jugada es posible 
     * antes de intentar eliminarla.
     */
    public Carta obtenerCartaPorId(String idCarta) {
        return cartas.stream()
                .filter(c -> c.getIdCarta().equals(idCarta))
                .findFirst()
                .orElse(null);
    }

    public int contarCartas() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        return new ArrayList<>(cartas);
    }

    @Override
    public String toString() {
        return "Mano{cantidad=" + cartas.size() + "}";
    }
}
