package vista;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Jugador;

public class JugadorUI extends JPanel {

    private final JLabel lblNombre;
    private final JLabel lblCartas;
    private final JLabel lblEstado; // Para mostrar si gritó UNO o si es su turno

    public JugadorUI() {
        // Usamos BoxLayout vertical para que los textos salgan uno debajo de otro
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(120, 80)); // Tamaño sugerido
        this.setBackground(Color.LIGHT_GRAY);

        // Inicializamos las etiquetas
        lblNombre = new JLabel("Oponente");
        lblCartas = new JLabel("Cartas: 0");
        lblEstado = new JLabel("");

        // Añadimos las etiquetas al panel
        this.add(lblNombre);
        this.add(lblCartas);
        this.add(lblEstado);
    }

    /**
     * Actualiza la información visual del oponente.
     */
    public void pintarOponente(Jugador oponente, boolean esSuTurno) {
        if (oponente != null) {
            this.setVisible(true);
            lblNombre.setText("👤 " + oponente.getNombre());
            
            // Verificamos cuántas cartas tiene
            lblCartas.setText("Cartas: " + oponente.getMano().contarCartas());

            String estado = "";
            
            // Pintamos el panel de verde si es su turno para que resalte
            if (esSuTurno) {
                estado += "▶ ¡Su turno! ";
                this.setBackground(new Color(200, 255, 200)); 
            } else {
                this.setBackground(Color.LIGHT_GRAY);
            }

            // Si el oponente está protegido con UNO, lo mostramos
            if (oponente.isEstadoUNO()) {
                estado += "[¡UNO!]";
            }

            lblEstado.setText(estado);
        } else {
            // Si le pasamos un nulo, ocultamos el panel (útil para partidas de menos jugadores)
            this.setVisible(false);
        }
    }
}