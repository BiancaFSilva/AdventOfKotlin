// Day 4: Ceres Search
fun main() {
    // Possível movimentação para idenificar o padrão 'XMAS'
    val movimentacao = listOf(
        Pair(-1, -1),      // Diagonal superior esquerda
        Pair(-1, 0),       // Cima
        Pair(-1, 1),       // Diagonal superior direita
        Pair(0, -1),       // Esquerda
        Pair(0, 1),        // Direita
        Pair(1, 0),        // Baixo
        Pair(1, -1),       // Diagonal inferior esquerda
        Pair(1, 1)         // Diagonal inferior direita
    )

    fun pesquisaLetra(linha: Int, coluna: Int, matriz: List<String>, movimento: Pair<Int, Int>, letra: String): Boolean {
        if (letra.isEmpty()) {
            return true
        }
        if (linha < 0 || linha >= matriz.size) {
            return false
        }
        if (coluna < 0 || coluna >= matriz[linha].length) {
            return false
        }
        if (matriz[linha][coluna] != letra[0]) {
            return false
        }

        return pesquisaLetra(linha + movimento.first, coluna + movimento.second, matriz, movimento, letra.substring(1))
    }

    fun ocorrenciaDaCombinacaoXMAX(linha: Int, coluna: Int, matriz: List<String>): Int {
        var ocorrencias = 0

        for (direcao in movimentacao) {
            if (pesquisaLetra(linha, coluna, matriz, direcao, "XMAS")) {
                ocorrencias++
            }
        }

        return ocorrencias
    }

    fun resolveParteUm(input: List<String>): Int {
        var resultado = 0

        for (linha in input.indices) {
            for (coluna in input[linha].indices) {
                if (input[linha][coluna] == 'X') {
                    resultado += ocorrenciaDaCombinacaoXMAX(linha, coluna, input)
                }
            }
        }

        return resultado
    }

    fun resolveParteDois(input: List<String>): Int {
        fun mapeiaCaracter(linha: Int, coluna: Int, direcaoM: Pair<Int, Int>, direcaoX: Pair<Int, Int>, matriz: List<String>): Boolean {
            return pesquisaLetra(linha + direcaoM.first, coluna + direcaoM.second, matriz, direcaoM, "M") &&
                   pesquisaLetra(linha + direcaoX.first, coluna + direcaoX.second, matriz, direcaoX, "S")
        }

        fun identificaPadraoMAS(linha: Int, coluna: Int, matriz: List<String>): Int {
            if ((mapeiaCaracter(linha, coluna, Pair(-1, -1), Pair(1, 1), matriz) || mapeiaCaracter(linha, coluna, Pair(1, 1), Pair(-1, -1), matriz)) &&
                (mapeiaCaracter(linha, coluna, Pair(-1, 1), Pair(1, -1), matriz) || mapeiaCaracter(linha, coluna, Pair(1, -1), Pair(-1, 1), matriz))) {
                return 1
            }

            return 0
        }

        var resultado = 0

        for (linha in input.indices) {
            for (coluna in input[linha].indices) {
                if (input[linha][coluna] == 'A') {
                    resultado += identificaPadraoMAS(linha, coluna, input)
                }
            }
        }

        return resultado
    }


    // Recebe os dados do input fornecido para o 4º dia.
    val input = readInput("Day04")

    // Resultados
    resolveParteUm(input).println()         // 2536
    resolveParteDois(input).println()       // 1875
}