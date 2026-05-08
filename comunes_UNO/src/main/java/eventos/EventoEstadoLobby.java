package eventos;

import dtos.EstadoLobbyDTO;
import eventos.IEvento;

public class EventoEstadoLobby implements IEvento {
    
    public static final String TIPO = "ESTADO_LOBBY_ACTUALIZADO";
    private final EstadoLobbyDTO estadoLobbyDTO;

    public EventoEstadoLobby(EstadoLobbyDTO estadoLobbyDTO) {
        this.estadoLobbyDTO = estadoLobbyDTO;
    }

    public EstadoLobbyDTO getEstadoLobbyDTO() {
        return estadoLobbyDTO;
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }
}