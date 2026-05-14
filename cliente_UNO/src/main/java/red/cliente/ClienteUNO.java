package red.cliente;

import dtos.ComandoJugadorDTO;
import eventos.IEventBus;
import eventos.IEvento;
import eventos.IEventoListener;
import eventos.tipos.EventoComando;
import red.ConexionSocket; // O comunes.red.ConexionSocket dependiendo de dónde lo dejaste

/**
 *
 * @author ReneEzequiel23
 */
// ¡CAMBIO 1! Quitamos IClienteRed e implementamos IEventoListener
public class ClienteUNO implements Runnable, IEventoListener {

    private final IEventBus busLocal;
    private final ConexionSocket conexion; 
    private boolean corriendo;

    public ClienteUNO(IEventBus busLocal) {
        this.busLocal = busLocal;
        this.conexion = new ConexionSocket();
        this.corriendo = true;
        
        // ¡CAMBIO 2! El cliente se suscribe para escuchar cualquier comando que salga de las vistas
        this.busLocal.suscribir(EventoComando.TIPO, this);
    }

    // ¡CAMBIO 3! En lugar de "enviarComando", reaccionamos cuando llega un evento al bus
    @Override
    public void onEvent(IEvento evento) {
        if (evento instanceof EventoComando) {
            ComandoJugadorDTO comando = ((EventoComando) evento).getComandoDTO();
            try {
                System.out.println("[ClienteUNO] EventoComando atrapado en el Bus. Enviando a la red: " + comando.getTipoAccion());
                conexion.enviarObjeto(comando); 
            } catch (Exception e) {
                System.err.println("[ERROR - ClienteUNO] Falló al enviar comando por red:");
                e.printStackTrace();
            }
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
    
    public void enviarComando(ComandoJugadorDTO comando) {
        try {
            System.out.println("[ClienteUNO] Enviando comando a través de ConexionSocket: " + comando.getTipoAccion());
            conexion.enviarObjeto(comando); // Ocultamos el try/catch feo del writeObject
        } catch (Exception e) {
            System.err.println("[ERROR - ClienteUNO] Falló al enviar comando:");
            e.printStackTrace();
        }
    }
}