/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renee
 */
public class Jugador {
    
    public Jugador(){
        
    }
    
    private Mano mano;
    String nombre;
    String avatar;
    int puntaje;
    boolean estadoUNO;

    public Jugador(Mano mano, String nombre, String avatar, int puntaje, boolean estadoUNO) {
        this.mano = mano;
        this.nombre = nombre;
        this.avatar = avatar;
        this.puntaje = puntaje;
        this.estadoUNO = estadoUNO;
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

    public boolean isEstadoUNO() {
        return estadoUNO;
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
