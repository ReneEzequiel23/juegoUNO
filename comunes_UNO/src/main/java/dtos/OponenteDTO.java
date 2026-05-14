package dtos;

import java.io.Serializable;

public class OponenteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idJugador;
    private String nombre;
    private int cantidadCartas;
    private boolean estadoUNO;
    private boolean esSuTurno;

    public OponenteDTO(String idJugador, String nombre, int cantidadCartas, boolean estadoUNO, boolean esSuTurno) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.cantidadCartas = cantidadCartas;
        this.estadoUNO = estadoUNO;
        this.esSuTurno = esSuTurno;
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

    public int getCantidadCartas() {
        return cantidadCartas;
    }

    public void setCantidadCartas(int cantidadCartas) {
        this.cantidadCartas = cantidadCartas;
    }

    public boolean isEstadoUNO() {
        return estadoUNO;
    }

    public void setEstadoUNO(boolean estadoUNO) {
        this.estadoUNO = estadoUNO;
    }

    public boolean isEsSuTurno() {
        return esSuTurno;
    }

    public void setEsSuTurno(boolean esSuTurno) {
        this.esSuTurno = esSuTurno;
    }

    
}