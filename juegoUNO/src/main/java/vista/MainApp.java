package vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

// Importamos lo que construimos en la rama codigo_Rene
import modelo.Jugador;
import modelo.Partida;
import control.PartidaControlador;
import vista.PantallaPartida;

public class MainApp {

    public static void main(String[] args) {
        
        // 1. PREPARAMOS EL MODELO (El Backend)
        System.out.println("1. Inicializando el modelo de la partida...");
        
        // Creamos los jugadores
        Jugador jugadorLocal = new Jugador("René", "avatar_rene.png");
        Jugador oponente1 = new Jugador("Edgar", "avatar_edgar.png");
        Jugador oponente2 = new Jugador("El Profe", "avatar_profe.png");

        List<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(jugadorLocal);
        listaJugadores.add(oponente1);

        // Creamos el Agregado Raíz y el Controlador
        Partida partida = new Partida(listaJugadores);
        PartidaControlador controlador = new PartidaControlador(partida);

        // ¡Importante! Iniciamos el juego para que se repartan las 7 cartas y se ponga la primera en la mesa
        partida.iniciarJuego();

        // 2. PREPARAMOS LA VISTA (El Frontend)
        System.out.println("2. Lanzando la interfaz gráfica...");
        
        // SwingUtilities.invokeLater asegura que la ventana se dibuje de forma segura
        SwingUtilities.invokeLater(() -> {
            
            // Creamos la pantalla, pasándole el controlador y tu ID para que sepa quién eres
            // Usamos el nombre del jugador como ID temporal
            PantallaPartida pantalla = new PantallaPartida(controlador,partida, jugadorLocal.getNombre());
//            PantallaPartida pantalla1 = new PantallaPartida(controlador,partida, oponente1.getNombre());
            
            // Configuraciones básicas de la ventana
            pantalla.setTitle("Juego UNO - Partida Local");
            pantalla.setLocationRelativeTo(null); // Para que aparezca centrada en tu monitor
            pantalla.setVisible(true);            // ¡Que se haga la luz!
            
//            pantalla1.setTitle("Juego UNO - Partida Local");
//            pantalla1.setLocationRelativeTo(null); // Para que aparezca centrada en tu monitor
//            pantalla1.setVisible(true);            // ¡Que se haga la luz!
            
        });
    }
}