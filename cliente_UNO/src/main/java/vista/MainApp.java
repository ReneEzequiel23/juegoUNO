package vista;


// Ejemplo conceptual para tu MainApp.java (lado del jugador)
public class MainApp {
    public static void main(String[] args) {
        
        // 1. Preguntamos el nombre con un popup elegante de Swing
        String nombreElegido = javax.swing.JOptionPane.showInputDialog(
                null, 
                "Ingresa tu nombre para conectarte a la partida:", 
                "UNO - Conexión Multijugador", 
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        // Si el usuario cancela o no escribe nada, cerramos el programa
        if (nombreElegido == null || nombreElegido.trim().isEmpty()) {
            System.exit(0);
        }
        
        // Limpiamos espacios en blanco extra
        final String miNombre = nombreElegido.trim(); 

        eventos.IEventBus busLocal = new eventos.EventBus();
        red.cliente.ClienteUNO cliente = new red.cliente.ClienteUNO(busLocal);

        javax.swing.SwingUtilities.invokeLater(() -> {
            
            // Le pasamos el nombre real a la pantalla
            vista.PantallaPartida pantalla = new vista.PantallaPartida(cliente, busLocal, miNombre);
            pantalla.setTitle("UNO - Jugando como: " + miNombre); // Le ponemos el nombre arriba en la ventana
            pantalla.setVisible(true);

            new Thread(() -> {
                // Le pasamos el nombre real a la red
                cliente.conectar("127.0.0.1", 5050, miNombre);
                new Thread(cliente).start(); 
            }).start();
            
        });
    }
}