import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 8002;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Servidor servidor = new Servidor();
            
            while (true) {
                Socket s = serverSocket.accept();
                new Thread(() -> servidor.socketBuscaBin(s)).start(); // nova thread para cada buscaBin.
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}