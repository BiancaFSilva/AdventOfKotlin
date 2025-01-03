import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Lê o arquivo '.txt' de input respectivo a cada teste.
 */
fun readInput(name: String) = Path("src/inputs/$name.txt").readText().trim().lines()

// Simplifica a exibição do resultado
fun Any?.println() = println(this)