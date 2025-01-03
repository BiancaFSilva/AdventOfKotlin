// Day 7: Bridge Repair
fun main() {
    fun calibragem(calibragemDesejada: Long, valoresTeste: List<Long>, regra: (Long, Long) -> List<Long>): Boolean {
        if (valoresTeste[0] > calibragemDesejada) {
            return false
        }

        if (valoresTeste.size == 1) {
            return valoresTeste[0] == calibragemDesejada
        }

        for (novosValoresTeste in regra(valoresTeste[0], valoresTeste[1])) {
            if (calibragem(calibragemDesejada, listOf(novosValoresTeste) + valoresTeste.drop(2), regra)) {
                return true
            }
        }

        return false
    }

    fun resolveParteUm(input: List<String>): Long {
        var resultado: Long = 0
        for (linha in input) {
            val (valorDesejado, valoresTestados) = linha.split(":")
            val valoresTeste = valoresTestados.trim().split(" ").map { it.toLong() }
            val calibragemDesejada = valorDesejado.toLong()

            if (calibragem(calibragemDesejada, valoresTeste) { primeiroElemento, segundoElelemento ->
                listOf(primeiroElemento + segundoElelemento, primeiroElemento * segundoElelemento) }) {
                resultado += calibragemDesejada
            }
        }

        return resultado
    }

    fun resolveParteDois(input: List<String>): Long {
        var resultado: Long = 0
        for (linha in input) {
            val (valorDesejado, valoresTestados) = linha.split(":")
            val valoresTeste = valoresTestados.trim().split(" ").map { it.toLong() }
            val calibragemDesejada = valorDesejado.toLong()

            if (calibragem(calibragemDesejada, valoresTeste) { primeiroElemento, segundoElelemento ->
                listOf(primeiroElemento + segundoElelemento, primeiroElemento * segundoElelemento, (primeiroElemento.toString() + segundoElelemento.toString()).toLong()) }) {
                resultado += calibragemDesejada
            }
        }

        return resultado
    }


    // Recebe os dados do input fornecido para o 7º dia.
    val input = readInput("Day07")

    // Resultados
    resolveParteUm(input).println()         // 12839601725877
    resolveParteDois(input).println()       // 149956401519484
}