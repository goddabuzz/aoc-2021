import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.exp

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun result(result: Int, expected: Int) {
    println(result)
    check(result == expected)
}

/**
 * filter collection with the previous item and the current item. Only if there is a previous item.
 */
fun <T> List<T>.filterWithPrev(predicate: (prev: T, current: T) -> Boolean): List<T> {
    val elements = ArrayList<T>()
    var index = 0
    for (item in this) {
        if (index < size-1 && predicate(this[index], this[++index])) elements.add(this[index])
    }
    return elements;
}