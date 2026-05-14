package dtos;

import java.io.Serializable;

public class CartaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String color; // Ej: "ROJO", "AZUL", "COMODIN"
    private String valor; // Ej: "7", "REVERSA", "TOMA_DOS"

    public CartaDTO(String id, String color, String valor) {
        this.id = id;
        this.color = color;
        this.valor = valor;
    }

    // Getters...
    public String getIdCarta() { return id; }
    public String getColor() { return color; }
    public String getValor() { return valor; }
}