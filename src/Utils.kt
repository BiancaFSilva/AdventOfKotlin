import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Lê o arquivo '.txt' de input respectivo a cada teste.
 */
fun readInput(name: String) = Path("src/inputs/$name.txt").readText().trim().lines()

// Converte strings em hash de md5
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

// Simplifica a exibição do resultado
fun Any?.println() = println(this)
