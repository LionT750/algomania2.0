#include "algoritmos.h"

void quicksort(int* v, int lim1, int lim2)
{
  if (lim1 >= lim2)
    return;

  int i = lim1;
  int j = lim2;
  int pivot = v[(lim1 + lim2) / 2];

  while (i <= j)
  {
    while (v[i] < pivot)
      i++;
    while (v[j] > pivot)
      j--;

    if (i <= j)
    {
      int temp = v[i];
      v[i] = v[j];
      v[j] = temp;

      i++;
      j--;
    }
    
  }
  quicksort(v, lim1, j);
  quicksort(v, i, lim2);
}

int busca_bin(int *v, int tamanhov, int alvo)
{
	int lim1 = 0;
	int lim2 = tamanhov - 1;

	if ((alvo < v[lim1]) || (alvo > v[lim2]))
		return -1;
	if (v[lim1] == alvo)
		return lim1;
	if (v[lim2] == alvo)
		return lim2;
	
	while (lim1 <= lim2)
	{
		int media = lim1 + (lim2 - lim1) / 2;
		if (v[media] == alvo)
			return media;
		else if (v[media] < alvo)
			lim1 = media + 1;
		else
			lim2 = media - 1;
	}
	return -1;
}
