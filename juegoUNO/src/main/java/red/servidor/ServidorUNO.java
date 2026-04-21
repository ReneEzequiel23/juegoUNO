package red.servidor;

import eventos.EventBus;
import eventos.EventBus;
import eventos.IEventBus;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorUNO {

    private static final int PUERTO = 5050; // El puerto por donde entrarán los jugadores
    private IEventBus eventBusCentral;
    private List<ManejadorCliente> clientesConectados;
    private boolean ejecutando;

    public ServidorUNO() {
        this.eventBusCentral = new EventBus(); // ¡El corazón de tu DDD!
        this.clientesConectados = new ArrayList<>();
        this.ejecutando = true;
    }

    public void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("=== SERVIDOR UNO INICIADO EN EL PUERTO " + PUERTO + " ===");
            System.out.println("Esperando a que los jugadores se conecten...");

            // Bucle infinito esperando clientes
            while (ejecutando) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("¡Nuevo jugador conectado desde: " + socketCliente.getInetAddress() + "!");

                // Creamos un "Avatar" (Hilo) para este jugador dentro del servidor
                ManejadorCliente manejador = new ManejadorCliente(socketCliente, eventBusCentral);
                clientesConectados.add(manejador);
                
                // Lanzamos al manejador en su propio hilo para que no bloquee al servidor
                new Thread(manejador).start();
                
                // (Opcional) Aquí podrías lanzar una lógica que diga: 
                // Si clientesConectados.size() == 3, ¡Inicia la Partida!
            }
        } catch (IOException e) {
            System.err.println("Error fatal en el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ServidorUNO().iniciarServidor();
    }
}