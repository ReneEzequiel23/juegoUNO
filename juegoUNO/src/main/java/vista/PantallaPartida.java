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

        manoUI1 = new vista.ManoUI();
        pilaDescartesUI1 = new vista.PilaDescartesUI();
        jugadorUI1 = new vista.JugadorUI();
        jugadorUI2 = new vista.JugadorUI();
        Robar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jugadorUI3 = new vista.JugadorUI();
        DenuncarBtn = new javax.swing.JButton();
        UNO = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        manoUI1.setPreferredSize(new java.awt.Dimension(400, 100));

        Robar.setText("Robar");
        Robar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobarActionPerformed(evt);
            }
        });

        jButton1.setText("simularTurno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularOponenteActionPerformed(evt);
            }
        });

        DenuncarBtn.setText("Denunciar");
        DenuncarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DenuncarBtnActionPerformed(evt);
            }
        });

        UNO.setText("UNO");
        UNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UNOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(pilaDescartesUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(manoUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Robar)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(UNO)
                                .addGap(18, 18, 18)
                                .addComponent(DenuncarBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugadorUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jugadorUI2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(153, 153, 153)
                                .addComponent(jugadorUI3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jugadorUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugadorUI2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugadorUI3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111)
                .addComponent(pilaDescartesUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(manoUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Robar)
                    .addComponent(jButton1)
                    .addComponent(DenuncarBtn)
                    .addComponent(UNO))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RobarActionPerformed
        modelo.Jugador jugadorReal = controlador.obtenerJugador(idJugadorLocal);

        if (jugadorReal != null) {
            controlador.robarCartaEnTurno(jugadorReal);
        }
    }//GEN-LAST:event_RobarActionPerformed

    private void btnSimularOponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularOponenteActionPerformed
       modelo.Jugador enTurno = partida.getTurno().getJugadorActual();
        if (!enTurno.getNombre().equals(idJugadorLocal)) {
            controlador.robarCartaEnTurno(enTurno);
        }
    }//GEN-LAST:event_btnSimularOponenteActionPerformed

    private void UNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UNOActionPerformed
        modelo.Jugador yo = controlador.obtenerJugador(idJugadorLocal);
        
        if (yo != null) {
            boolean exito = controlador.gritarUNO(yo);
            
            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                        "¡Te has protegido gritando UNO!", 
                        "Protección Activa", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                        "Aún tienes demasiadas cartas. No puedes gritar UNO.", 
                        "Aviso", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_UNOActionPerformed

    private void DenuncarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DenuncarBtnActionPerformed
        modelo.Jugador yo = controlador.obtenerJugador(idJugadorLocal);
        if (yo == null) return;

        modelo.Jugador culpable = null;

        // 1. Buscamos en toda la mesa si hay un tramposo
        // Usamos la variable 'partida' que guardamos en la pantalla
        for (modelo.Jugador oponente : partida.getJugadores()) {
            // Ignoramos nuestra propia mano
            if (!oponente.getNombre().equals(yo.getNombre())) {
                // ¿Tiene 1 carta y NO está protegido?
                if (oponente.getMano().contarCartas() == 1 && !oponente.isEstadoUNO()) {
                    culpable = oponente;
                    break;
                }
            }
        }

        // 2. Si encontramos al culpable, lo denunciamos
        if (culpable != null) {
            boolean castigado = controlador.denunciarFaltaUNO(yo, culpable);
            
            if (castigado) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                        "¡Atrapaste a " + culpable.getNombre() + " sin decir UNO!\nSe comerá 2 cartas.", 
                        "¡Denuncia Exitosa!", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // 3. Si todos están en regla o nadie tiene 1 carta
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "Nadie ha olvidado decir UNO... o te equivocaste de momento.", 
                    "Falsa Alarma", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_DenuncarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DenuncarBtn;
    private javax.swing.JButton Robar;
    private javax.swing.JButton UNO;
    private javax.swing.JButton jButton1;
    private vista.JugadorUI jugadorUI1;
    private vista.JugadorUI jugadorUI2;
    private vista.JugadorUI jugadorUI3;
    private vista.ManoUI manoUI1;
    private vista.PilaDescartesUI pilaDescartesUI1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        
        // Este método se ejecutará SOLO, de forma automática, cada vez que 
        // el controlador llame a partida.notificarObservadores()
        // 1. Sabemos quién tiene el turno actualmente
        modelo.Jugador jugadorEnTurno = partida.getTurno().getJugadorActual();

        // 2. Filtramos la lista para separar a los oponentes de ti
        java.util.List<modelo.Jugador> oponentes = new java.util.ArrayList<>();
        for (modelo.Jugador j : partida.getJugadores()) {
            if (!j.getNombre().equals(idJugadorLocal)) {
                oponentes.add(j);
            }
        }

        // 3. Pintamos el panel del primer oponente (Ej. Edgar)
        if (oponentes.size() > 0) {
            boolean esTurnoOp1 = oponentes.get(0).equals(jugadorEnTurno);
            jugadorUI1.pintarOponente(oponentes.get(0), esTurnoOp1);
        }
        
        // 4. Pintamos el panel del segundo oponente (Ej. El Profe)
        if (oponentes.size() > 1) {
            boolean esTurnoOp2 = oponentes.get(1).equals(jugadorEnTurno);
            jugadorUI2.pintarOponente(oponentes.get(1), esTurnoOp2);
        }
        
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
