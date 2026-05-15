/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.servidor;

import dtos.CartaDTO;
import dtos.EstadoLobbyDTO;
import dtos.EstadoMesaDTO;
import dtos.JugadorLobbyDTO;
import dtos.OponenteDTO;
import java.util.ArrayList;
import java.util.List;
import modelo.Carta;
import modelo.Comodin;
import modelo.Jugador;
import modelo.Numerica;
import modelo.Partida;
/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class TraductorDTO {

    public static EstadoMesaDTO generarEstadoParaJugador(Partida partida, String idJugadorDestino) {
        Jugador jugadorDestino = null;
        List<OponenteDTO> oponentesDTO = new ArrayList<>();

        // 1. Separamos quién es el jugador que lo pidió y quiénes son sus oponentes
        for (Jugador j : partida.getJugadores()) {
            boolean esSuTurno = partida.getTurno().getJugadorActual().equals(j);
            
            if (j.getNombre().equals(idJugadorDestino)) {
                jugadorDestino = j;
            } else {
                // A los oponentes SOLO les copiamos la cantidad de cartas, no las cartas reales
                oponentesDTO.add(new OponenteDTO(j.getNombre(), j.getNombre(), j.getMano().contarCartas(), j.isEstadoUNO(), esSuTurno));
            }
        }

        // 2. Traducimos la mano real a CartaDTOs para el jugador que lo pidió
        List<CartaDTO> manoDTO = new ArrayList<>();
        if (jugadorDestino != null) {
            for (Carta c : jugadorDestino.getMano().getCartas()) {
                manoDTO.add(traducirCarta(c));
            }
        }
        
        // 3. Traducimos la carta del centro de la mesa
        CartaDTO cartaCentroDTO = traducirCarta(partida.getPilaDescartes().obtenerCartaSuperior());
        String colorActivo = partida.getColorActivo() != null ? partida.getColorActivo().name() : "";

        // 4. Armamos el paquete final
        return new EstadoMesaDTO(idJugadorDestino, manoDTO, cartaCentroDTO, colorActivo, oponentesDTO, null);
    }
    
    public static EstadoLobbyDTO generarEstadoLobby(Partida partida) {
        
        List<JugadorLobbyDTO> listaJugadoresDTO = new ArrayList<>();
        
        // El primer jugador de la lista siempre será considerado el Host
        boolean esHost = true; 
        
        for (Jugador jugador : partida.getJugadores()) {
            // Usamos exactamente tu constructor: (String nombre, boolean esHost, boolean estaListo)
            JugadorLobbyDTO jDTO = new JugadorLobbyDTO(jugador.getNombre(), esHost, false);
            
            listaJugadoresDTO.add(jDTO);
            esHost = false; // Los siguientes que evaluemos ya no serán host
        }
        
        // Usamos exactamente tu constructor: (String codigoSala, List<JugadorLobbyDTO> jugadoresEnSala)
        return new EstadoLobbyDTO(partida.getCodigoSala(), listaJugadoresDTO);
    }

    private static CartaDTO traducirCarta(Carta carta) {
        if (carta == null) return null;
        
        String valor = "";
        if (carta instanceof Numerica) {
            valor = String.valueOf(((Numerica) carta).obtenerValor());
        } else if (carta instanceof Comodin) {
            valor = ((Comodin) carta).obtenerAccion().name();
        }
        return new CartaDTO(carta.getIdCarta(), carta.getColor().name(), valor);
    }
}
