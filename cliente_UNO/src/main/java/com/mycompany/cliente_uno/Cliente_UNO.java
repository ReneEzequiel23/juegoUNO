/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cliente_uno;

import vista.PantallaAbandonar;
import javax.swing.SwingUtilities;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */



public class Cliente_UNO {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        SwingUtilities.invokeLater(() -> {

            PantallaAbandonar pantalla = new PantallaAbandonar(null);
            pantalla.setVisible(true);

        });
    }
}
