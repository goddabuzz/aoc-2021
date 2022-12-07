package aoc2022.day07

import readInput
import result

fun main() {

    val testInput = readInput(2022, 7, true)
    val input = readInput(2022, 7)
    result(part1(testInput), 95437)
    println(part1(input))

    result(part2(testInput), 24933642)
    println(part2(input))
}

data class File(val name: String, val size: Int, var parent: Directory? = null)

data class Directory(
    val name: String,
    var parent: Directory? = null,
    var sub: MutableList<Directory> = mutableListOf(),
    var files: MutableList<File> = mutableListOf()
) {
    fun addFile(file: File) {
        file.parent = this
        files.add(file)
    }

    fun addDir(dir: Directory) {
        sub.add(dir)
        dir.parent = this
    }
}

fun mapDirectories(input: List<String>): MutableList<Directory> {
    val root = Directory("/")
    val directories = mutableListOf(root)
    var current = root;

    // Map directories
    input.forEach {
        if (it.contains("$ cd /")) {
            current = root
        } else if (it.contains("$ cd ..")) {
            current = current.parent!!
        } else if (it.contains("$ cd")) {
            val dir = Directory(it.drop(5), current)
            directories.add(dir)
            current.addDir(dir)
            current = dir
        } else if (it.contains("$ ls")) {
            // ignore
        } else {
            // ls result
            if (!it.contains("dir")) {
                val (size, name) = it.split(" ")
                current.addFile(File(name, size.toInt(), current))
            }
        }
    }
    return directories
}

fun dirSize(dir: Directory): Int = dir.sub.sumOf { dirSize(it) } + dir.files.sumOf { it.size }

fun part1(input: List<String>): Int = mapDirectories(input)
    .map { dirSize(it) }
    .filter { it < 100000 }
    .sum()

fun part2(input: List<String>): Int {
    val directories = mapDirectories(input)
    val rootSize = dirSize(directories[0])
    return directories
        .map { dirSize((it)) }
        .filter { (70000000 + it - rootSize) > 30000000 }.min()
}