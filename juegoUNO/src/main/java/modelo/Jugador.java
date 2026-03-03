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
<<<<<<< Updated upstream
    public String id;
=======
    
    public Jugador(){
        
    }
    
    private Mano mano;
    public String nombre;
    public String avatar;
    public int puntaje;
    public boolean estadoUNO;

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
        /*
        Este constructor es para aquel jugador que apenas entro al juego o aun no esta en
        partida
        by Edgar
        */
        this.nombre = nombre;
        this.avatar = avatar;
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
    
    
>>>>>>> Stashed changes
}
