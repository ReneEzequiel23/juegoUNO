package vista;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import modelo.Carta;
import modelo.Comodin;
import modelo.Numerica;

public class ManoUI extends JPanel {

    // Referencia a la pantalla principal para avisarle cuando hagamos clic
    private PantallaPartida pantallaPadre;

    public ManoUI() {
        // FlowLayout alinea los elementos uno al lado del otro, ideal para una mano de cartas
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // Opcional: Hacer el fondo transparente para que se vea el fondo de la mesa
        this.setOpaque(false); 
    }

    public void setPantallaPadre(PantallaPartida pantallaPadre) {
        this.pantallaPadre = pantallaPadre;
    }

    /**
     * Este método recibe la mano actual y "dibuja" las cartas en pantalla.
     */
    public void pintarCartas(List<Carta> cartasEnMano) {
        // 1. Limpiamos cualquier carta que se estuviera mostrando antes
        this.removeAll(); 

        // 2. Creamos un elemento visual por cada carta
        for (Carta carta : cartasEnMano) {
            
            // TIP: Para tu versión final, en lugar de texto usarías un JLabel con un ImageIcon (png)
            // Por ahora, para estructurar, usaremos un JButton con texto descriptivo
            JButton btnCarta = new JButton(obtenerTextoVisual(carta));
            
            // 3. Le agregamos el evento de clic
            btnCarta.addActionListener((ActionEvent e) -> {
                if (pantallaPadre != null) {
                    // Le avisamos a la pantalla principal qué carta se quiere jugar
                    pantallaPadre.alHacerClicEnCarta(carta.getIdCarta());
                }
            });

            // 4. Añadimos el botón al panel
            this.add(btnCarta);
        }

        // 5. Truco clave de Java Swing: Avisar que redibuje la pantalla porque cambiamos su contenido
        this.revalidate();
        this.repaint();
    }
    
    // Método temporal para ponerle texto a los botones antes de que pongas imágenes
    private String obtenerTextoVisual(Carta carta) {
        if (carta instanceof Numerica) {
            return ((Numerica) carta).obtenerValor() + " " + carta.getColor();
        } else if (carta instanceof Comodin) {
            return ((Comodin) carta).obtenerAccion() + " " + carta.getColor();
        }
        return "Carta";
    }
}