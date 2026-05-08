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
        if(tiempoLimite==partida.getTurno().tiempoLimite){
            System.out.println("El tiempo limite es igual");
        }else{
            int tiempoPasado = partida.getTurno().getTiempoLimite();
            partida.getTurno().setTiempoLimite(tiempoLimite);
            System.out.println("El tiempo se ha actualizado exitosamente");
            System.out.println("Tiempo anterior: "+tiempoPasado+" Tiempo actual: "+tiempoLimite);
        }
    }

    public void cambiarNumeroCartas(int numCartas) {
        int cartasActuales = numCartas;
        //int cartasPartida = partida.getJugadores().get.
        // obtienes jugadores, y a cada
    }

    
}
