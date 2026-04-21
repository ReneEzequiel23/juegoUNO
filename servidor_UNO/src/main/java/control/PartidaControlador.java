/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import modelo.*;

/**
 *
 * @author renee, edgar
 */
public class PartidaControlador {
    /*Dividan los metodos por el tipo de clase que sea, y si se puede por el
    flujo normal
    Entran Flujos, validaciones basicas, metodos
    supongan que en las clases estan el metodo directo y aqui el que va a hacer una validacion 
    basica.
    By Edgar Acevedo
     */
  
    /*
    --------------- Lista de Control ------------------
    27 -- Flujo NOrmal
    etc
    */
    
    
    //Flujo Normal
    //Partida
    //Jugador
    //Mano
    //Turno
    //Mazo
    //Carta
    //PilaDescartes
    
    private final Partida partida;

    public PartidaControlador(Partida partida) {
        this.partida = partida;
    }

    public boolean validarJugada(Carta cartaAJugar) {
        // (El mismo código de validación que ya hicimos antes se mantiene aquí)
        if (cartaAJugar.getColor() == Color.NEGRO) return true;
        if (cartaAJugar.getColor() == partida.getColorActivo()) return true;
        Carta cartaEnMesa = partida.getPilaDescartes().obtenerCartaSuperior();
        if (cartaAJugar instanceof Numerica && cartaEnMesa instanceof Numerica) {
            return ((Numerica) cartaAJugar).obtenerValor() == ((Numerica) cartaEnMesa).obtenerValor();
        }
        if (cartaAJugar instanceof Comodin && cartaEnMesa instanceof Comodin) {
            return ((Comodin) cartaAJugar).obtenerAccion() == ((Comodin) cartaEnMesa).obtenerAccion();
        }
        return false;
    }

    public boolean jugarCarta(Jugador jugador, String idCarta, Color colorElegido) {
        // 1. Validamos que sea su turno y tenga la carta
        if (!partida.getTurno().getJugadorActual().equals(jugador)) return false;

        Carta cartaAJugar = jugador.getMano().obtenerCartaPorId(idCarta);
        if (cartaAJugar == null) return false;

        // 2. Validamos las reglas (Aquí el controlador checa el número y el Color Activo)
        if (validarJugada(cartaAJugar)) {
            
            // 3. Movemos la carta de la mano a la mesa
            jugador.getMano().eliminarCarta(idCarta);
            partida.getPilaDescartes().agregarCarta(cartaAJugar);
            
            // =========================================================
            // EL FIX: AQUÍ ACTUALIZAMOS EL CEREBRO DEL JUEGO
            // =========================================================
            if (cartaAJugar.getColor() == Color.NEGRO) {
                // Si es un +4 o Cambio de Color, toma el color del JOptionPane
                partida.actualizarColorActivo(colorElegido); 
            } else {
                // Si es una carta normal, el color activo cambia al color de esta carta
                partida.actualizarColorActivo(cartaAJugar.getColor()); 
            }
            // =========================================================

            // 4. Efectos (Saltos, Robar cartas)
            aplicarEfectoCarta(cartaAJugar);

            // 5. Verificamos si ganó
            if (verificarVictoria(jugador)) {
                calcularPuntajeVictoria(jugador);
                partida.notificarObservadores(); // Avisamos a la pantalla
                return true; 
            }

            // 6. Pasamos el turno y avisamos a la pantalla
            partida.avanzarTurno(); 
            partida.notificarObservadores(); 
            return true;
        }
        return false;
    }

    /**
     * Identifica si la carta es un Comodín y aplica sus reglas a la Partida.
     */
    private void aplicarEfectoCarta(Carta carta) {
        if (carta instanceof Comodin) {
            Comodin comodin = (Comodin) carta;
            
            switch (comodin.obtenerAccion()) {
                case REVERSA:
                    partida.getTurno().cambiarSentido();
                    break;
                case BLOQUEO:
                    // Le decimos a la partida que el siguiente jugador pierde su turno
                    partida.saltarSiguienteTurno();
                    break;
                case TOMA2:
                    penalizarSiguienteJugador(2);
                    partida.saltarSiguienteTurno(); // El que roba, pierde su turno
                    break;
                case TOMA4:
                    penalizarSiguienteJugador(4);
                    partida.saltarSiguienteTurno(); // El que roba, pierde su turno
                    break;
                case CAMBIOCOLOR:
                    // No hace nada extra a los turnos, el cambio de color ya se hizo en jugarCarta()
                    break;
            }
        }
    }

    /**
     * Hace que el jugador que sigue en la ronda robe X cantidad de cartas.
     */
    private void penalizarSiguienteJugador(int cantidadCartas) {
        Jugador victima = partida.obtenerSiguienteJugador();
        
        for (int i = 0; i < cantidadCartas; i++) {
            Carta castigo = partida.robarCartaSeguro(); // Usamos el método seguro
            
            if (castigo != null) {
                victima.getMano().agregarCarta(castigo);
                victima.quitarUNO(); // O jugador.quitarUNO()
            } else {
                // Si castigo es null aquí, significa que ni reciclando la pila alcanzó 
                // para completar el castigo (un caso rarísimo pero posible).
                System.out.println("No hay suficientes cartas para completar el castigo.");
                break; 
            }
        }
    }
    
