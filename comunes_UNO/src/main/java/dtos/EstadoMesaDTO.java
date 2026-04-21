package dtos;

import java.io.Serializable;
import java.util.List;

public class EstadoMesaDTO implements Serializable {
    // Lo que el jugador ve de sí mismo
    private String idJugadorActual; 
    private List<CartaDTO> miMano;
    
    // Lo que el jugador ve de la mesa
    private CartaDTO cartaEnMesa;
    private String colorActivo;
    
    // Lo que el jugador ve de los demás
    private List<OponenteDTO> oponentes;
    
    // ¿Ya terminó el juego?
    private String idGanador; // null si seguimos jugando

    public EstadoMesaDTO(String idJugadorActual, List<CartaDTO> miMano, CartaDTO cartaEnMesa, String colorActivo, List<OponenteDTO> oponentes, String idGanador) {
        this.idJugadorActual = idJugadorActual;
        this.miMano = miMano;
        this.cartaEnMesa = cartaEnMesa;
        this.colorActivo = colorActivo;
        this.oponentes = oponentes;
        this.idGanador = idGanador;
    }

    public String getIdJugadorActual() {
        return idJugadorActual;
    }

    public void setIdJugadorActual(String idJugadorActual) {
        this.idJugadorActual = idJugadorActual;
    }

    public List<CartaDTO> getMiMano() {
        return miMano;
    }

    public void setMiMano(List<CartaDTO> miMano) {
        this.miMano = miMano;
    }

    public CartaDTO getCartaEnMesa() {
        return cartaEnMesa;
    }

    public void setCartaEnMesa(CartaDTO cartaEnMesa) {
        this.cartaEnMesa = cartaEnMesa;
    }

    public String getColorActivo() {
        return colorActivo;
    }

    public void setColorActivo(String colorActivo) {
        this.colorActivo = colorActivo;
    }

    public List<OponenteDTO> getOponentes() {
        return oponentes;
    }

    public void setOponentes(List<OponenteDTO> oponentes) {
        this.oponentes = oponentes;
    }

    public String getIdGanador() {
        return idGanador;
    }

    public void setIdGanador(String idGanador) {
        this.idGanador = idGanador;
    }
    
}