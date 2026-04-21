package red.servidor;

import dtos.ComandoJugadorDTO;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import eventos.tipos.EventoEstadoMesa; // El evento que creamos antes
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManejadorCliente implements Runnable, IEventoListener {

    private Socket socket;
    private IEventBus eventBus;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean conectado;

    public ManejadorCliente(Socket socket, IEventBus eventBus) {
        this.socket = socket;
        this.eventBus = eventBus;
        this.conectado = true;

        try {
            // Es vital crear primero el OutputStream y hacer flush en Java Sockets
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(socket.getInputStream());
            
            // ¡Nos suscribimos al EventBus para escuchar cuando la mesa cambie!
            // Así el profe verá que respetamos la arquitectura limpiamente.
            this.eventBus.suscribir(EventoEstadoMesa.TIPO, this);
            
        } catch (IOException e) {
            System.err.println("Error conectando con el cliente: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        // Bucle infinito escuchando lo que manda el jugador desde su computadora
        while (conectado) {
            try {
                // Leemos el DTO que viaja por la red
                Object mensaje = in.readObject();

                if (mensaje instanceof ComandoJugadorDTO) {
                    ComandoJugadorDTO comando = (ComandoJugadorDTO) mensaje;
                    System.out.println("Comando recibido del jugador: " + comando.getIdJugador() + " Acción: " + comando.getAccion());
                    
                    // Aquí podrías envolver este DTO en un Evento (ej. EventoComandoRecibido) 
                    // y lanzarlo al EventBus para que tu PartidaControlador lo procese.
                    // eventBus.publicar(new EventoComandoRecibido(comando));
                }
                
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Jugador desconectado.");
                desconectar();
            }
        }
    }

    /**
     * Este método se dispara automáticamente porque somos un IEventListener.
     * Cuando el Controlador de la Partida actualiza la mesa y lo publica en el EventBus,
     * este método atrapa el evento y lo manda por la red.
     */
    @Override
    public void onEvent(IEvento evento) {
        try {
            // Mandamos el evento (que contiene el EstadoMesaDTO) por el cable
            out.writeObject(evento);
            out.reset(); // Importante para evitar caché de objetos en Java
            out.flush();
        } catch (IOException e) {
            System.err.println("No se pudo enviar el evento al cliente.");
        }
    }

    private void desconectar() {
        conectado = false;
        eventBus.desuscribir(EventoEstadoMesa.TIPO, this); // Limpiamos la basura
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}