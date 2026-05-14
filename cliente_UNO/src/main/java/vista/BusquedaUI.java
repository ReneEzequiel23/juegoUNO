/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author edgar
 */
public class BusquedaUI extends JPanel{
    private String codigo;
    private JLabel lblNombreSala;
    private JLabel lblNumeroPersonas;
    public JButton btnSolicitar;
    private boolean existe;

    public BusquedaUI() {
        setPreferredSize(new Dimension(390, 56));
        setBackground(new java.awt.Color(51, 51, 51));
        JPanel panelInfo = new JPanel();
//        panelInfo.setOpaque(false);
        panelInfo.setBackground(new java.awt.Color(51, 51, 51));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        lblNombreSala = new JLabel("No existe esa Partida");
        lblNombreSala.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18));
        lblNombreSala.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroPersonas = new JLabel("");
        lblNumeroPersonas.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18));
        lblNumeroPersonas.setForeground(new java.awt.Color(255, 255, 255));
        btnSolicitar = new JButton("Solicictar Entrar");
        btnSolicitar.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        btnSolicitar.setBackground(new java.awt.Color(72,245,78));
        btnSolicitar.setForeground(new java.awt.Color(0, 0, 0));
        btnSolicitar.setVisible(false);
        panelInfo.add(lblNombreSala);
        panelInfo.add(lblNumeroPersonas);
        panelInfo.add(btnSolicitar);
        this.add(panelInfo);
        if(codigo==null){
//            setLayout(new FlowLayout(FlowLayout.CENTER));
            lblNombreSala.setText("Busca un Partido");
        }else{
            setLayout(new FlowLayout(FlowLayout.CENTER));
            lblNombreSala.setText("No existe esa Partida");
            add(lblNombreSala);
        }
    }
    
    public void actualizarInfo(String Codigo, int numeroParticipantes, boolean existe){
        lblNombreSala.setText(Codigo);
        lblNumeroPersonas.setText(String.valueOf(numeroParticipantes)+"/ 4");
        btnSolicitar.setVisible(true);
        
    }
    
    
    
    
}
