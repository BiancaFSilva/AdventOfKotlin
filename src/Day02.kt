// Day 2: Red-Nosed Reports
fun main() {
    // Separa o input de acordo com os espaços
    fun identificaNivel(input: List<Int>): Boolean {
        val aumentaNivel = input.zipWithNext().all { (atual, proximo) -> proximo - atual in 1..3 }
        val diminuiNivel = input.zipWithNext().all { (atual, proximo) -> atual - proximo in 1..3 }

        return aumentaNivel || diminuiNivel
    }

    fun resolveParteUm(input: List<String>): Int {
        val resultado = input.count { relatorio ->
            val niveis = relatorio.split(" ").map { it.toInt() }
            
            identificaNivel(niveis)
        }

        return resultado
    }

    fun resolveParteDois(input: List<String>): Int {
        val resultado =  input.count { relatorio ->
            val niveis = relatorio.split(" ").map { it.toInt() }

            niveis.indices.any { i ->
                val niveisSeguros = niveis.filterIndexed { index, _ -> index != i }
                identificaNivel(niveisSeguros)
            }
        }

        return resultado
    }

    // Recebe os dados do input fornecido para o 2º dia.
    val input = readInput("Day02")

    // Resultados
    resolveParteUm(input).println()         // 510
    resolveParteDois(input).println()       // 553
}