/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cliente_uno;

import eventos.EventBus;
import red.cliente.ClienteUNO;
import vista.PantallaLobby;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class Cliente_UNO {

    public static void main(String[] args) {
        
        // 1. Pedimos un nombre dinámico para no tener duplicados
        String miNombre = javax.swing.JOptionPane.showInputDialog(
                null, 
                "Ingresa tu nombre de jugador:", 
                "Bienvenido a UNO", 
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        // Si el usuario cancela, cerramos
        if (miNombre == null || miNombre.trim().isEmpty()) {
            System.exit(0);
        }

        // 2. Inicializamos la red local
        eventos.IEventBus busLocal = EventBus.getInstance();
        ClienteUNO cliente = new ClienteUNO(busLocal);

        // 3. ¡EL TRUCO! Creamos la pantalla ANTES de conectar
        // Así la pantalla ya está suscrita al EventBus lista para escuchar
        PantallaLobby lobby = new PantallaLobby(cliente, busLocal, miNombre);

        // 4. Ahora sí, nos conectamos y arrancamos el hilo
        cliente.conectar("localhost", 12345, miNombre);
        new Thread(cliente).start();
        
        // ¡NUEVO! Le decimos al servidor "Ya entré, mándame la lista del Lobby actual"
        dtos.ComandoJugadorDTO comandoEntrar = new dtos.ComandoJugadorDTO(
                miNombre, dtos.TipoAccion.ENTRAR_LOBBY, null, null, null
        );
        cliente.enviarComando(comandoEntrar);

        // 5. Mostramos la pantalla
        lobby.setVisible(true);
    }
}
