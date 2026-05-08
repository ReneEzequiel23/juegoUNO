/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cliente_uno;

import vista.PantallaLobby;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class Cliente_UNO {

    public static void main(String[] args) {
        // 1. Creamos el bus local y el cliente
        eventos.IEventBus busLocal = eventos.EventBus.getInstance();
        red.cliente.ClienteUNO cliente = new red.cliente.ClienteUNO(busLocal);

// 2. Nos conectamos (Esto hace el handshake)
        cliente.conectar("localhost", 12345, "Rene");

// 3. ¡MUY IMPORTANTE! Arrancamos el hilo para que se quede escuchando
        new Thread(cliente).start();

// 4. Abrimos el Lobby pasándole las dependencias
        PantallaLobby lobby = new PantallaLobby(cliente, busLocal, "Rene");
        lobby.setVisible(true);
    }
}
