package red.servidor;

import control.PartidaControlador;
import eventos.EventBus;
import eventos.IEventBus;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.IObserver;
import modelo.Jugador;
import modelo.Partida;

public class ServidorUNO {

    private IEventBus eventBusCentral;
    private PartidaControlador controladorCentral;
    private Partida partidaCentral;

    public ServidorUNO() {
        this.eventBusCentral = new EventBus();
        inicializarJuegoEnServidor();
    }

    private void inicializarJuegoEnServidor() {
        // 1. Creamos la partida (Temporalmente con 3 jugadores fijos para la prueba)
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Rene", "avatar_rene.png"));
        jugadores.add(new Jugador("Edgar", "avatar_edgar.png"));

        partidaCentral = new Partida(jugadores);
        controladorCentral = new PartidaControlador(partidaCentral);

        // 2. EL PUENTE CLAVE: El Modelo (Partida) avisa al EventBus cuando cambia
        partidaCentral.agregarObservador(new IObserver() {
            @Override
            public void actualizar() {
                // Cuando alguien tira una carta, gritamos "¡LA MESA CAMBIÓ!" al EventBus
                // Nota: Usamos un evento temporal simple que crearemos enseguida
                eventBusCentral.publicar(new eventos.tipos.EventoNotificacion("ACTUALIZAR_MESAS"));
            }
        });

        // Repartimos las cartas e iniciamos (esto disparará el primer 'actualizar')
        partidaCentral.iniciarJuego();
    }

    public void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            System.out.println("=== SERVIDOR INICIADO - JUEGO EN CURSO ===");
            while (true) {
                Socket socketCliente = serverSocket.accept();

                // ¡Ya no pasamos "Rene"! El manejador lo leerá de la red.
                ManejadorCliente manejador = new ManejadorCliente(socketCliente, eventBusCentral, controladorCentral, partidaCentral);
                new Thread(manejador).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServidorUNO().iniciarServidor();
    }
}
