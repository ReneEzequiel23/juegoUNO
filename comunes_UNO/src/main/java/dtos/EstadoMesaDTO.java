package dtos;

import java.io.Serializable;
import java.util.List;

public class EstadoMesaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idJugadorActual;
    private List<CartaDTO> miMano;
    private CartaDTO cartaEnMesa;
    private String colorActivo;
    private List<OponenteDTO> oponentes;
    private String idGanador;

    public EstadoMesaDTO(String idJugadorActual, List<CartaDTO> miMano, CartaDTO cartaEnMesa, String colorActivo, List<OponenteDTO> oponentes, String idGanador) {
        this.idJugadorActual = idJugadorActual;
        this.miMano = miMano;
        this.cartaEnMesa = cartaEnMesa;
        this.colorActivo = colorActivo;
        this.oponentes = oponentes;
        this.idGanador = idGanador;
    }

    public String getIdJugadorActual() { return idJugadorActual; }
    public List<CartaDTO> getMiMano() { return miMano; }
    public CartaDTO getCartaEnMesa() { return cartaEnMesa; }
    public String getColorActivo() { return colorActivo; }
    public List<OponenteDTO> getOponentes() { return oponentes; }
    public String getIdGanador() { return idGanador; }
}