    /**
     * Acción que se dispara cuando un jugador decide robar una carta del mazo en su turno.
     */
    public boolean robarCartaEnTurno(Jugador jugador) {
        if (!partida.getTurno().getJugadorActual().equals(jugador)) {
            return false;
        }

        Carta cartaRobada = partida.robarCartaSeguro();
        
        if (cartaRobada != null) {
            jugador.getMano().agregarCarta(cartaRobada);
            jugador.quitarUNO(); 
            partida.avanzarTurno();
            
            // ¡NOTIFICAMOS QUE ALGUIEN ROBÓ Y CAMBIÓ EL TURNO!
            partida.notificarObservadores();
            return true;
        }
        return false;
    }
    
    /**
     * El jugador presiona el botón de "¡UNO!".
     * Regla digital común: Puedes protegerte si tienes 2 cartas (a punto de tirar) o 1 carta.
     */
    public boolean gritarUNO(Jugador jugador) {
        int cantidadCartas = jugador.getMano().contarCartas();
        
        // Solo puede gritar UNO si está en peligro de quedarse con 1 carta o ya tiene 1
        if (cantidadCartas <= 2) {
            jugador.marcarUNO();
            System.out.println(jugador.getNombre() + " ha gritado ¡UNO!");
            
            // ¡NUEVO! Avisamos a la interfaz que el estado cambió
            partida.notificarObservadores(); 
            return true;
        }
        
        System.out.println("No puedes gritar UNO todavía.");
        return false;
    }

    /**
     * Alguien presiona el botón para denunciar que un jugador no dijo "UNO".
     */
    public boolean denunciarFaltaUNO(Jugador denunciante, Jugador acusado) {
        if (acusado.getMano().contarCartas() == 1 && !acusado.isEstadoUNO()) {
            
            for (int i = 0; i < 2; i++) {
                Carta castigo = partida.robarCartaSeguro();
                if (castigo != null) {
                    acusado.getMano().agregarCarta(castigo);
                }
            }
            
            // ¡NOTIFICAMOS EL CASTIGO!
            partida.notificarObservadores();
            return true;
        }
        return false;
    }
    
    /**
     * Verifica si un jugador se ha quedado sin cartas.
     */
    private boolean verificarVictoria(Jugador jugador) {
        return jugador.getMano().contarCartas() == 0;
    }

    /**
     * Calcula los puntos del ganador sumando el valor de las cartas 
     * restantes en las manos de los demás jugadores.
     */
    private void calcularPuntajeVictoria(Jugador ganador) {
        int puntosTotales = 0;

        for (Jugador oponente : partida.getJugadores()) {
            if (!oponente.equals(ganador)) {
                for (Carta carta : oponente.getMano().getCartas()) {
                    if (carta instanceof Numerica) {
                        puntosTotales += ((Numerica) carta).obtenerValor();
                    } else if (carta instanceof Comodin) {
                        Comodin comodin = (Comodin) carta;
                        // Cambio de color y +4 valen 50, las demás de acción valen 20
                        if (comodin.getColor() == Color.NEGRO) {
                            puntosTotales += 50;
                        } else {
                            puntosTotales += 20;
                        }
                    }
                }
            }
        }

        ganador.setPuntaje(ganador.getPuntaje() + puntosTotales);
        System.out.println("¡" + ganador.getNombre() + " gana la ronda y suma " + puntosTotales + " puntos!");
    }
    
    /**
     * Busca y devuelve el objeto Jugador basándose en su ID o nombre.
     */
    public Jugador obtenerJugador(String idJugador) {
        for (Jugador j : partida.getJugadores()) {
            // Asumiendo que por ahora usas el nombre como ID (como hicimos en el Main)
            if (j.getNombre().equals(idJugador)) { 
                return j;
            }
        }
        return null; // Si no lo encuentra
    }
    
    public Carta obtenerCartaEnMesa() {
        return partida.getPilaDescartes().obtenerCartaSuperior();
    }
    
    // Este método es el puente entre la Red y tus reglas de negocio (DDD)
    public void procesarComandoRed(dtos.ComandoJugadorDTO comando) {
        // Buscamos al jugador real en la memoria del servidor
        Jugador jugador = obtenerJugador(comando.getIdJugador());
        if (jugador == null) return;

        System.out.println("[Controlador] Ejecutando acción: " + comando.getTipoAccion() + " para " + jugador.getNombre());

        switch (comando.getTipoAccion()) {
            case JUGAR_CARTA:
                modelo.Color colorNuevo = null;
                if (comando.getColorElegido() != null) {
                    colorNuevo = modelo.Color.valueOf(comando.getColorElegido());
                }
                jugarCarta(jugador, comando.getIdCartaJugada(), colorNuevo);
                break;
                
            case ROBAR:
                robarCartaEnTurno(jugador);
                break;
                
            case GRITAR_UNO:
                gritarUNO(jugador);
                break;
                
            case DENUNCIAR:
                // Para simplificar, buscamos si alguien tiene 1 carta y lo castigamos
                for (Jugador oponente : partida.getJugadores()) {
                    if (!oponente.equals(jugador) && oponente.getMano().contarCartas() == 1 && !oponente.isEstadoUNO()) {
                        denunciarFaltaUNO(jugador, oponente);
                        break;
                    }
                }
                break;
        }
    }
}
