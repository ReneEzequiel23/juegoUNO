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
import modelo.Carta;
import modelo.Comodin;
import modelo.Numerica;
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

    public void pintarCartas(List<Carta> cartasEnMano) {
        // Obtenemos el panel de cartas
        JPanel panelCartas = (JPanel) ((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        panelCartas.removeAll(); // Limpiamos cartas anteriores

        // --- ETIQUETA "Tu Mano" ---
        JLabel lblTitulo = new JLabel("👤 Tu Mano");
        lblTitulo.setForeground(new Color(180, 180, 180)); // Gris claro
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        // ... añadir a la vista (quizás arriba) ...

        // 2. Creamos un elemento visual por cada carta
        for (Carta carta : cartasEnMano) {
            // TIP: Cargar la imagen real de la carta
            // ImageIcon icono = ImageUtils.getCardImage(carta); 
            // lblCarta.setIcon(icono);

            // Temporalmente, usaremos un JLabel con un borde y color para simular
            JLabel lblCarta = new JLabel();
            lblCarta.setPreferredSize(new Dimension(80, 120)); // Tamaño de carta
            lblCarta.setOpaque(true);
            lblCarta.setBackground(Color.WHITE); // Temporal
            lblCarta.setForeground(Color.BLACK);
            lblCarta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Esquinas redondeadas (Swing lo hace tosco, mejor imagen)
            lblCarta.setText(obtenerTextoVisual(carta)); // Temporal
            lblCarta.setHorizontalAlignment(SwingConstants.CENTER);
            
            // 3. Le agregamos el evento de clic (usando MouseListener en vez de ActionListener)
            lblCarta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (pantallaPadre != null) {
                        pantallaPadre.alHacerClicEnCarta(carta.getIdCarta());
                    }
                }
            });
            panelCartas.add(lblCarta);
        }

        this.revalidate();
        this.repaint();
    }
    
    // Método temporal para ponerle texto a las cartas antes de que pongas imágenes
    private String obtenerTextoVisual(Carta carta) {
        if (carta instanceof Numerica) {
            return ((Numerica) carta).obtenerValor() + " " + carta.getColor();
        } else if (carta instanceof Comodin) {
            return ((Comodin) carta).obtenerAccion() + " " + carta.getColor();
        }
        return "Carta";
    }
    
    // Nuevo método adaptado para la Red
    public void pintarCartasDTO(java.util.List<dtos.CartaDTO> cartasDTO) {
        JPanel panelCartas = (JPanel) ((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        panelCartas.removeAll(); 

        for (dtos.CartaDTO carta : cartasDTO) {
            JLabel lblCarta = new JLabel();
            lblCarta.setPreferredSize(new Dimension(80, 160));
            lblCarta.setOpaque(true);
            lblCarta.setBackground(Color.WHITE);
            lblCarta.setForeground(Color.BLACK);
            lblCarta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            
            // Usamos los datos simples del DTO para dibujar
            String textoHtml = "<html><center><b style='font-size:16px;'>" + carta.getValor() + "</b><br>" + carta.getColor() + "</center></html>";
            lblCarta.setText(textoHtml);
            lblCarta.setHorizontalAlignment(SwingConstants.CENTER);
            
            lblCarta.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (pantallaPadre != null) {
                        // Devolvemos el ID de la carta clicada
                        pantallaPadre.alHacerClicEnCarta(carta.getIdCarta());
                    }
                }
            });

            panelCartas.add(lblCarta);
        }

        this.revalidate();
        this.repaint();
    }
}