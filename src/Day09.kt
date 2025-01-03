// Day 9: Disk Fragmenter
fun main() {
    fun resolveParteUm(input: List<String>): Long {
        val disco = mutableListOf<Int>()
        val linha = input.joinToString("").trim() + '0'

        for (indice in 0 until linha.length / 2) {
            disco.addAll(List(linha[indice * 2].digitToInt()) { indice })
            disco.addAll(List(linha[indice * 2 + 1].digitToInt()) { -1 })
        }

        var posicaoDisco = 0
        var tamanhoUsado = disco.size - 1

        fun verificaSoma(): Long? {
            if (posicaoDisco == tamanhoUsado) {
                return disco.take(posicaoDisco).mapIndexed { indice, bloco -> indice * bloco.toLong() }.sum()
            }

            return null
        }

        while (true) {
            while (disco[posicaoDisco] != -1) {
                posicaoDisco += 1
                verificaSoma()?.let { return it }
            }

            while (disco[tamanhoUsado] == -1) {
                tamanhoUsado -= 1
                verificaSoma()?.let { return it }
            }

            disco[posicaoDisco] = disco[tamanhoUsado]
            disco[tamanhoUsado] = -1
        }
    }

    fun resolveParteDois(input: List<String>): Long {
        val disco = mutableListOf<Int>()
        val linha = input.joinToString("").trim() + '0'
        val arquivos = mutableListOf<Pair<Int, Int>>()
        val espacosLivres = mutableListOf<MutableList<Int>>()

        for (indice in 0 until linha.length / 2) {
            val posicaoDisco = linha[indice * 2].digitToInt()
            val tamanhoUsado = linha[indice * 2 + 1].digitToInt()
            arquivos.add(Pair(disco.size, posicaoDisco))

            if (tamanhoUsado > 0) {
                espacosLivres.add(mutableListOf(disco.size + posicaoDisco, tamanhoUsado))
            }

            disco.addAll(List(posicaoDisco) { indice })
            disco.addAll(List(tamanhoUsado) { -1 })
        }

        for (arquivo in arquivos.asReversed()) {
            for (espacoLivre in espacosLivres) {
                if (espacoLivre[0] >= arquivo.first) {
                    break
                }

                if (arquivo.second <= espacoLivre[1]) {
                    for (indice in 0 until arquivo.second) {
                        disco[espacoLivre[0] + indice] = disco[arquivo.first + indice]
                        disco[arquivo.first + indice] = -1
                    }

                    espacoLivre[0] += arquivo.second
                    espacoLivre[1] -= arquivo.second

                    break
                }
            }
        }

        val resultado = disco.mapIndexed { indice, bloco -> if (bloco > 0) indice * bloco.toLong() else 0L }.sum()

        return resultado
    }


    // Recebe os dados do input fornecido para o 9º dia.
    val input = readInput("Day09").toMutableList()

    // Resultados
    resolveParteUm(input).println()         // 6421128769094
    resolveParteDois(input).println()       // 6448168620520
}