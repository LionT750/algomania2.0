public class Ordenacao {
    private int tamanho;
    private int[] vetor;

    public Ordenacao(int tamanho, int[] vetor) {
        this.tamanho = tamanho;
        this.vetor = vetor;
    }
    
    public Ordenacao() {
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
    
    public void quickSort(int lim1, int lim2) {
      if (lim1 >= lim2) {
        return;
      }
      int i = lim1;
      int j = lim2;
      int pivot = vetor[(i + j) / 2];
    
      while (i <= j) {
          while (vetor[i] < pivot) {
              i++;
          }
          while (vetor[j] > pivot) {
              j--;
          }
          if (i <= j) {
              int temp = vetor[i];
              vetor[i] = vetor[j];
              vetor[j] = temp;

              i++;
              j--;
          }
      }
      quickSort(lim1, j);
      quickSort(i, lim2);
    }
}