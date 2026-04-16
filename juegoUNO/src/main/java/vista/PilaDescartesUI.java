package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.Carta;
import modelo.Comodin;
import modelo.Numerica;
// Importa la clase de utilidad que crearemos después
// import utilidades.ImageUtils; 

public class PilaDescartesUI extends JPanel {

    private JLabel lblCartaSuperior;
    private JLabel lblEstadoMesa;
    private JButton btnRobar; // Ahora es un atributo para personalizarlo

    public PilaDescartesUI() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        // --- CARTA CENTRAL GRANDE ---
        lblCartaSuperior = new JLabel();
        lblCartaSuperior.setHorizontalAlignment(SwingConstants.CENTER);
        // ... (Cargar una imagen de carta superior por defecto después) ...
        this.add(lblCartaSuperior, BorderLayout.CENTER);

        // --- PANEL DE ESTADO Y MAZO ---
        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));
        panelSur.setOpaque(false);
        this.setPreferredSize(new Dimension(300, 400));
        // Etiqueta de estado de la mesa ("Rojo 7 Activo")
        lblEstadoMesa = new JLabel("Mesa Vacía");
        lblEstadoMesa.setForeground(Color.WHITE); // Texto blanco
        lblEstadoMesa.setFont(new Font("Arial", Font.BOLD, 18));
        lblEstadoMesa.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        lblEstadoMesa.setBorder(new EmptyBorder(10, 0, 10, 0)); // Margen superior/inferior

        panelSur.add(lblEstadoMesa);

        // Panel para mazo de robar y botón
        JPanel panelMazo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelMazo.setOpaque(false);

        // Imagen del mazo (boca abajo)
        JLabel lblMazo = new JLabel();
        // TIP: Cargar la imagen de la parte trasera de la carta
        // lblMazo.setIcon(ImageUtils.getCardBackImage());
        // Temporal: simular con color y borde
        lblMazo.setPreferredSize(new Dimension(60, 90));
        lblMazo.setOpaque(true);
        lblMazo.setBackground(new Color(40, 40, 60)); // Azul oscuro
        lblMazo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        // Botón de Robar (más moderno, con icono '+')
        btnRobar = new JButton("+ Robar Carta");
        btnRobar.setFocusPainted(false); // Quitar el borde de foco
        btnRobar.setBackground(new Color(30, 120, 240)); // Azul brillante
        btnRobar.setForeground(Color.WHITE); // Texto blanco
        btnRobar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRobar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano

        panelMazo.add(lblMazo);
        panelMazo.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio
        panelMazo.add(btnRobar);
        
        panelSur.add(panelMazo);
        this.add(panelSur, BorderLayout.SOUTH);
    }

    /**
     * Actualiza la imagen/texto de la carta central y la etiqueta de estado.
     */
    public void pintarCartaSuperior(Carta carta) {
        if (carta != null) {
            // TIP: Cargar la imagen real de la carta y escalarla
            // ImageIcon icono = ImageUtils.getCardImage(carta);
            // Image img = icono.getImage().getScaledInstance(120, 180, Image.SCALE_SMOOTH);
            // lblCartaSuperior.setIcon(new ImageIcon(img));
            
            // Temporal: simular con color y texto grande
            lblCartaSuperior.setPreferredSize(new Dimension(120, 180));
            lblCartaSuperior.setOpaque(true);
            lblCartaSuperior.setBackground(Color.WHITE);
            lblCartaSuperior.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            lblCartaSuperior.setFont(new Font("Arial", Font.BOLD, 24));
            lblCartaSuperior.setText(obtenerTextoVisual(carta));
            
            // Actualizar etiqueta de estado
            String textoEstado = "";
            if (carta instanceof Numerica) {
                Numerica num = (Numerica) carta;
                textoEstado = num.getColor() + " " + num.obtenerValor() + " Activo";
            } else if (carta instanceof Comodin) {
                Comodin com = (Comodin) carta;
                // ... lógica para para color elegido si es NEGRO ...
                textoEstado = com.getColor() + " " + com.obtenerAccion() + " Activo";
            }
            lblEstadoMesa.setText(textoEstado);
            
        } else {
            lblCartaSuperior.setText("Mesa Vacía");
            lblEstadoMesa.setText("Mesa Vacía");
        }
    }
    
    private String obtenerTextoVisual(Carta carta) {
        if (carta instanceof Numerica) {
            return ((Numerica) carta).obtenerValor() + "\n" + carta.getColor();
        } else if (carta instanceof Comodin) {
            return ((Comodin) carta).obtenerAccion() + "\n" + carta.getColor();
        }
        return "Carta";
    }
}