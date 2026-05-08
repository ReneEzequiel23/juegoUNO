/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.ComandoJugadorDTO;
import eventos.EventBus;
import java.util.ArrayList;
import java.util.List;
import modelo.Jugador;
import modelo.Partida;
/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class LobbyControlador {
    private final Partida partida;

    public LobbyControlador(Partida partida) {
        this.partida = partida;
    }

    public void procesarComando(ComandoJugadorDTO comando, Jugador jugador) {
        // 1. Si el jugador NO existe pero está pidiendo ENTRAR, lo creamos y lo unimos a la mesa
        if (jugador == null && comando.getTipoAccion() == dtos.TipoAccion.ENTRAR_LOBBY) {
            jugador = new Jugador(comando.getIdJugador());
            partida.getJugadores().add(jugador);
        }
        // 2. Si sigue sin existir y NO es el Host forzando el inicio, entonces sí lo ignoramos
        else if (jugador == null && comando.getTipoAccion() != dtos.TipoAccion.SOLICITAR_INICIO) {
            return;
        }
        
        switch (comando.getTipoAccion()) {
            case ENTRAR_LOBBY:
                System.out.println("[Lobby] " + jugador.getNombre() + " solicita ver el lobby.");
                verificarCondicionesDeInicio();
                notificarCambioEnLobby();
                break;
            case MARCAR_LISTO:
                jugador.setEstaListo(true);
                System.out.println("[Lobby] " + jugador.getNombre() + " está listo.");
                verificarCondicionesDeInicio();
                notificarCambioEnLobby();
                break;
            case DESMARCAR_LISTO:
                jugador.setEstaListo(false);
                notificarCambioEnLobby();
                break;
            case SOLICITAR_INICIO:
                if (partida.getJugadores().size() >= 2) {
                    System.out.println("[Lobby] El Host ha forzado el inicio.");
                    iniciarYNotificar();
                }
                break;
        }
    }

    private void verificarCondicionesDeInicio() {
        List<Jugador> lista = partida.getJugadores();
        int cantidad = lista.size();

        if (cantidad == 4) {
            System.out.println("[Lobby] Sala llena. Iniciando partida automáticamente...");
            iniciarYNotificar();
            return;
        }

        if (cantidad >= 2) {
            boolean todosListos = true;
            for (Jugador j : lista) {
                if (!j.isEstaListo()) {
                    todosListos = false;
                    break;
                }
            }
            if (todosListos) {
                System.out.println("[Lobby] Todos listos. Iniciando automáticamente...");
                iniciarYNotificar();
            }
        }
    }

    private void iniciarYNotificar() {
        partida.iniciarJuego();
        // Disparamos el evento de la mesa para todos
        eventos.EventBus.getInstance().publicar(new eventos.tipos.EventoEstadoMesa(null)); 
    }

    private void notificarCambioEnLobby() {
        List<dtos.JugadorLobbyDTO> listaLobby = new ArrayList<>();
        for (int i = 0; i < partida.getJugadores().size(); i++) {
            Jugador j = partida.getJugadores().get(i);
            listaLobby.add(new dtos.JugadorLobbyDTO(j.getNombre(), (i == 0), j.isEstaListo()));
        }

        dtos.EstadoLobbyDTO estadoLobby = new dtos.EstadoLobbyDTO(partida.getCodigoSala(), listaLobby);
        eventos.EventBus.getInstance().publicar(new eventos.tipos.EventoEstadoLobby(estadoLobby));
    }
}
