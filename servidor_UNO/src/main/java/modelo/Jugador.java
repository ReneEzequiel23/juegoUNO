/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renee, edgar
 */
public class Jugador {
    private String id;
    private Mano mano;
    private String nombre;
    private String avatar;
    private int puntaje;
    private boolean estadoUNO;

    public Jugador(Mano mano, String nombre, String avatar, int puntaje, boolean estadoUNO) {
        this.mano = mano;
        this.nombre = nombre;
        this.avatar = avatar;
        this.puntaje = puntaje;
        this.estadoUNO = estadoUNO;
    }

    /**
     * Constructor para crear un jugador que aun no este en una partida
     * @param nombre El nombre del usuario
     * @param avatar el avattar del usuario
     */
    public Jugador(String nombre, String avatar) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.mano = new Mano();
        this.puntaje = 0;
        this.estadoUNO = false; // Por defecto nadie tiene el UNO
    }
    
    /**
     * El jugador gritó UNO exitosamente para protegerse.
     */
    public void marcarUNO() {
        this.estadoUNO = true;
    }
    
    /**
     * Se usa cuando el jugador roba cartas y deja de tener solo una.
     */
    public void quitarUNO() {
        this.estadoUNO = false;
    }

    public boolean isEstadoUNO() {
        return estadoUNO;
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

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setEstadoUNO(boolean estadoUNO) {
        this.estadoUNO = estadoUNO;
    }

    public Mano getMano() {
        return mano;
    }

    
    @Override
    public String toString() {
        return "Jugador{" + "mano=" + mano + ", nombre=" + nombre + ", avatar=" + avatar + ", puntaje=" + puntaje + ", estadoUNO=" + estadoUNO + '}';
    }
    

}
