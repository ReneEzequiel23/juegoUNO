package dtos;

import java.io.Serializable;
import modelo.Accion;

public class ComandoJugadorDTO implements Serializable {
    private String idJugador;
    private Accion accion; // Enum: JUGAR_CARTA, ROBAR, GRITAR_UNO, DENUNCIAR
    
    // Atributos opcionales (dependen de la acción)
    private String idCartaJugada; 
    private String colorElegido; // Solo si jugó una carta negra
    private String idJugadorDenunciado; // Solo si la acción es DENUNCIAR

    // Constructor completo
    public ComandoJugadorDTO(String idJugador, Accion accion, String idCartaJugada, String colorElegido, String idJugadorDenunciado) {
        this.idJugador = idJugador;
        this.accion = accion;
        this.idCartaJugada = idCartaJugada;
        this.colorElegido = colorElegido;
        this.idJugadorDenunciado = idJugadorDenunciado;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public String getIdCartaJugada() {
        return idCartaJugada;
    }

    public void setIdCartaJugada(String idCartaJugada) {
        this.idCartaJugada = idCartaJugada;
    }

    public String getColorElegido() {
        return colorElegido;
    }

    public void setColorElegido(String colorElegido) {
        this.colorElegido = colorElegido;
    }

    public String getIdJugadorDenunciado() {
        return idJugadorDenunciado;
    }

    public void setIdJugadorDenunciado(String idJugadorDenunciado) {
        this.idJugadorDenunciado = idJugadorDenunciado;
    }
    
}
