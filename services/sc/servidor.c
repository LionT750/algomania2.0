#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <stdio.h>

#include "algoritmos.h"

#define PORT 8001

void para_big(tamanho, vetor);
void para_little(tamanho, vetor);

int main()
{
    struct sockaddr_in servidor;
    socklen_t addrlen = sizeof(servidor);
    int opt = 1;
    int sock = socket(AF_INET, SOCK_STREAM, 0);

    setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    servidor.sin_family = AF_INET;
    servidor.sin_addr.s_addr = INADDR_ANY;
    servidor.sin_port = htons(PORT);

    bind(sock, (struct sockaddr *)&servidor, sizeof(servidor));

    listen(sock, 2);

    while (1)
    {
        int novo_sock = accept(sock, (struct sockaddr *)&servidor, &addrlen);
        int tamanho = 0;
        int alvo = 0;
        int indice = -1;

        recv(novo_sock, &tamanho, sizeof(int), 0);
        recv(novo_sock, &alvo, sizeof(int), 0);

        tamanho = ntohl(tamanho);
        alvo = ntohl(alvo);

        int *vetor = malloc(tamanho * sizeof(int));

        /*The MSG_WAITALL doesnt guarantee full receive of data, signals, errors, and different data types 
        can cause it to return before all data is received.
        If bugs occur again, we must properly wrap this function to ensure that all data is received before proceeding.
        recv(novo_sock, vetor, tamanho * sizeof(int), MSG_WAITALL); */

        para_little(tamanho, vetor);

        quicksort(vetor, 0, tamanho - 1);
        indice = busca_bin(vetor, tamanho, alvo);


        indice = htonl(indice);
        para_big(tamanho, vetor);

        send(novo_sock, &indice, 4, 0);
        send(novo_sock, vetor, tamanho * 4, 0);

        free(vetor);
        close(novo_sock);
    }
    close(sock);

    return 0;
}

void para_big(int tamanho, int *vetor)
{
    for (int i = 0; i < tamanho; i++)
        vetor[i] = htonl(vetor[i]);
}

void para_little(int tamanho, int *vetor)
{
    for (int i = 0; i < tamanho; i++)
        vetor[i] = ntohl(vetor[i]);
}