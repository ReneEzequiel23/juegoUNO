/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author ReneEzequiel23 & EdgarAcevedoAcosta
 */
public class JugadorLobbyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private boolean esHost;
    private boolean estaListo;

    public JugadorLobbyDTO(String nombre, boolean esHost, boolean estaListo) {
        this.nombre = nombre;
        this.esHost = esHost;
        this.estaListo = estaListo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsHost() {
        return esHost;
    }

    public void setEsHost(boolean esHost) {
        this.esHost = esHost;
    }

    public boolean isEstaListo() {
        return estaListo;
    }

    public void setEstaListo(boolean estaListo) {
        this.estaListo = estaListo;
    }
    
    
}