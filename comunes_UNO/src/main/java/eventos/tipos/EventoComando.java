package eventos.tipos;

import dtos.ComandoJugadorDTO;
import eventos.IEvento;

public class EventoComando implements IEvento {
    
    public static final String TIPO = "ENVIAR_COMANDO";
    private final ComandoJugadorDTO comandoDTO;

    public EventoComando(ComandoJugadorDTO comandoDTO) {
        this.comandoDTO = comandoDTO;
    }

    public ComandoJugadorDTO getComandoDTO() {
        return comandoDTO;
    }

    @Override
    public String getTipoEvento() {
        return TIPO;
    }
}