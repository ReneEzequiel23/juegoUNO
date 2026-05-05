package vista;

import eventos.IEvento;
import java.awt.Color;
import java.util.List;
/**
 * @author rene
 */
public class PantallaPartida extends javax.swing.JFrame implements eventos.IEventoListener {

    private red.cliente.ClienteUNO cliente;
    private eventos.IEventBus busLocal;
    private String idJugadorLocal;

    public PantallaPartida(red.cliente.ClienteUNO cliente, eventos.IEventBus busLocal, String idJugadorLocal) {
        this.cliente = cliente;
        this.busLocal = busLocal;
        this.idJugadorLocal = idJugadorLocal;

        initComponents();
        manoUI1.setPantallaPadre(this);
        this.getContentPane().setBackground(new java.awt.Color(10, 15, 30));

        this.busLocal.suscribir(eventos.tipos.EventoEstadoMesa.TIPO, this);
    }

    public void alHacerClicEnCarta(String idCarta) {
        String colorElegidoString = null;

        if (idCarta.contains("NEGRO") || idCarta.contains("TOMA4") || idCarta.contains("CAMBIO")) {
            colorElegidoString = mostrarDialogoColor();
            if (colorElegidoString == null) return;
        }

        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal,
                dtos.TipoAccion.JUGAR_CARTA,
                idCarta,
                colorElegidoString,
                null
        );
        cliente.enviarComando(comando);
    }

    private String mostrarDialogoColor() {
        String[] opciones = {"ROJO", "AZUL", "VERDE", "AMARILLO"};
        int seleccion = javax.swing.JOptionPane.showOptionDialog(
                this,
                "Elige el nuevo color de la mesa:",
                "Carta Comodín jugada",
                javax.swing.JOptionPane.DEFAULT_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]
        );
        return seleccion >= 0 ? opciones[seleccion] : null;
    }

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
        AbandonarBtn = new javax.swing.JButton(); // ← NUEVO

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

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

        // ── BOTÓN ABANDONAR ──────────────────────────────────
        AbandonarBtn.setText("Abandonar");
        AbandonarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbandonarBtnActionPerformed(evt);
            }
        });
        // ─────────────────────────────────────────────────────

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Robar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AbandonarBtn)                          // ← NUEVO en layout
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
                                                        .addComponent(Robar)
                                                        .addComponent(AbandonarBtn)))           // ← NUEVO en layout
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
                                .addComponent(manoUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RobarActionPerformed(java.awt.event.ActionEvent evt) {
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.ROBAR, null, null, null
        );
        cliente.enviarComando(comando);
    }

    private void btnSimularOponenteActionPerformed(java.awt.event.ActionEvent evt) {
        // vacío por ahora
    }

    private void UNOActionPerformed(java.awt.event.ActionEvent evt) {
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.GRITAR_UNO, null, null, null
        );
        cliente.enviarComando(comando);
    }

    private void DenuncarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.DENUNCIAR, null, null, null
        );
        cliente.enviarComando(comando);
    }

    // ── MÉTODO NUEVO: abre la pantalla de confirmación ───────
    private void AbandonarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        vista.PantallaAbandonar pantallaAbandono = new vista.PantallaAbandonar(this);
        pantallaAbandono.setVisible(true);
    }
    // ─────────────────────────────────────────────────────────

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DenuncarBtn;
    private javax.swing.JButton Robar;
    private javax.swing.JButton UNO;
    private javax.swing.JButton AbandonarBtn; // ← NUEVO
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
    public void onEvent(eventos.IEvento evento) {
        if (evento instanceof eventos.tipos.EventoEstadoMesa) {
            dtos.EstadoMesaDTO estado = ((eventos.tipos.EventoEstadoMesa) evento).getEstadoDTO();
            javax.swing.SwingUtilities.invokeLater(() -> {
                actualizarInterfazConDTO(estado);
            });
        }
    }

    private void actualizarInterfazConDTO(dtos.EstadoMesaDTO estado) {
        if (estado.getIdGanador() != null && !estado.getIdGanador().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "¡El juego ha terminado! Ganador: " + estado.getIdGanador());
            this.dispose();
            return;
        }

        manoUI1.pintarCartasDTO(estado.getMiMano());
        pilaDescartesUI1.pintarCartaSuperiorDTO(estado.getCartaEnMesa(), estado.getColorActivo());

        java.util.List<dtos.OponenteDTO> oponentes = estado.getOponentes();
        if (oponentes.size() > 0) jugadorUI1.pintarOponenteDTO(oponentes.get(0));
        if (oponentes.size() > 1) jugadorUI2.pintarOponenteDTO(oponentes.get(1));
    }
}