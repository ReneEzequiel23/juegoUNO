/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author edgar
 */
public class ClienteUI extends JPanel{
    private final JLabel lblAvatar;
    private final JLabel lblNombre;

    /**
     * Contructor del panel, o para hacerla sencilla el mvc del cliente, cuando
     * sea necesario mostrar la informacion del jugador
     */
    public ClienteUI() {
        setPreferredSize(new Dimension(218, 70));
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        panelInfo.setBackground(new java.awt.Color(51, 51, 51));
        JPanel panelavatar = new JPanel();
        panelavatar.setLayout(new BoxLayout(panelavatar, BoxLayout.Y_AXIS));
        panelavatar.setOpaque(false);
        panelavatar.setBackground(new java.awt.Color(51, 51, 51));
//        panelavatar.setLayout(new BoxLayout(panelavatar, ));
        setBackground(new java.awt.Color(51, 51, 51));
        lblAvatar = new JLabel();
        //lblAvatar.setPreferredSize(new Dimension(50, 50));
        lblAvatar.setText("AVATAR");

        JLabel jLabel2 = new JLabel();
        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel2.setText("Nombre:");
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        JLabel jLabel3 = new JLabel();
        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24)); // NOI18N
        jLabel3.setText("Avatar:");
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        lblAvatar.setForeground(new java.awt.Color(255, 255, 255));
        panelavatar.add(jLabel3);
        panelavatar.add(Box.createRigidArea(new Dimension(0, 5)));
        panelavatar.add(lblAvatar);

        lblNombre = new JLabel();
        lblNombre.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        lblNombre.setText("JugadorEjemplo");
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        panelInfo.add(jLabel2);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio
        panelInfo.add(lblNombre);

        this.add(panelavatar);
        this.add(Box.createRigidArea(new Dimension(70, 0))); // Espacio
        this.add(panelInfo);

    }

    public void pintarJugador(String player, int i) {
        this.setVisible(true);
        if (player == null) {
            lblNombre.setText("Default Name");
            cargarImagenInicial();
        } else {
            lblNombre.setText(player);
            mostrarImagen(i);
        }
    }

    private void mostrarImagen(int i) {
        try {
            ImageIcon icon = new ImageIcon(imgs[i]);
            lblAvatar.setText("");
            Image imagen = icon.getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH);
            lblAvatar.setIcon(new ImageIcon(imagen));
        } catch (Exception e) {
            lblAvatar.setText("No se pudo cargar");
        }
    }

    private void cargarImagenInicial() {
        mostrarImagen(0);
    }

    private String[] imgs = {
        "src/recursos/articMonkeys.png", "src/recursos/monopoly.jpg", "src/recursos/pokebola.png", "src/recursos/solitario.png",
        "src/recursos/swords.jpg", "src/recursos/trebol.jpg", "src/recursos/visualStudio.png"};
}
