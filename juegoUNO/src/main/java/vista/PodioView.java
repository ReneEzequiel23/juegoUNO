package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.Jugador;
import modelo.Partida;

public class PodioView extends JFrame {

    public PodioView(Partida partida) {
        // 1. Configuraciones básicas de la ventana
        this.setTitle("¡Fin del Juego - Marcador Final!");
        this.setSize(400, 400); // La hicimos un poco más alta para que quepan todos
        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // 2. Encontramos al ganador para el título grande
        Jugador ganador = null;
        for (Jugador j : partida.getJugadores()) {
            if (j.getMano().contarCartas() == 0) {
                ganador = j;
                break;
            }
        }

        // 3. Título Superior
        String textoTitulo = (ganador != null) ? " ¡" + ganador.getNombre() + " HA GANADO! " : "¡Fin del juego!";
        JLabel lblTitulo = new JLabel(textoTitulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(new EmptyBorder(20, 10, 20, 10)); // Márgenes para que no se vea apretado
        this.add(lblTitulo, BorderLayout.NORTH);

        // Creamos una copia de la lista para no alterar la partida original
        List<Jugador> listaOrdenada = new ArrayList<>(partida.getJugadores());
        
        // Usamos el método sort de Java comparando los puntajes
        listaOrdenada.sort((jugador1, jugador2) -> Integer.compare(jugador2.getPuntaje(), jugador1.getPuntaje()));

        // Creamos un panel que apile los elementos verticalmente (BoxLayout)
        JPanel panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBorder(new EmptyBorder(10, 50, 20, 50));

        int posicion = 1;
        for (Jugador j : listaOrdenada) {
            // Creamos el texto, por ejemplo: "1. René - 150 pts"
            String textoJugador = posicion + ". " + j.getNombre() + "  -  " + j.getPuntaje() + " pts";
            
            JLabel lblJugador = new JLabel(textoJugador);
            lblJugador.setFont(new Font("Arial", Font.PLAIN, 18));
            lblJugador.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT); // Centrar el texto
            
            // Añadimos el texto y un espacio en blanco debajo para separarlos
            panelLista.add(lblJugador);
            panelLista.add(Box.createRigidArea(new java.awt.Dimension(0, 15)));
            
            posicion++;
        }

        // Añadimos la lista al centro de la ventana
        this.add(panelLista, BorderLayout.CENTER);
    }
}