/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import modelo.*;

/**
 *
 * @author Adrián
 */
public class ConfiguracionController {

    private final Partida partida;
    
    public ConfiguracionController(Partida partida){
        this.partida=partida;
    }

    public void cambiarNumeroJugadores(int jugadoresTotales) {
        int jugadoresActuales = jugadoresTotales;
        int jugadoresLista = partida.getJugadores().size();
    }

    public void cambiarTemporizador(int tiempoLimite) {
        int tiempoActual = tiempoLimite;
        
    }

    public void cambiarNumeroCartas(int numCartas) {
        int cartasActuales = numCartas;

    }

}
