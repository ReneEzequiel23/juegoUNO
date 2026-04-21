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

public class PodioView extends JFrame {

    public PodioView() {
        // 1. Configuraciones básicas de la ventana
        this.setTitle("¡Fin del Juego - Marcador Final!");
        this.setSize(400, 400); // La hicimos un poco más alta para que quepan todos
        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

}
}