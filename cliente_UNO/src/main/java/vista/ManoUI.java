package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
// Importa la clase de utilidad que crearemos después
// import utilidades.ImageUtils; 

public class ManoUI extends JPanel {

    private PantallaPartida pantallaPadre;
    // Componentes para el avatar y nombre
    private JLabel lblAvatar;
    private JLabel lblNombre;

    public ManoUI() {
        // Layout principal para separar avatar de cartas
        this.setLayout(new BorderLayout());
        this.setOpaque(false); // Fondo transparente
        this.setBorder(new EmptyBorder(10, 20, 10, 20)); // Márgenes

        // --- PANEL DE AVATAR Y NOMBRE ---
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        panelInfo.setAlignmentY(java.awt.Component.BOTTOM_ALIGNMENT);
        this.setPreferredSize(new Dimension(800, 180));
        // JLabel para la imagen del avatar
        lblAvatar = new JLabel();
        lblAvatar.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        // ... (Cargar un avatar por defecto después) ...

        // JLabel para el nombre
        lblNombre = new JLabel("Tú");
        lblNombre.setForeground(Color.WHITE); // Texto blanco
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        panelInfo.add(lblAvatar);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio
        panelInfo.add(lblNombre);
        this.add(panelInfo, BorderLayout.WEST); // A la izquierda

        // --- PANEL DE CARTAS ---
        JPanel panelCartas = new JPanel(new FlowLayout(FlowLayout.LEFT, -15, 0)); // FlowLayout con solapamiento negativo
        panelCartas.setOpaque(false);
        this.add(panelCartas, BorderLayout.CENTER); // Al centro
    }

    public void setPantallaPadre(PantallaPartida pantallaPadre) {
        this.pantallaPadre = pantallaPadre;
    }

    // Método para cargar la información del jugador local (avatar y nombre)
    public void cargarDatosJugador(String nombre, ImageIcon avatar) {
        lblNombre.setText(nombre);
        // Escalar el avatar si es necesario
        if (avatar != null) {
            Image img = avatar.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblAvatar.setIcon(new ImageIcon(img));
        }
    }

    // Nuevo método adaptado para la Red
    public void pintarCartasDTO(java.util.List<dtos.CartaDTO> cartasDTO) {
        JPanel panelCartas = (JPanel) ((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        panelCartas.removeAll();

        for (dtos.CartaDTO carta : cartasDTO) {
            JLabel lblCarta = new JLabel();
            lblCarta.setPreferredSize(new Dimension(100, 120));
            lblCarta.setOpaque(true);
            lblCarta.setBackground(Color.WHITE);
            lblCarta.setForeground(Color.BLACK);
            lblCarta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

            // Usamos los datos simples del DTO para dibujar
            String textoHtml = "<html><center><b style='font-size:16px;'>" + carta.getValor() + "</b><br>" + carta.getColor() + "</center></html>";
            // ...
            lblCarta.setOpaque(true);
            // En lugar de Color.WHITE, usamos nuestro traductor:
            lblCarta.setBackground(obtenerColorAwt(carta.getColor()));

            // Y para que el texto resalte mejor si la carta es oscura:
            lblCarta.setForeground(carta.getColor().equals("AMARILLO") ? Color.BLACK : Color.WHITE);
            // ...
            lblCarta.setText(textoHtml);
            lblCarta.setHorizontalAlignment(SwingConstants.CENTER);

            lblCarta.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (pantallaPadre != null) {
                        // ¡CORRECCIÓN! Pasamos el ID y el Color
                        pantallaPadre.alHacerClicEnCarta(carta.getIdCarta(), carta.getColor());
                    }
                }
            });

            panelCartas.add(lblCarta);
        }

        this.revalidate();
        this.repaint();
    }

    private Color obtenerColorAwt(String colorDTO) {
        if (colorDTO == null) {
            return Color.WHITE;
        }

        switch (colorDTO.toUpperCase()) {
            case "ROJO":
                return new Color(255, 85, 85);     // Rojo UNO
            case "AZUL":
                return new Color(85, 85, 255);     // Azul UNO
            case "VERDE":
                return new Color(85, 170, 85);    // Verde UNO
            case "AMARILLO":
                return new Color(255, 170, 0); // Amarillo UNO
            case "NEGRO":
                return new Color(40, 40, 40);     // Negro Comodín
            default:
                return Color.WHITE;
        }
    }
}
