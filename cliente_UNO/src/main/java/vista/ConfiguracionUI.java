/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import dtos.OponenteDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

import javax.swing.BoxLayout;

import javax.swing.border.EmptyBorder;

/**
 *
 * @author Adrián
 */
public class ConfiguracionUI {

        
    private final JLabel blJugadores;
    private final JLabel blTemporizador;
    private final JLabel blComodines;
    private final JLabel blMazo;

    public ConfiguracionUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false); // Fondo transparente
        this.setBorder(new EmptyBorder(10, 10, 10, 10)); // Márgenes    }
    }
    
    
    
}
