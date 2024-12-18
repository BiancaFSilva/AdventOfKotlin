// Day 5: Print Queue
fun main() {
    fun resultado(input: List<String>, parteUm: Boolean): Int {
        val inputSegmentado = input.joinToString("\n").split("\n\n")

        val primeiraSecao = inputSegmentado[0].split("\n")          // Primeira parte do input
        val ordenacao = mutableMapOf<String, MutableList<String>>()            // Segunda parte do input

        for (regra in primeiraSecao) {
            val (item, sucessor) = regra.split("|")
            
            if (item in ordenacao) {
                ordenacao[item]!!.add(sucessor)
            } else {
                ordenacao[item] = mutableListOf(sucessor)
            }
        }

        val segundaSecao = inputSegmentado[1].split("\n").map { it.split(",") }

        fun ordenaSegundaSecao(atual: String, proximo: String): Int {
            if (atual == proximo) {
                return 0        // Já está na ordem
            }
            if (atual in ordenacao && proximo in ordenacao[atual]!!) {
                return -1       // O 'atual' é anterior ao 'proximo'
            }
            if (proximo in ordenacao && atual in ordenacao[proximo]!!) {
                return 1        // O 'atual' é sucessor do 'proximo'
            }

            return 0
        }

        fun verificaOrdem(listaOrdenada: List<String>): Boolean {
            return listaOrdenada == listaOrdenada.sortedWith(Comparator { atual, proximo -> ordenaSegundaSecao(atual, proximo) })
        }

        val resultado = if (parteUm) {
            segundaSecao.filter { verificaOrdem(it) }.map { it[it.size / 2] }
        } else {
            segundaSecao.filter { !verificaOrdem(it) }
                .map { it.sortedWith(Comparator { primeiroElemento, segundoElemento ->
                    ordenaSegundaSecao(primeiroElemento, segundoElemento) }) }
                .map { it[it.size / 2] }
        }

        return resultado.filter { it.all { numero -> numero.isDigit() } }.sumOf { it.toInt() }
    }

    fun resolveParteUm(input: List<String>): Int {
        return resultado(input, true)
    }

    fun resolveParteDois(input: List<String>): Int {
        return resultado(input, false)
    }


    // Recebe os dados do input fornecido para o 5º dia.
    val input = readInput("Day05")

    // Resultados
    resolveParteUm(input).println()         // 6267
    resolveParteDois(input).println()       // 5184
}