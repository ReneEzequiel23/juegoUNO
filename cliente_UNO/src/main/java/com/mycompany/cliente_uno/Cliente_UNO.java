/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cliente_uno;

import control.LobbyVistaControlador;
import eventos.EventBus; // Asegúrate de que apunte a tu implementación de IEventBus
import eventos.IEventBus;
import javax.swing.JOptionPane;
import red.cliente.ClienteUNO;
import vista.PantallaLobby;

public class Cliente_UNO {

    public static void main(String[] args) {
        // 1. Pedimos el nombre para poder abrir varias instancias y probar
        String nombre = JOptionPane.showInputDialog(null, "Introduce tu nombre:", "Conexión UNO", JOptionPane.QUESTION_MESSAGE);

        if (nombre == null || nombre.trim().isEmpty()) {
            System.exit(0);
        }

        // 2. Inicializamos la infraestructura (Bajo Nivel)
        // Usamos el Bus Local para la comunicación interna entre hilos
        IEventBus busLocal = new EventBus();

        // Inicializamos el cliente de red (implementa IClienteRed)
        ClienteUNO clienteRed = new ClienteUNO(busLocal);

        // 3. Inicializamos el Controlador de la Vista (Cerebro)
        // Inyectamos la red y el bus al controlador
        LobbyVistaControlador lobbyCtrl = new LobbyVistaControlador(clienteRed, busLocal, nombre);

        // 4. Inicializamos la Vista pasándole el controlador y el nombre
        vista.PantallaLobby lobbyVista = new vista.PantallaLobby(lobbyCtrl, nombre);

        Thread hiloRed = new Thread(clienteRed);
        hiloRed.start();

        java.awt.EventQueue.invokeLater(() -> {
            lobbyVista.setVisible(true);
            lobbyVista.setLocationRelativeTo(null);

            // Automatización: Esperamos 300ms para que el Socket se estabilice 
            // y luego enviamos el DTO de entrada automáticamente.
            new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    System.out.println("[Main] Ejecutando saludo automático...");
                    lobbyCtrl.entrarAlLobby();
                }
            }, 300);
        });

        System.out.println("[Main] Cliente " + nombre + " iniciado y listo.");
    }
}
