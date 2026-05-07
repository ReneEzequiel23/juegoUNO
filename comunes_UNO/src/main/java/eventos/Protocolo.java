package eventos;

public class Protocolo {
    // Comandos (Cliente -> Servidor)
    public static final String SOLICITAR_INICIO = "SOLICITAR_INICIO";
    public static final String JUGAR_CARTA = "JUGAR_CARTA";
    public static final String ROBAR_CARTA = "ROBAR_CARTA";
    
    // Actualizaciones (Servidor -> Cliente)
    public static final String PARTIDA_INICIADA = "PARTIDA_INICIADA";
    public static final String ESTADO_ACTUALIZADO = "ESTADO_ACTUALIZADO";
}