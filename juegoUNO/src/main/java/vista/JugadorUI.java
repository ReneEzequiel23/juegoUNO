package vista;

import dtos.OponenteDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
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
            lblCartas.setText("Cartas: " + oponente.getMano().contarCartas());

            // Pintamos un borde verde brillante y añadimos una flecha si es su turno
            if (esSuTurno) {
                this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(50, 205, 50), 3, true)); 
                lblNombre.setText("▶ " + oponente.getNombre()); // Flechita indicadora
            } else {
                this.setBorder(new javax.swing.border.EmptyBorder(10, 10, 10, 10)); // Margen normal sin borde
                lblNombre.setText(oponente.getNombre());
            }

        } else {
            this.setVisible(false);
        }
    }
    
    /**
     * Actualiza la vista del oponente con la información recibida del servidor.
     */
    public void pintarOponenteDTO(dtos.OponenteDTO oponente) {
        if (oponente != null) {
            this.setVisible(true);
            
            // 1. Gestionar el nombre y el indicador de turno
            if (oponente.isEsSuTurno()) {
                lblNombre.setText("▶ " + oponente.getNombre());
                // Ponemos un borde verde brillante si es su turno
                this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 205, 50), 3, true));
            } else {
                lblNombre.setText(oponente.getNombre());
                // Quitamos el borde si no es su turno
                this.setBorder(new javax.swing.border.EmptyBorder(10, 10, 10, 10));
            }

            // 2. Actualizar cantidad de cartas y si gritó ¡UNO!
            String infoCartas = "Cartas: " + oponente.getCantidadCartas();
            if (oponente.isEstadoUNO()) {
                infoCartas += " <b style='color:red;'>[¡UNO!]</b>";
                lblCartas.setText("<html>" + infoCartas + "</html>");
            } else {
                lblCartas.setText(infoCartas);
            }
            
            // Aquí podrías asignar el avatar si lo tienes en una carpeta de recursos
            // lblAvatar.setIcon(utilidades.ImageUtils.loadIcon(oponente.getNombre() + ".png"));

        } else {
            // Si no hay oponente en este slot, ocultamos el panel
            this.setVisible(false);
        }
        
        this.revalidate();
        this.repaint();
    }
}