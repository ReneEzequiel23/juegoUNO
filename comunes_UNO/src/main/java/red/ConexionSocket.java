package red;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexionSocket {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // Para el Cliente (abre la conexión)
    public void conectar(String ip, int puerto) throws IOException {
        vincularSocketExistente(new Socket(ip, puerto));
    }

    // Para el Servidor (usa un socket ya aceptado)
    public void vincularSocketExistente(Socket socket) throws IOException {
        this.socket = socket;
        // Regla de Oro: Out -> Flush -> In
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.out.flush();
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void enviarObjeto(Object mensaje) throws IOException {
        if (out != null) {
            out.writeObject(mensaje);
            out.reset(); // Importante para que el estado de los DTOs se refresque
            out.flush();
        }
    }

    public Object recibirObjeto() throws IOException, ClassNotFoundException {
        return (in != null) ? in.readObject() : null;
    }

    public void desconectar() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) { }
    }
}