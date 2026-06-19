import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Servidor {

    public Servidor() {

    }

    public void socketBuscaBin(Socket socket) {
        try (Socket s = socket;
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {
            int tamanho = dis.readInt();
            int alvo = dis.readInt();
            int[] vetor = new int[tamanho];
            for (int i = 0; i < tamanho; i++) {
                vetor[i] = dis.readInt();
            }

            Ordenacao ordenacao = new Ordenacao(tamanho, vetor);
            ordenacao.quickSort(0, tamanho - 1);

            Busca busca = new Busca(tamanho, ordenacao.getVetor(), alvo);
            int indice = busca.buscaBin();

            dos.writeInt(indice);
            for (int v : vetor) {
                dos.writeInt(v);
            }
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}