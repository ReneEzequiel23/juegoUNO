package red.servidor;

import control.ConfiguracionController;
import control.DespachadorComandos;
import control.LobbyControlador;
import control.PartidaControlador;
import eventos.EventBus;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import modelo.Partida;
import red.servidor.ManejadorCliente;

public class ServidorUNO {

    public static void main(String[] args) throws ClassNotFoundException {
        int puerto = 12345;
        
        // 1. Inicializamos el cerebro del servidor (Solo 1 para todos)
        EventBus busGlobal = EventBus.getInstance();
        Partida partidaCentral = new Partida(new ArrayList<>());
        
        // Ensamblamos la arquitectura limpia
        PartidaControlador juegoCtrl = new PartidaControlador(partidaCentral);
        LobbyControlador lobbyCtrl = new LobbyControlador(partidaCentral, busGlobal);
        ConfiguracionController partiContr=new ConfiguracionController(partidaCentral, busGlobal);
        DespachadorComandos despachador = new DespachadorComandos(partidaCentral, lobbyCtrl, juegoCtrl, partiContr);

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            
            // 2. El ciclo infinito que acepta conexiones
            while (true) {
                System.out.println("Esperando nuevos jugadores...");
                
                // El programa se pausa en esta línea hasta que un ClienteUNO hace "new Socket()"
                Socket socketCliente = serverSocket.accept(); 
                
                System.out.println("¡Cliente detectado! IP: " + socketCliente.getInetAddress());

                // 3. Contratamos a un "Trabajador" para este cliente y lo lanzamos en su propio hilo
                ManejadorCliente trabajador = new ManejadorCliente(socketCliente, busGlobal, despachador, partidaCentral);
                new Thread(trabajador).start();
                
            }
            
        } catch (IOException e) {
            System.err.println("Error grave en el servidor: " + e.getMessage());
        }
    }
}