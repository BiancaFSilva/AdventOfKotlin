// Day 8: Resonant Collinearity
fun main() {
    fun identificaSinal(input: List<String>): Map<Char, List<Pair<Int, Int>>> {
        val antenas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()

        input.forEachIndexed { linha, frequencia ->
            frequencia.trim().forEachIndexed { coluna, possivelAntinodo ->
                if (possivelAntinodo != '.') {
                    antenas.computeIfAbsent(possivelAntinodo) { mutableListOf() }.add(Pair(linha, coluna))
                }
            }
        }

        return antenas
    }

    fun resolveParteUm(input: List<String>): Int {
        val antenas = identificaSinal(input)
        val antinodos = mutableSetOf<Pair<Int, Int>>()

        antenas.values.forEach { posicao ->
            posicao.forEach { frequenciaInicial ->
                posicao.forEach { frequenciaAtual ->
                    if (frequenciaInicial != frequenciaAtual) {
                        val diferencaDuracaoSinal = frequenciaInicial.first - frequenciaAtual.first
                        val diferenciaValorSinal = frequenciaInicial.second - frequenciaAtual.second
                        val novaDuracaoSinal = frequenciaAtual.first - diferencaDuracaoSinal
                        val novoSinal = frequenciaAtual.second - diferenciaValorSinal

                        antinodos.add(Pair(novaDuracaoSinal, novoSinal))
                    }
                }
            }
        }

        val resultado = antinodos.count { (duracaoSinal, sinal) ->
            duracaoSinal in input.indices && sinal in input[duracaoSinal].trim().indices }

        return resultado
    }

    fun resolveParteDois(input: List<String>): Int {
        val antenas = identificaSinal(input)
        val antinodos = mutableSetOf<Pair<Int, Int>>()

        fun sinalValido(frequencia: Pair<Int, Int>): Boolean {
            return frequencia.first in input.indices && frequencia.second in input[frequencia.first].trim().indices
        }

        // Calculate antinodes based on antenna positions
        antenas.values.forEach { posicao ->
            posicao.forEach { frequenciaInicial ->
                posicao.forEach { frequenciaAtual ->
                    if (frequenciaInicial != frequenciaAtual) {
                        val diferencaDuracaoSinal = frequenciaInicial.first - frequenciaAtual.first
                        val diferenciaValorSinal = frequenciaInicial.second - frequenciaAtual.second
                        var novaDuracaoSinal = frequenciaAtual.first
                        var novoSinal = frequenciaAtual.second

                        while (sinalValido(Pair(novaDuracaoSinal, novoSinal))) {
                            antinodos.add(Pair(novaDuracaoSinal, novoSinal))
                            novaDuracaoSinal -= diferencaDuracaoSinal
                            novoSinal -= diferenciaValorSinal
                        }
                    }
                }
            }
        }

        val resultado = antinodos.size

        return resultado
    }


    // Recebe os dados do input fornecido para o 8º dia.
    val input = readInput("Day08")

    // Resultados
    println(resolveParteUm(input))         // 289
    println(resolveParteDois(input))       // 1030
}