package utilidades;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelo.Carta;
import modelo.Comodin;
import modelo.Numerica;

public class ImageUtils {

    // Método principal para obtener la imagen de una carta
    public static ImageIcon getCardImage(Carta carta) {
        String nombreArchivo = "";
        
        if (carta instanceof Numerica) {
            Numerica num = (Numerica) carta;
            // Ejemplo de mapeo: rojo_5.png
            nombreArchivo = num.getColor().toString().toLowerCase() + "_" + num.obtenerValor() + ".png";
        } else if (carta instanceof Comodin) {
            Comodin com = (Comodin) carta;
            // Ejemplo de mapeo: +4.png o cambio_color.png
            nombreArchivo = com.obtenerAccion().toString().toLowerCase() + ".png";
        }
        
        return loadIcon(nombreArchivo);
    }

    public static ImageIcon getCardBackImage() {
        return loadIcon("parte_trasera.png");
    }

    // Método genérico para cargar un ImageIcon desde la carpeta de recursos
    public static ImageIcon loadIcon(String path) {
        try {
            // Usamos getResource para cargar desde el classpath (tu carpeta src/recursos)
            Image img = ImageIO.read(ImageUtils.class.getResource("/recursos/" + path));
            return new ImageIcon(img);
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.err.println("Error cargando imagen: " + path + ". Asegúrate de que existe en src/recursos.");
            // Opcional: devolver un icono de error
            return null;
        }
    }
}