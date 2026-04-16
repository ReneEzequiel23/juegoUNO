/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import control.PartidaControlador;
import java.util.List;
import modelo.Carta;
import modelo.Partida;
import modelo.IObserver;

/**
 *
 * @author renee
 */
public class PantallaPartida extends javax.swing.JFrame implements IObserver {

    // El controlador que validará las acciones
    private PartidaControlador controlador;
    private Partida partida;

    // Identificador del jugador local (quien está viendo esta pantalla)
    private String idJugadorLocal;

    // Componentes visuales internos (según tu diagrama)
    // En NetBeans, estos podrían ser JPanels personalizados
    /*
    private ManoUI manoUI;
    private PilaDescarteUI pilaDescartesUI;
    private MazoUI mazoUI;
    private JugadorUI oponentesUI;
     */
    /**
     * Constructor de la pantalla.
     */
    public PantallaPartida(PartidaControlador controlador, Partida partida, String idJugadorLocal) {
        this.controlador = controlador;
        this.partida = partida;
        this.idJugadorLocal = idJugadorLocal;

        // Método generado automáticamente por NetBeans para inicializar botones y paneles
        initComponents();
        manoUI1.setPantallaPadre(this);

        this.partida.agregarObservador(this);
        // Configuraciones personalizadas y suscripción a eventos
        actualizar();
    }

