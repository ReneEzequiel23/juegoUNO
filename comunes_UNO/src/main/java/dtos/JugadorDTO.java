/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author edgar
 */
public class JugadorDTO implements Serializable{
    private String idJugador;
    private String nombre;
    private String avatar;

    /**
     * Podria verse cooo que es temporalmente 
     * @param idJugador identidicador
     * @param nombre nombre del jugador
     * @param avatar nombre del avatar
     */
    public JugadorDTO(String idJugador, String nombre, String avatar) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.avatar = avatar;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
}
