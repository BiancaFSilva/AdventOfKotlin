// Day 6: Guard Gallivant
fun main() {
    // Possível direção com base na posição
    val direcoes = mapOf(
        '^' to Pair('>', Pair(-1, 0)),      // Cima
        '>' to Pair('v', Pair(0, 1)),       // Direita
        'v' to Pair('<', Pair(1, 0)),       // Baixo
        '<' to Pair('^', Pair(0, -1))       // Esquerda
    )

    fun identificaPosicaoInicial(input: List<String>): Pair<Pair<Int, Int>, Char> {
        for (linha in input.indices) {
            for (coluna in input[linha].indices) {
                if (input[linha][coluna] in direcoes.keys) {
                    return Pair(Pair(linha, coluna), input[linha][coluna])
                }
            }
        }

        return Pair(Pair(0, 0), '>')
    }

    fun move(input: MutableList<String>, posicao: Pair<Int, Int>, direcao: Char): Pair<Pair<Int, Int>, Char> {
        var posicaoAtual = posicao
        var direcaoAtual = direcao

        while (posicaoAtual.first in input.indices && posicaoAtual.second in input[posicaoAtual.first].indices) {
            val linha = StringBuilder(input[posicaoAtual.first])

            linha[posicaoAtual.second] = 'X'
            input[posicaoAtual.first] = linha.toString()

            val (proximaDirecao, mudanca) = direcoes[direcaoAtual]!!
            val proximaLinha = posicaoAtual.first + mudanca.first
            val proximaColuna = posicaoAtual.second + mudanca.second

            if (proximaLinha !in input.indices || proximaColuna !in input[proximaLinha].indices) {
                posicaoAtual = Pair(proximaLinha, proximaColuna)
                continue
            }

            if (input[proximaLinha][proximaColuna] == '#') {
                direcaoAtual = proximaDirecao
                continue
            }

            posicaoAtual = Pair(proximaLinha, proximaColuna)
        }

        return Pair(posicaoAtual, direcaoAtual)
    }

    fun resolveParteUm(input: MutableList<String>): Int {
        val (posicaoInicial, direcaoInicial) = identificaPosicaoInicial(input)
        move(input, posicaoInicial, direcaoInicial)

        val resultado = input.sumBy { line -> line.count { it == 'X' } }

        return resultado
    }

    fun resolveParteDois(input: MutableList<String>): Int {
        val (posicaoInicial, direcaoInicial) = identificaPosicaoInicial(input)
        move(input, posicaoInicial, direcaoInicial)

        val obstaculos = mutableListOf<Pair<Int, Int>>()

        for (linha in input.indices) {
            for (coluna in input[linha].indices) {
                if (input[linha][coluna] == 'X') {
                    obstaculos.add(Pair(linha, coluna))
                }
            }
        }

        fun evitaObstaculos(map: List<String>, posicao: Pair<Int, Int>, direcao: Char, obstaculo: Pair<Int, Int>): Boolean {
            val posicaoVisitada = mutableSetOf<Triple<Int, Int, Char>>()

            var posicaoAtual = posicao
            var direcaoAtual = direcao

            while (posicaoAtual.first in map.indices && posicaoAtual.second in map[posicaoAtual.first].indices) {
                posicaoVisitada.add(Triple(posicaoAtual.first, posicaoAtual.second, direcaoAtual))

                val (proximaDirecao, mudanca) = direcoes[direcaoAtual]!!
                val proximaLinha = posicaoAtual.first + mudanca.first
                val proximaColuna = posicaoAtual.second + mudanca.second

                if (Triple(proximaLinha, proximaColuna, direcaoAtual) in posicaoVisitada) {
                    return true
                }

                if (proximaLinha !in map.indices || proximaColuna !in map[proximaLinha].indices) {
                    posicaoAtual = Pair(proximaLinha, proximaColuna)
                    continue
                }

                if (map[proximaLinha][proximaColuna] == '#' || Pair(proximaLinha, proximaColuna) == obstaculo) {
                    direcaoAtual = proximaDirecao
                    continue
                }

                posicaoAtual = Pair(proximaLinha, proximaColuna)
            }

            return false
        }

        var resultado = 0
        for (obstaculo in obstaculos) {
            if (evitaObstaculos(input, posicaoInicial, direcaoInicial, obstaculo)) {
                resultado++
            }
        }

        return resultado
    }


    // Recebe os dados do input fornecido para o 6º dia.
    val input = readInput("Day06").toMutableList()

    // Resultados
    resolveParteUm(input).println()         // 5242
    resolveParteDois(input).println()       // 1424
}