/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dtos.OponenteDTO;

/**
 *
 * @author edgar
 */
public class ClienteControl {
    private OponenteDTO jugador;

    public ClienteControl(OponenteDTO jugador) {
        this.jugador = jugador;
    }
    
    /**
     * Estas no las Implemente por que se van a entrar a el servidor 
     * @param cliente
     * @param idJugadorLocal
     */
    public void crearJugador(red.cliente.ClienteUNO cliente, String idJugadorLocal){
        // enviarComando
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.AGREGARJ, null, null, null
        );
        cliente.enviarComando(comando);
    }
    
    /**
     * Estas no las Implemente por que se van a entrar a el servidor 
     * @param cliente
     * @param idJugadorLocal
     */
    public void editarJugador(red.cliente.ClienteUNO cliente, String idJugadorLocal){
        dtos.ComandoJugadorDTO comando = new dtos.ComandoJugadorDTO(
                idJugadorLocal, dtos.TipoAccion.EDITARJ, null, null, null
        );
        cliente.enviarComando(comando);
    }
}
