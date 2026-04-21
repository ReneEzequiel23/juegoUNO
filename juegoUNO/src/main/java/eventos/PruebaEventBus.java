package eventos;

import dtos.EstadoMesaDTO;
import eventos.tipos.EventoEstadoMesa;
import java.util.ArrayList;

public class PruebaEventBus {

    public static void main(String[] args) {
        // 1. Instanciamos nuestro EventBus central
        IEventBus miBus = new EventBus();

        // 2. Creamos un "Listener" simulando ser tu PantallaPartida
        IEventoListener pantallaSimulada = new IEventoListener() {
            @Override
            public void onEvent(IEvento evento) {
                if (evento.getTipoEvento().equals(EventoEstadoMesa.TIPO)) {
                    // ¡Cast (transformación) seguro porque sabemos qué evento es!
                    EventoEstadoMesa eventoReal = (EventoEstadoMesa) evento;
                    System.out.println("---- LA PANTALLA REACCIONA ----");
                    System.out.println("¡Oh! Llegó el color activo: " + eventoReal.getEstadoDTO().getColorActivo());
                    System.out.println("-------------------------------");
                }
            }
        };

        // 3. Suscribimos la pantalla al bus
        miBus.suscribir(EventoEstadoMesa.TIPO, pantallaSimulada);

        // --- PAUSA DRAMÁTICA ---
        System.out.println("El juego está esperando...");
        
        // 4. Simulamos que el Controlador del Servidor publica un evento
        // (Armamos un DTO falso rápido solo para la prueba)
        EstadoMesaDTO dtoFalso = new EstadoMesaDTO("Rene", new ArrayList<>(), null, "ROJO", new ArrayList<>(), null);
        EventoEstadoMesa eventoDisparado = new EventoEstadoMesa(dtoFalso);
        
        miBus.publicar(eventoDisparado);
    }
}