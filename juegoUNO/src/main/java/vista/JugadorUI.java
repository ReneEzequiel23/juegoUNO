package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.Jugador;

public class JugadorUI extends JPanel {

    private final JLabel lblAvatar;
    private final JLabel lblNombre;
    private final JLabel lblCartas;

    public JugadorUI() {
        // Layout horizontal principal para separar avatar de info
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false); // Fondo transparente
        this.setBorder(new EmptyBorder(10, 10, 10, 10)); // Márgenes

        // JLabel para la imagen del avatar (pequeño)
        lblAvatar = new JLabel();
        lblAvatar.setPreferredSize(new Dimension(50, 50));
        // ... (Cargar un avatar por defecto después) ...

        // Panel vertical para nombre y cartas
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);

        lblNombre = new JLabel("Oponente");
        lblNombre.setForeground(Color.WHITE); // Texto blanco
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        lblCartas = new JLabel("Cartas: 0");
        lblCartas.setForeground(new Color(200, 200, 200)); // Gris claro
        lblCartas.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCartas.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        panelInfo.add(lblNombre);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio
        panelInfo.add(lblCartas);

        this.add(lblAvatar);
        this.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio
        this.add(panelInfo);
    }

    /**
     * Actualiza la información visual del oponente.
     */
    public void pintarOponente(Jugador oponente, boolean esSuTurno) {
        if (oponente != null) {
            this.setVisible(true);
            
            // Cargar el avatar del jugador (puedes añadir el avatar al modelo Jugador)
            // if (oponente.getAvatar() != null) {
            //     Image img = oponente.getAvatar().getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            //     lblAvatar.setIcon(new ImageIcon(img));
            // } else {
            //     // Cargar avatar genérico por defecto
            // }

            lblNombre.setText(oponente.getNombre());
            lblCartas.setText("Cartas: " + oponente.getMano().contarCartas());

            // Pintamos el panel de verde si es su turno para que resalte
            if (esSuTurno) {
                // ... aplicar un borde o fondo tenue verde ...
                // this.setBorder(new LineBorder(new Color(20, 200, 20), 2, true)); 
            } else {
                this.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen normal
            }

        } else {
            this.setVisible(false);
        }
    }
}