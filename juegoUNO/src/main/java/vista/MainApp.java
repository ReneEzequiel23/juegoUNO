package vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

// Importamos lo que construimos en la rama codigo_Rene
import modelo.Jugador;
import modelo.Partida;
import control.PartidaControlador;
import vista.PantallaPartida;

// Ejemplo conceptual para tu MainApp.java (lado del jugador)
public class MainApp {
    public static void main(String[] args) {
        
        // 1. Creamos un mini EventBus solo para conectar la red con la pantalla
        eventos.IEventBus busLocal = new eventos.EventBus();
        
        // 2. Iniciamos la conexión al servidor (suponiendo que corre en tu misma PC por ahora)
        red.cliente.ClienteUNO cliente = new red.cliente.ClienteUNO("127.0.0.1", 5050, busLocal);
        
        // 3. Lanzamos al cliente en un hilo separado para que no congele la ventana de Java Swing
        new Thread(cliente).start();

        // 4. Lanzamos tu interfaz (Swing)
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Nota: Aquí tendrías que modificar ligeramente tu PantallaPartida para que 
            // en lugar de recibir el "PartidaControlador" local, reciba el "cliente" y el "busLocal".
            // PantallaPartida pantalla = new PantallaPartida(cliente, busLocal, "Rene");
            // pantalla.setVisible(true);
        });
    }
}