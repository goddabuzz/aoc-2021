import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Reads lines from the given input txt file.
 */
fun readInput(year: Int, day: Int, test: Boolean = false): List<String> {
    val dayText = "Day" + ("$day".padStart(2, '0'))
    val testText = if (test) "_test" else ""

    return File(
        "src/main/kotlin/aoc$year",
        "${dayText.lowercase(Locale.getDefault())}/$dayText${testText}.txt").readLines()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun <T> result(result: T, expected: T) {
    println(result)
    check(result == expected)
}

fun resultLong(result: Long, expected: Long) {
    println(result)
    check(result == expected)
}