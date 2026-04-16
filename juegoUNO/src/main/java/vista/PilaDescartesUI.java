package vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import modelo.Carta;
import modelo.Comodin;
import modelo.Numerica;

public class PilaDescartesUI extends JPanel {

    private final JButton btnCartaSuperior;

    public PilaDescartesUI() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        
        // Inicializamos un botón vacío que representará la carta en el centro
        btnCartaSuperior = new JButton("Mesa Vacía");
        btnCartaSuperior.setEnabled(false); // Deshabilitado para que no parezca clickeable
        
        this.add(btnCartaSuperior, BorderLayout.CENTER);
    }

    /**
     * Actualiza la imagen/texto de la carta central.
     */
    public void pintarCartaSuperior(Carta carta) {
        if (carta != null) {
            btnCartaSuperior.setText(obtenerTextoVisual(carta));
        } else {
            btnCartaSuperior.setText("Mesa Vacía");
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