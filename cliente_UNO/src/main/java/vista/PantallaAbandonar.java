package vista;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * CU Abandonar partida
 *
 * @author Jonathan
 */
public class PantallaAbandonar extends JFrame {


    private static final Color BG_COLOR = new Color(60, 63, 120);

    //referencia a la pantalla de partida para poder cerrarla si confirma
    private PantallaPartida pantallaPadre;

    public PantallaAbandonar(PantallaPartida pantallaPadre) {
        this.pantallaPadre = pantallaPadre;
        initComponents();
    }

    private void initComponents() {
        setTitle("Abandonar Partida");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(BG_COLOR);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(30, 60, 40, 60));


        JLabel lblTitulo = new JLabel("ABANDONAR PARTIDA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(new EmptyBorder(0, 0, 30, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);


        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(BG_COLOR);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));


        JPanel filaJugador = crearFila("Jugador:", "DefaultName");
        panelCentro.add(filaJugador);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 16)));


        JPanel filaAvatar = crearFila("Avatar:", "default");
        panelCentro.add(filaAvatar);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 30)));


        JLabel lblPregunta = new JLabel("¿Estas seguro que deseas abandonar?", SwingConstants.CENTER);
        lblPregunta.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblPregunta.setForeground(new Color(200, 200, 220));
        lblPregunta.setAlignmentX(CENTER_ALIGNMENT);
        panelCentro.add(lblPregunta);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelBotones.setBackground(BG_COLOR);
        panelBotones.setBorder(new EmptyBorder(30, 0, 0, 0));

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setPreferredSize(new Dimension(150, 45));
        btnAtras.setBackground(new Color(220, 80, 120));
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnAtras.setFocusPainted(false);
        btnAtras.setBorderPainted(false);
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAtras.addActionListener(e -> dispose()); // Solo cierra esta ventana

        JButton btnAceptar = new JButton("Confirmar");
        btnAceptar.setPreferredSize(new Dimension(150, 45));
        btnAceptar.setBackground(new Color(80, 200, 140));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAceptar.addActionListener(e -> confirmarAbandono());

        panelBotones.add(btnAtras);
        panelBotones.add(btnAceptar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    private JPanel crearFila(String etiqueta, String valor) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        fila.setBackground(BG_COLOR);

        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(new Color(200, 200, 220));

        JLabel val = new JLabel(valor);
        val.setFont(new Font("SansSerif", Font.BOLD, 16));
        val.setForeground(Color.WHITE);

        fila.add(lbl);
        fila.add(val);
        return fila;
    }

    private void confirmarAbandono() {
        //por ahora solo cierra ambas pantallas (sin validaciones)
        if (pantallaPadre != null) {
            pantallaPadre.dispose();
        }
        dispose();

        //aqui después ira:cliente.enviarComando(ABANDONAR)
        //y abrira menu principal o pantalla de inicio
        System.out.println("[PantallaAbandonar] Jugador confirmo abandono");
    }
}