package dtos;

import java.io.Serializable;

public class CartaDTO implements Serializable {
    private String idCarta;
    private String color;  // ROJO, AZUL, VERDE, AMARILLO, NEGRO
    private String valor;  // "0"-"9", "TOMA2", "REVERSA", "SALTO", "CAMBIO_COLOR", "TOMA4"

    public CartaDTO(String idCarta, String color, String valor) {
        this.idCarta = idCarta;
        this.color = color;
        this.valor = valor;
    }

    public String getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(String idCarta) {
        this.idCarta = idCarta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}