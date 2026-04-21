/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.cliente;

import dtos.ComandoJugadorDTO;
import eventos.IEventBus;
import eventos.IEvento;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class ClienteUNO implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    // Este bus es local (solo para la computadora del jugador).
    // Sirve para pasarle los mensajes de la red a la PantallaPartida sin romper el MVC.
    private IEventBus eventBusLocal; 
    private boolean conectado;

    // 1. El constructor ahora solo guarda el bus, ¡pero no se conecta todavía!
    public ClienteUNO(eventos.IEventBus eventBusLocal) {
        this.eventBusLocal = eventBusLocal;
        this.conectado = false;
    }

    // 2. Nuevo método para conectarnos cuando estemos listos
    // Modificamos el método para pedir el nombre
    public void conectar(String ipServidor, int puerto, String miNombre) {
        try {
            System.out.println("Intentando conectar al servidor en " + ipServidor + ":" + puerto + "...");
            this.socket = new Socket(ipServidor, puerto);
            
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(socket.getInputStream());
            
            // --- ¡NUEVO! EL HANDSHAKE ---
            // Enviamos nuestro nombre como el primerísimo paquete de red
            this.out.writeObject(miNombre);
            this.out.flush();
            // ----------------------------
            
            this.conectado = true;
            System.out.println("¡Conectado al servidor de UNO con éxito!");
            
        } catch (IOException e) {
            System.err.println("No se pudo conectar al servidor. Error: " + e.getMessage());
            this.conectado = false;
        }
    }

    /**
     * Este método lo llamará tu PantallaPartida cuando hagas clic en una carta.
     */
    public void enviarComando(ComandoJugadorDTO comando) {
        if (conectado) {
            try {
                out.writeObject(comando);
                out.reset(); // Limpiar caché
                out.flush();
            } catch (IOException e) {
                System.err.println("Error al intentar enviar tu jugada al servidor.");
            }
        }
    }

    /**
     * Este hilo se ejecuta en segundo plano todo el tiempo, escuchando al servidor.
     */
    @Override
    public void run() {
        while (conectado) {
            try {
                // El cliente se queda pausado aquí hasta que el servidor mande algo
                Object mensaje = in.readObject();

                // Si el servidor nos mandó un evento (como el EstadoMesaDTO)...
                if (mensaje instanceof IEvento) {
                    IEvento evento = (IEvento) mensaje;
                    
                    System.out.println("[Cliente Red] ¡Recibimos una actualización del servidor! Notificando a la pantalla...");
                    
                    // ...lo lanzamos a nuestro EventBus Local.
                    // Como tu PantallaPartida estará suscrita a este bus, se actualizará sola.
                    eventBusLocal.publicar(evento); 
                }
                
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Se perdió la conexión con el servidor.");
                desconectar();
            }
        }
    }

    private void desconectar() {
        conectado = false;
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}