package aoc2021.day16

import result
import java.math.BigInteger

fun main() {


    // D2FE28

    // D - 1101
    // 2 - 0010
    // F - 1111
    // E - 1110
    // 2 - 0010
    // 8 - 1000

    // 110 100 10111 11110 00101 000
    // 110100101111111000101000

    // 110 -> Header, first 3 bits
    // 100 -> PacketId

    // Next iterate
    // Every next 5 bits
    // 1 -> Group, continue
    // 0 -> Last 4 bits

    // Ignore 0's

   // result(solve("D2FE28"), 2021)
    println("---")
    result(solve("EE00D40C823060"), 16)

}
val VERSION = 0..2
val TYPE_ID = 3..5
val LENGTH_ID = 6..7
val L_15 = 8..21
val L_11 = 8..17

fun solve(input: String): Int {
    return parseMessage(input.map { BigInteger(it.toString(), 16).toString(2).padStart(4, "0"[0]) }.joinToString("")).toInt()
}

fun parseMessage(input: String): String {
    var message = ""

    val version = input.packet(VERSION).toInt()
    val type = input.packet(TYPE_ID, 16)

    println("Version: $version")
    println("Type Id: $type")

    if (type != "99") {
        var subInput = input.substring(TYPE_ID.last+1)
        val groups = mutableListOf<String>()

        do {
            groups.add(subInput.take(5))
            subInput = subInput.substring(5)
        } while (groups.last()[0].toString() == "1")

        message += msgToValue(groups.joinToString(""))
        println(message)

        if (subInput.length > 6) {
            message += parseMessage(subInput)
        }
    } else {
        val lengthId = input.packet(LENGTH_ID).toInt()
        println("LengthTypeId: $lengthId")

        val range = if (lengthId == 0) L_15 else L_11
        val length = input.packet(range, 10).toInt()
        println("subpacket length: $length")

        message += parseMessage(input.substring(range.last+1..(range.last + length)))
    }

    return message
}

fun msgToValue(input: String): String {
    return input.chunked(5)
        .filter { it.length == 5 }
        .joinToString("") { it.takeLast(4) }.toBigInteger(2).toString()
}

private fun String.packet(range: IntRange): BigInteger = this.substring(range).toBigInteger(2)

private fun String.packet(range: IntRange, radix: Int): String {
    return this.substring(range).toBigInteger(2).toString(radix)
}