    // =========================================================================
    // 1. RECEPCIÓN DE ACCIONES DEL USUARIO (De la Vista al Controlador)
    // =========================================================================
    /**
     * Ejemplo de método que se llama cuando el usuario hace clic en una carta
     * de su mano.
     */
    public void alHacerClicEnCarta(String idCarta) {
        // 1. Obtenemos tu jugador real
        modelo.Jugador yo = controlador.obtenerJugador(idJugadorLocal);
        
        if (yo != null) {
            // 2. Buscamos qué carta exactamente acabas de clickear
            modelo.Carta cartaClickeada = yo.getMano().obtenerCartaPorId(idCarta);
            
            if (cartaClickeada != null) {
                modelo.Color colorElegido = null;
                
                // 3. Verificamos si es una carta negra (Comodín)
                if (cartaClickeada.getColor() == modelo.Color.NEGRO) {
                    // Pausamos y mostramos el popup pidiendo el color
                    colorElegido = mostrarDialogoColor();
                    
                    // Si el usuario cerró la ventana sin elegir, abortamos la jugada
                    if (colorElegido == null) {
                        return; 
                    }
                }
                
                // 4. Ahora sí, le pasamos la jugada al controlador con el color correcto
                boolean jugadaValida = controlador.jugarCarta(yo, idCarta, colorElegido);
                
                // 5. Opcional: Feedback visual si te equivocas de carta
                if (!jugadaValida) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                            "Jugada inválida. Esa carta no coincide con el color ni el número de la mesa.", 
                            "Movimiento no permitido", 
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    /**
     * Acción del botón "¡UNO!" generado por NetBeans.
     */
    private void btnGritarUNOActionPerformed(java.awt.event.ActionEvent evt) {
        modelo.Jugador jugadorReal = controlador.obtenerJugador(idJugadorLocal);

        if (jugadorReal != null) {
            controlador.gritarUNO(jugadorReal);
        }
    }

    /**
     * Acción de hacer clic en el mazo para robar.
     */
    private void btnRobarCartaActionPerformed(java.awt.event.ActionEvent evt) {
        modelo.Jugador jugadorReal = controlador.obtenerJugador(idJugadorLocal);

        if (jugadorReal != null) {
            controlador.robarCartaEnTurno(jugadorReal);
        }
    }

    // =========================================================================
    // 2. ACTUALIZACIÓN VISUAL (De los Eventos a la Vista)
    // =========================================================================
    /**
     * Aquí suscribimos la pantalla al Bus de Eventos.
     */
    private void configurarEventos() {
        /*
        EventBus.suscribir("CARTA_JUGADA", evento -> {
            actualizarPilaDescartes(evento.getCarta());
            actualizarManoLocal();
        });

        EventBus.suscribir("TURNO_CAMBIADO", evento -> {
            actualizarIndicadorTurno(evento.getIdJugadorActual());
        });
        
        EventBus.suscribir("FIN_PARTIDA", evento -> {
            mostrarPantallaPodio();
        });
         */
    }

    /**
     * Método comodín para recargar toda la interfaz (útil al iniciar la
     * partida).
     */
    private void actualizarPantallaCompleta() {
        // manoUI.pintarCartas( controlador.obtenerMano(idJugadorLocal) );
        // pilaDescartesUI.pintarCartaSuperior( controlador.obtenerCartaEnMesa() );
        // mazoUI.actualizarCantidad( controlador.obtenerCartasRestantesMazo() );
    }

    /**
     * Método temporal para probar que los paneles dibujan las cartas
     * correctamente. MOCK = Datos simulados.
     */
    private void probarInterfaz() {
        System.out.println("Cargando cartas de prueba en la interfaz...");

        // 1. Fabricamos una mano de prueba con 4 cartas variadas
        List<Carta> cartasDePrueba = new java.util.ArrayList<>();
        cartasDePrueba.add(new modelo.Numerica("id-1", modelo.Color.ROJO, 5));
        cartasDePrueba.add(new modelo.Numerica("id-2", modelo.Color.AZUL, 9));
        cartasDePrueba.add(new modelo.Comodin("id-3", modelo.Color.AMARILLO, modelo.Accion.TOMA2));
        cartasDePrueba.add(new modelo.Comodin("id-4", modelo.Color.NEGRO, modelo.Accion.TOMA4));

        // 2. Fabricamos la carta que estará en el centro de la mesa
        Carta cartaEnMesa = new modelo.Numerica("id-5", modelo.Color.VERDE, 3);

        // 3. Le pasamos estos datos falsos a nuestros componentes visuales
        // Nota: Cambia 'manoUI1' y 'pilaDescarteUI1' por los nombres que NetBeans 
        // le haya puesto a tus paneles en el código auto-generado (puedes verlo en el Navegador de componentes).
        manoUI1.pintarCartas(cartasDePrueba);
        pilaDescartesUI1.pintarCartaSuperior(cartaEnMesa);
    }

    /**
     * Lee el estado actual del modelo y lo dibuja en la pantalla.
     */
    private void cargarDatosReales() {
        // 1. Buscamos tu jugador usando el ID
        modelo.Jugador yo = controlador.obtenerJugador(idJugadorLocal);

        // 2. Pintamos tus cartas (¡deberían ser 7 al inicio!)
        if (yo != null) {
            manoUI1.pintarCartas(yo.getMano().getCartas());
        }

        // 3. Pintamos la carta con la que inició el juego
        modelo.Carta cartaEnMesa = controlador.obtenerCartaEnMesa();
        pilaDescartesUI1.pintarCartaSuperior(cartaEnMesa);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        manoUI1 = new vista.ManoUI();
        pilaDescartesUI1 = new vista.PilaDescartesUI();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        pnlMenu.setBackground(new java.awt.Color(33, 33, 33));

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 858, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        manoUI1.setPreferredSize(new java.awt.Dimension(400, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(pilaDescartesUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(manoUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(pilaDescartesUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addComponent(manoUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.ManoUI manoUI1;
    private vista.PilaDescartesUI pilaDescartesUI1;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        // Este método se ejecutará SOLO, de forma automática, cada vez que 
        // el controlador llame a partida.notificarObservadores()
        System.out.println("La vista detectó un cambio en el Modelo. Redibujando...");

        modelo.Jugador yo = controlador.obtenerJugador(idJugadorLocal);
        if (yo != null) {
            manoUI1.pintarCartas(yo.getMano().getCartas());
        }

        modelo.Carta cartaEnMesa = controlador.obtenerCartaEnMesa();
        pilaDescartesUI1.pintarCartaSuperior(cartaEnMesa);
    }

    /**
     * Muestra una ventana emergente para que el jugador elija un color.
     *
     * @return El color elegido, o null si el usuario cerró la ventana.
     */
    private modelo.Color mostrarDialogoColor() {
        // Las opciones que aparecerán en los botones de la ventana
        String[] opciones = {"Rojo", "Azul", "Verde", "Amarillo"};

        int seleccion = javax.swing.JOptionPane.showOptionDialog(
                this,
                "¡Has jugado un Comodín! Elige el nuevo color de la mesa:",
                "Seleccionar Color",
                javax.swing.JOptionPane.DEFAULT_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0] // El rojo estará seleccionado por defecto
        );

        // Traducimos el botón que presionó al Enum de nuestro modelo
        switch (seleccion) {
            case 0:
                return modelo.Color.ROJO;
            case 1:
                return modelo.Color.AZUL;
            case 2:
                return modelo.Color.VERDE;
            case 3:
                return modelo.Color.AMARILLO;
            default:
                return null; // Si el usuario le dio a la 'X' para cerrar la ventana
        }
    }
}
