fun main() {

    val input = readInput("Day01").map { it.toInt() }
    val step1 = input.filterIndexed { index, s ->
        index != 0 && input[index - 1] < s
    }
    check(step1.size == 1342)
    println(step1.size)

    val test = readInput("Day01").map { it.toInt() }
    val step2 = test.mapIndexedNotNull { index, i ->
        if (index < test.size - 2) {
            i + test[index + 1] + test[index + 2]
        } else null
    }

    val step2Result = step2.filterIndexed { index, s ->
        index != 0 && step2[index - 1] < s
    }
    check(step2Result.size == 1378)
    println(step2Result.size)
}