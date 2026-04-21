/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import control.PartidaControlador;
import java.awt.Color;
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
    private red.cliente.ClienteUNO cliente;

    // Identificador del jugador local (quien está viendo esta pantalla)
    private String idJugadorLocal;

    public PantallaPartida(red.cliente.ClienteUNO cliente, String idJugadorLocal) {
        this.cliente = cliente;
        this.idJugadorLocal = idJugadorLocal;

        // Método generado automáticamente por NetBeans para inicializar botones y paneles
        initComponents();
        manoUI1.setPantallaPadre(this);

//        // --- NUEVO: Cargar Avatar y Nombre de "Tú" ---
//        // Supongamos que traes el avatar en tu objeto Jugador
//        modelo.Jugador yo = controlador.obtenerJugador(idJugadorLocal);
//        if (yo != null) {
//            // ImageIcon avatar = yo.getAvatar(); // Añadir avatar al modelo Jugador
//            // manoUI1.cargarDatosJugador(yo.getNombre(), avatar);
//        }
//
//        this.partida.agregarObservador(this);
//        actualizar();
        this.getContentPane().setBackground(new Color(10, 15, 30));
    }

    // =========================================================================
    // 1. RECEPCIÓN DE ACCIONES DEL USUARIO (De la Vista al Controlador)
    // =========================================================================
    /**
     * Ejemplo de método que se llama cuando el usuario hace clic en una carta
     * de su mano.
     */
    public void alHacerClicEnCarta(String idCarta) {
        String colorElegidoString = null;
        
        // 1. Verificamos visualmente si la carta clicada es negra (Comodín)
        // NOTA: Como ya no tenemos el modelo completo, puedes deducir si es negra
        // si el idCarta contiene "TOMA4" o "CAMBIO_COLOR" (dependiendo de cómo los nombres).
        if (idCarta.contains("NEGRO") || idCarta.contains("TOMA4") || idCarta.contains("CAMBIO")) {
            modelo.Color colorElegido = mostrarDialogoColor();
            if (colorElegido == null) {
                return; // Canceló la selección
            }
            colorElegidoString = colorElegido.name();
        }

        // 2. Armamos el paquete DTO con la intención de jugar
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, 
                dtos.TipoAccion.JUGAR_CARTA, 
                idCarta, 
                colorElegidoString, 
                null
        );

        // 3. ¡Lo enviamos por el Socket al Servidor!
        cliente.enviarComando(comando);
        
        // Fíjate que AQUÍ NO ACTUALIZAMOS LA PANTALLA.
        // La carta no desaparece de la mano todavía. 
        // Esperaremos a que el servidor valide la jugada y nos mande el nuevo EstadoMesaDTO.
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
        jPanel1 = new javax.swing.JPanel();
        jugadorUI2 = new vista.JugadorUI();
        jugadorUI3 = new vista.JugadorUI();
        jugadorUI1 = new vista.JugadorUI();
        jPanel2 = new javax.swing.JPanel();
        pilaDescartesUI1 = new vista.PilaDescartesUI();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Robar = new javax.swing.JButton();
        UNO = new javax.swing.JButton();
        DenuncarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        manoUI1.setPreferredSize(new java.awt.Dimension(800, 180));

        jPanel1.setBackground(new java.awt.Color(10, 15, 30));
        jPanel1.add(jugadorUI2);
        jPanel1.add(jugadorUI3);
        jPanel1.add(jugadorUI1);

        jPanel2.setBackground(new java.awt.Color(10, 15, 30));
        jPanel2.add(pilaDescartesUI1);

        jPanel3.setBackground(new java.awt.Color(10, 15, 30));

        jButton1.setText("simularTurno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularOponenteActionPerformed(evt);
            }
        });

        Robar.setText("Robar");
        Robar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobarActionPerformed(evt);
            }
        });

        UNO.setText("UNO");
        UNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UNOActionPerformed(evt);
            }
        });

        DenuncarBtn.setText("Denunciar");
        DenuncarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DenuncarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Robar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DenuncarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UNO))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(Robar)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(UNO)
                            .addComponent(DenuncarBtn))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(manoUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manoUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RobarActionPerformed
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.ROBAR, null, null, null
        );
        cliente.enviarComando(comando);
    }//GEN-LAST:event_RobarActionPerformed

    private void btnSimularOponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularOponenteActionPerformed
        modelo.Jugador enTurno = partida.getTurno().getJugadorActual();
        if (!enTurno.getNombre().equals(idJugadorLocal)) {
            controlador.robarCartaEnTurno(enTurno);
        }
    }//GEN-LAST:event_btnSimularOponenteActionPerformed

    private void UNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UNOActionPerformed
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.GRITAR_UNO, null, null, null
        );
        cliente.enviarComando(comando);
    }//GEN-LAST:event_UNOActionPerformed

    private void DenuncarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DenuncarBtnActionPerformed
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.DENUNCIAR, null, null, null
        );
        cliente.enviarComando(comando);
    }//GEN-LAST:event_DenuncarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DenuncarBtn;
    private javax.swing.JButton Robar;
    private javax.swing.JButton UNO;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private vista.JugadorUI jugadorUI1;
    private vista.JugadorUI jugadorUI2;
    private vista.JugadorUI jugadorUI3;
    private vista.ManoUI manoUI1;
    private vista.PilaDescartesUI pilaDescartesUI1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        for (modelo.Jugador j : partida.getJugadores()) {
            if (j.getMano().contarCartas() == 0) {
                // 1.1 Mostramos un mensaje rápido
                javax.swing.JOptionPane.showMessageDialog(this,
                        "¡" + j.getNombre() + " se ha quedado sin cartas!",
                        "¡FIN DE LA PARTIDA!",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);

                // 1.2 Abrimos la pantalla del Podio
                new PodioView(partida).setVisible(true);

                // 1.3 Cerramos esta pantalla de juego (PantallaPartida)
                this.dispose();

                // 1.4 Detenemos el método para que no intente dibujar cartas que ya no importan
                return;
            }
        }
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
