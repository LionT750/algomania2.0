const END = "http://localhost:8000/busca_bin"

let selecionada

// in
const lings = document.getElementById("linguagens")
const form_alvo = document.getElementById("form-alvo")
const alvo = document.getElementById("alvo")

// out
const resultado = document.getElementById("resultado")
const v = document.getElementById("vetor")
const vetor_ord = document.getElementById("vetor-ord")
const indice = document.getElementById("indice")
const milis = document.getElementById("milis")

function vetor_rand() {
  const tamanho = 10 // alterar
  const vetor = []

  for (let i = 0; i < tamanho; i++) {
    vetor.push(Math.floor(Math.random() * 99) + 1) // de 0 a 99
  }

  return vetor
}

lings.addEventListener("click", evento => {
  const a = evento.target

  if (a && a.type === "radio") {
    selecionada = a.value
  }
})

form_alvo.addEventListener("submit", async evento => {
  evento.preventDefault()

  if (!selecionada) {
    return
  }

  const vetor_nord = vetor_rand()
  const valvo = parseInt(alvo.value, 10)
  v.textContent = JSON.stringify(vetor_nord)

  const payload = {
    vetor: vetor_nord,
    alvo: valvo,
    linguagem: selecionada
  }

  const res = await fetch(END, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(payload)
  })

  const dados = await res.json()

  vetor_ord.textContent = JSON.stringify(dados.vetor)
  indice.textContent = dados.posicao === -1 ? "não encontrado" : dados.posicao + 1
  milis.textContent = dados.milis.toFixed(2) + "ms"
})
