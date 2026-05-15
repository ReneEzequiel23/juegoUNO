/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.ComandoJugadorDTO;
import dtos.EstadoLobbyDTO;
import dtos.JugadorLobbyDTO;
import eventos.EventBus;
import eventos.IEventBus;
import eventos.IEvento;
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
    private final IEventBus eventBus; // Agrega esta línea si no la tienes

    public LobbyControlador(Partida partida, IEventBus eventBus) {
        this.partida = partida;
        this.eventBus = eventBus;
    }

    public void procesarComando(ComandoJugadorDTO comando, Jugador jugador) {
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
            case BUSCAR_LOBBY:
                String codigoBuscado = comando.getIdCartaJugada(); // El código viaja en este campo del DTO
                
                // Evitamos el NullPointerException y verificamos si la sala existe
                if (codigoBuscado != null && codigoBuscado.equals(partida.getCodigoSala())) {
                    // ¡La sala existe! Le enviamos el estado al cliente
                    dtos.EstadoLobbyDTO estado = red.servidor.TraductorDTO.generarEstadoLobby(partida);
                    eventBus.publicar(new eventos.tipos.EventoEstadoLobby(estado));
                } else {
                    // No existe. Mandamos un estado vacío para que la vista lo rechace
                    dtos.EstadoLobbyDTO estadoVacio = new dtos.EstadoLobbyDTO("NO_EXISTE", new java.util.ArrayList<>());
                    eventBus.publicar(new eventos.tipos.EventoEstadoLobby(estadoVacio));
                }
                break;
            case SOLICITAR_UNIRSE:
                // Publicamos directamente
                eventBus.publicar(new eventos.tipos.EventoNotificacion("SOLICITUD:" + comando.getIdJugador()));
                break;

            case ACEPTAR_JUGADOR:
                String jugadorAceptado = comando.getIdCartaJugada(); 
                System.out.println("[Servidor] ¡El Host ha aceptado a " + jugadorAceptado + "!");
                
                // Evitamos duplicados por si el Host le da doble clic rápido
                boolean yaExiste = false;
                for(modelo.Jugador j : partida.getJugadores()){
                    if(j.getNombre().equals(jugadorAceptado)){
                        yaExiste = true;
                        break;
                    }
                }
                
                if(!yaExiste){
                    partida.getJugadores().add(new modelo.Jugador(jugadorAceptado));
                }
                
                // Generamos el nuevo estado y lo gritamos a todos
                dtos.EstadoLobbyDTO nuevoEstado = red.servidor.TraductorDTO.generarEstadoLobby(partida);
                eventBus.publicar(new eventos.tipos.EventoEstadoLobby(nuevoEstado));
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

    // No creo que sea asi, pero es un metodo temporal
    private void buscarLobbyEnServidor() {
        List<dtos.JugadorLobbyDTO> listaLobby = new ArrayList<>();
        for (int i = 0; i < partida.getJugadores().size(); i++) {
            Jugador j = partida.getJugadores().get(i);
            listaLobby.add(new dtos.JugadorLobbyDTO(j.getNombre(), (i == 0), j.isEstaListo()));
        }

        dtos.EstadoLobbyDTO estadoLobby = new dtos.EstadoLobbyDTO(partida.getCodigoSala(), listaLobby);
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
}
