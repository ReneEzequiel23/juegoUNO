/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.ComandoJugadorDTO;
import dtos.EstadoLobbyDTO;
import dtos.TipoAccion;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import red.cliente.IClienteRed;

/**
 *
 * @author ReneEzequiel23
 */
public class LobbyVistaControlador implements IEventoListener {

    private final IEventBus eventBus;
    private final String nombreJugador;

    private vista.PantallaLobby vista;
    private boolean partidaIniciada = false;

    public LobbyVistaControlador(IEventBus eventBus, String nombreJugador) {
        this.eventBus = eventBus;
        this.nombreJugador = nombreJugador;

        // ¡El controlador se suscribe al nacer!
        this.eventBus.suscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        this.eventBus.suscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
        this.eventBus.suscribir(eventos.tipos.EventoNotificacion.TIPO, this);
        // En el método onEvent, escuchamos si tocan la puerta:

    }

    public void setVista(vista.PantallaLobby vista) {
        this.vista = vista;
    }

    // --- MÉTODOS DE SALIDA (LOS QUE YA TENÍAS) ---
    public void solicitarInicioPartida() {
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, TipoAccion.SOLICITAR_INICIO, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }

    public void cambiarEstadoListo(boolean estaListo) {
        TipoAccion accion = estaListo ? TipoAccion.MARCAR_LISTO : TipoAccion.DESMARCAR_LISTO;
        ComandoJugadorDTO comando = new ComandoJugadorDTO(nombreJugador, accion, null, null, null);
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }

    // --- MÉTODOS DE ENTRADA (MUDADOS DESDE LA VISTA) ---
    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoLobby) {
            EstadoLobbyDTO estado = ((eventos.tipos.EventoEstadoLobby) evento).getEstadoLobbyDTO();

            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    vista.actualizarInterfazLobby(estado);
                });
            }

            // ¡AQUÍ ESTÁ LA CORRECCIÓN! El if de Notificación ahora está afuera e independiente
        } else if (evento instanceof eventos.tipos.EventoNotificacion) {
            String msj = ((eventos.tipos.EventoNotificacion) evento).getMensaje();

            // Verificamos que sea una solicitud de entrada
            if (msj != null && msj.startsWith("SOLICITUD:")) {
                String jugadorPendiente = msj.split(":")[1]; // Sacamos el nombre (ej. "Rene")

                if (vista != null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        // Le ordenamos a la vista que muestre el panel emergente
                        vista.mostrarSolicitud(jugadorPendiente);
                    });
                }
            }

        } else if (evento instanceof eventos.tipos.EventoEstadoMesa) {
            if (partidaIniciada) {
                return; // El cerrojo
            }

            partidaIniciada = true;

            dtos.EstadoMesaDTO mesaInicial = ((eventos.tipos.EventoEstadoMesa) evento).getEstadoDTO();

            // 1. Preparamos el controlador de la siguiente pantalla
            PartidaVistaControlador partidaCtrl = new PartidaVistaControlador(this.eventBus, this.nombreJugador);

            // 2. Le decimos a la vista que haga el salto
            if (vista != null) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    vista.abrirPartidaYCerrar(partidaCtrl, mesaInicial);
                });
            }

            // 3. Nos desuscribimos para morir en paz
            destruir();
        }
    }

    // Método para cuando el Host da clic en Aceptar
    public void responderSolicitud(String jugadorPendiente, boolean aceptado) {
        if (aceptado) {
            // Mandamos el comando al servidor. Usamos el 3er parámetro (idCartaJugada) para llevar el nombre
            dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                    nombreJugador, dtos.TipoAccion.ACEPTAR_JUGADOR, jugadorPendiente, null, null
            );
            eventBus.publicar(new eventos.tipos.EventoComando(comando));
        }
    }

    public IEventBus getEventBus() {
        return eventBus;
    }

    private void destruir() {
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoLobby.TIPO, this);
        this.eventBus.desuscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }

    public void entrarAlLobby() {
        System.out.println("[Controlador] Intentando enviar comando ENTRAR_LOBBY a la red...");
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                nombreJugador,
                dtos.TipoAccion.ENTRAR_LOBBY,
                null, null, null
        );
        eventBus.publicar(new eventos.tipos.EventoComando(comando));
    }
}
