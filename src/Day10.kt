// Day 10: Hoof It
fun main() {
    val movimentacao = listOf(
        Pair(-1, 0),
        Pair(0, 1),
        Pair(1, 0),
        Pair(0, -1)
    )   // Cima, direita, baixo, esquerda

    fun buscaEmProfundidade(
        matriz: List<List<Int>>, linha: Int, coluna: Int, limiteDeTentativas: Int, antecessor: Int = -1, multiplosCaminhos: Boolean = false
    ): Any {
        if (linha !in matriz.indices || coluna !in matriz[linha].indices)
            return if (multiplosCaminhos) 0
                   else emptySet<Pair<Int, Int>>()

        val indice = matriz[linha][coluna]
        if (indice != antecessor + 1)
            return if (multiplosCaminhos) 0
                   else emptySet<Pair<Int, Int>>()

        if (indice == limiteDeTentativas)
            return if (multiplosCaminhos) 1
                   else setOf(Pair(linha, coluna))

        var resultado = if (multiplosCaminhos) 0 else emptySet<Pair<Int, Int>>()
        for (direction in movimentacao) {
            val novaLinha = linha + direction.first
            val novaColuna = coluna + direction.second

            resultado = if (multiplosCaminhos)
                resultado as Int + buscaEmProfundidade(matriz, novaLinha, novaColuna, limiteDeTentativas, indice, multiplosCaminhos) as Int
            else
                (resultado as Set<Pair<Int, Int>>).union(buscaEmProfundidade(matriz, novaLinha, novaColuna, limiteDeTentativas, indice, multiplosCaminhos) as Set<Pair<Int, Int>>)
        }

        return resultado
    }

    fun resolveParteUm(input: List<String>): Int {
        val matriz = input.map { it.map(Char::digitToInt) }
        var resultado = 0

        for (linha in matriz.indices) {
            for (coluna in matriz[linha].indices) {
                if (matriz[linha][coluna] == 0) {
                    val caminho = buscaEmProfundidade(matriz, linha, coluna, 9) as Set<Pair<Int, Int>>
                    resultado += caminho.size
                }
            }
        }

        return resultado
    }

    fun resolveParteDois(input: List<String>): Int {
        val matriz = input.map { it.map(Char::digitToInt) }
        var resultado = 0

        for (linha in matriz.indices) {
            for (culuna in matriz[linha].indices) {
                if (matriz[linha][culuna] == 0) {
                    val caminho = buscaEmProfundidade(matriz, linha, culuna, 9, multiplosCaminhos = true) as Int
                    resultado += caminho
                }
            }
        }

        return resultado
    }


    // Recebe os dados do input fornecido para o 10º dia.
    val input = readInput("Day10")

    // Resultados
    resolveParteUm(input).println()         // 816
    resolveParteDois(input).println()       // 1960
}