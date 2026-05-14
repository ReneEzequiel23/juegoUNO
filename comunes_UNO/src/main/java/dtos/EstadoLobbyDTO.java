package dtos;

import java.io.Serializable;
import java.util.List;

public class EstadoLobbyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigoSala; // Para tu etiqueta "Código de sala: XPLASF"
    private List<JugadorLobbyDTO> jugadoresEnSala;

    public EstadoLobbyDTO(String codigoSala, List<JugadorLobbyDTO> jugadoresEnSala) {
        this.codigoSala = codigoSala;
        this.jugadoresEnSala = jugadoresEnSala;
    }

    public String getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public List<JugadorLobbyDTO> getJugadoresEnSala() {
        return jugadoresEnSala;
    }

    public void setJugadoresEnSala(List<JugadorLobbyDTO> jugadoresEnSala) {
        this.jugadoresEnSala = jugadoresEnSala;
    }

    
}