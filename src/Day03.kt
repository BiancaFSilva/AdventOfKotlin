// Day 3: Mull It Over
fun main() {
    fun resolveParteUm(input: List<String>): Int {
        val mulRegex = Regex("mul\\((\\d+),(\\d+)\\)")
        
        val resultado = input.sumOf { linha ->
            mulRegex.findAll(linha).sumOf { multiplicacaoValida ->
                val (x, y) = multiplicacaoValida.destructured

                x.toInt() * y.toInt()
            }
        }

        return resultado
    }

    fun resolveParteDois(input: List<String>): Int {
        val mulRegex = Regex("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)")

        val resultado = input.flatMap { linha -> mulRegex.findAll(linha).toList() }
            .fold(Pair(0, true)) { (soma, permitido), multiplicacaoValida ->
                when (multiplicacaoValida.value) {
                    "don't()" -> soma to false
                    "do()" -> soma to true

                    else -> {
                        if (permitido && multiplicacaoValida.groupValues.size == 3) {
                            val (x, y) = multiplicacaoValida.destructured

                            soma + x.toInt() * y.toInt() to permitido
                        } else {
                            soma to permitido
                        }
                    }
                }
            }.first

        return resultado
    }

    // Recebe os dados do input fornecido para o 3º dia.
    val input = readInput("Day03")

    // Resultados
    resolveParteUm(input).println()         // 156388521
    resolveParteDois(input).println()       // 75920122
}