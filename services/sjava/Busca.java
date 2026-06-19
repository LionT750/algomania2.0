public class Busca {
    private int tamanho;
    private int[] vetor;
    private int alvo;
    
    public Busca(int tamanho, int[] vetor, int alvo) {
        this.tamanho = tamanho;
        this.vetor = vetor;
        this.alvo = alvo;
    }
    
    public Busca() {
    }

    public int getTamanho() {
        return tamanho;
    }
    
    public void setTamanho(int tamanho) {
      this.tamanho = tamanho;
    }
    
    public int[] getVetor() {
        return vetor;
    }
    
    public void setVetor(int[] vetor) {
      this.vetor = vetor;
    }
    
    public int getAlvo() {
        return alvo;
    }
    
    public void setAlvo(int alvo) {
      this.alvo = alvo;
    }
    
    public int buscaBin() {
        if ((vetor == null) || (tamanho < 0)) {
            return -1;
        }
        int lim1 = 0;
        int lim2 = tamanho - 1;

        if ((alvo < vetor[lim1]) || (alvo > vetor[lim2])) {
            return -1;
        }
        if (vetor[lim1] == alvo) {
            return lim1;
        }
        if (vetor[lim2] == alvo) {
            return lim2;
        }
        
        while (lim1 <= lim2) {
          int media = lim1 + (lim2 - lim1) / 2;
          if (vetor[media] == alvo) {
              return media;
          }
          else if (vetor[media] < alvo) {
              lim1 = media + 1;
          }
          else {
              lim2 = media - 1;
          }
        }
        return -1;
    }
}