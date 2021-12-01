fun main() {
    val input = readInput("Day01")
    println(input.filterIndexed {
        index, s -> index != 0 && input[index - 1].toInt() < s.toInt()
    }.size)
}
