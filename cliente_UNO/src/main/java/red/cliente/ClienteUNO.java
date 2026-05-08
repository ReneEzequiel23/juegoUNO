package red.cliente;

import dtos.ComandoJugadorDTO;
import eventos.IEventBus;
import eventos.IEvento;
import red.ConexionSocket;
/**
 *
 * @author ReneEzequiel23
 */
public class ClienteUNO implements IClienteRed, Runnable {

    private final IEventBus busLocal;
    private final ConexionSocket conexion; // ¡Nuestra nueva herramienta!
    private boolean corriendo;

    public ClienteUNO(IEventBus busLocal) {
        this.busLocal = busLocal;
        this.conexion = new ConexionSocket();
        this.corriendo = true;
    }

    @Override
    public void enviarComando(ComandoJugadorDTO comando) {
        try {
            System.out.println("[ClienteUNO] Enviando comando a través de ConexionSocket: " + comando.getTipoAccion());
            conexion.enviarObjeto(comando); // Ocultamos el try/catch feo del writeObject
        } catch (Exception e) {
            System.err.println("[ERROR - ClienteUNO] Falló al enviar comando:");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("[ClienteUNO] Intentando conectar al servidor...");
            
            // Le delegamos el trabajo sucio a la clase de conexión
            conexion.conectar("localhost", 12345); 
            
            System.out.println("[ClienteUNO] ¡Conexión establecida y flujos listos!");

            while (corriendo) {
                // Leer se vuelve una sola línea limpia
                Object mensaje = conexion.recibirObjeto(); 
                
                System.out.println("[ClienteUNO] ¡Paquete recibido del servidor!: " + mensaje.getClass().getSimpleName());

                if (mensaje instanceof IEvento) {
                    System.out.println("[ClienteUNO] Es un IEvento. Publicando en el busLocal...");
                    busLocal.publicar((IEvento) mensaje);
                }
            }

        } catch (Exception e) {
            System.err.println("[ERROR - ClienteUNO] Se rompió la conexión con el servidor.");
            corriendo = false;
            conexion.desconectar();
        }
    }
}