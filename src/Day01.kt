import kotlin.math.abs

// Day 1: Historian Hysteria
fun main() {
    // Separa o input em duas colunas
    fun identificaColunas(input: List<String>): Pair<List<Int>, List<Int>> {
        return input.map { linha ->
            val esquerda = linha.substringBefore(" ").toInt()
            val direita = linha.substringAfterLast(" ").toInt()

            esquerda to direita
        }.unzip()
    }

    fun resolveParteUm(input: List<String>): Int {
        val (primeiraColuna, segundaColuna) = identificaColunas(input)

        val resultado = primeiraColuna.sorted().zip(segundaColuna.sorted()).map { (esquerda, direita) ->
            abs(esquerda - direita)
        }.sum()

        return resultado
    }

    fun resolveParteDois(input: List<String>): Int {
        val (primeiraColuna, segundaColuna) = identificaColunas(input)

        val direita = segundaColuna.groupingBy { it }.eachCount()
        val resultado = primeiraColuna.sumOf { esquerda ->
            direita[esquerda]?.times(esquerda) ?: 0
        }

        return resultado
    }

    // Recebe os dados do input fornecido para o 1º dia.
    val input = readInput("Day01")

    // Resultados
    resolveParteUm(input).println()         // 3508942
    resolveParteDois(input).println()       // 26593248
}