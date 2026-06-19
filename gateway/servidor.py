import asyncio
import struct
import time

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel

class Busca(BaseModel):
  vetor: list[int]
  alvo: int
  linguagem: str

app = FastAPI()

app.add_middleware(
  CORSMiddleware,
  allow_origins=["*"],
  allow_credentials=["*"],
  allow_methods=["*"],
  allow_headers=["*"]
)

@app.post("/busca_bin")
async def binarios(req_front: Busca):
  vetor = req_front.vetor
  tamanho = len(vetor)
  alvo = req_front.alvo
  linguagem = req_front.linguagem

  if (linguagem == 'c'):
    porta = 8001
    host = 'servico-c'
  elif (linguagem == 'java'):
    porta = 8002
    host = 'servico-java'

  leia, escreva = await asyncio.open_connection(host, porta)
  escreva.write(struct.pack('!i', tamanho))
  await escreva.drain()
  escreva.write(struct.pack('!i', alvo))
  await escreva.drain()
  for v in vetor:
    escreva.write(struct.pack('!i', v))
  await escreva.drain()

  lim1 = time.time()

  cabecalho = await leia.readexactly(4) 
  indice = struct.unpack('!i', cabecalho)[0]
  
  res = await leia.readexactly(tamanho * 4)
  
  # como nao ha outro uso do vetor...
  vetor = list(struct.unpack(f'!{tamanho}i', res)) 

  lim2 = time.time()

  escreva.close()

  await escreva.wait_closed()

  milis = (lim2 - lim1) * 1000 

  payload = {
    "posicao": indice,
    "milis": milis,
    "vetor": vetor
  }

  return payload
