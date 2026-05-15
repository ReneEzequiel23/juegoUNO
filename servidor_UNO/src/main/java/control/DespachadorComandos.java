/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.ComandoJugadorDTO;
import static dtos.TipoAccion.ACEPTAR_JUGADOR;
import modelo.Jugador;
import modelo.Partida;

/**
 *
 * @author ReneEzequiel23
 */
public class DespachadorComandos {

    private final LobbyControlador lobbyCtrl;
    private final PartidaControlador juegoCtrl;
    private final Partida partida;

    public DespachadorComandos(Partida partida, LobbyControlador lobbyCtrl, PartidaControlador juegoCtrl) {
        this.partida = partida;
        this.lobbyCtrl = lobbyCtrl;
        this.juegoCtrl = juegoCtrl;
    }

    public void procesar(ComandoJugadorDTO comando) {
        Jugador jugador = obtenerJugador(comando.getIdJugador());

        switch (comando.getTipoAccion()) {
            // Comandos de la Sala de Espera
            case ENTRAR_LOBBY:
            case MARCAR_LISTO:
            case DESMARCAR_LISTO:
            case SOLICITAR_INICIO:
            case BUSCAR_LOBBY:
            case SOLICITAR_UNIRSE:
                
            case ACEPTAR_JUGADOR:
                lobbyCtrl.procesarComando(comando, jugador);
                break;

            // Comandos de la Mesa de Juego
            case JUGAR_CARTA:
            case ROBAR:
            case GRITAR_UNO:
            case DENUNCIAR:
                juegoCtrl.procesarComando(comando, jugador);
                break;
        }
    }

    // Movemos este método auxiliar aquí, ya que es útil para enrutar
    private Jugador obtenerJugador(String idJugador) {
        for (Jugador j : partida.getJugadores()) {
            if (j.getNombre().equals(idJugador)) {
                return j;
            }
        }
        return null;
    }

    private Partida obtenerPartida(String codigo) {
        if (partida.getCodigoSala() == codigo) {
            return partida;
        }
        return null;
    }
}
