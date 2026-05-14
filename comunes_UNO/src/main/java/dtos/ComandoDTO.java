package dtos;

import java.io.Serializable;

public class ComandoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String accion; // Ej: Protocolo.SOLICITAR_INICIO
    private String idJugador; // Quién ejecuta la acción
    private String idCarta; // Opcional: Solo si está jugando una carta

    public ComandoDTO(String accion, String idJugador) {
        this.accion = accion;
        this.idJugador = idJugador;
    }

    public ComandoDTO(String accion, String idJugador, String idCarta) {
        this.accion = accion;
        this.idJugador = idJugador;
        this.idCarta = idCarta;
    }

    // Getters y Setters...
    public String getAccion() { return accion; }
    public String getIdJugador() { return idJugador; }
    public String getIdCarta() { return idCarta; }
}