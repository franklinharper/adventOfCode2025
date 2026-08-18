import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.system.exitProcess

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = try {
    Path("src/$name.txt").readText().trim().lines()
} catch (_: java.nio.file.NoSuchFileException) {
    println("Input file 'src/$name.txt' was not found. Add it, then run again.")
    exitProcess(0)
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun <T> checkEquals(actual: T, expected: T, message: String = "") {
    check(actual == expected) { "Expected $expected, but found $actual. $message" }
}