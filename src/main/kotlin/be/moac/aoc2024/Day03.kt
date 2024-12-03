package be.moac.aoc2024

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: String = "/day03_input.txt".readResource()

    println("Part one: ${timed { Day03 partOne input }}")
//    println("Part two: ${timed { Day03 partTwo input }}")
}

object Day03 {
    infix fun partOne(input: String): Long =
        input.parse().sumOf { it.first * it.second }

    infix fun partTwo(input: String): Long = 0L


    private fun String.parse() =
        pattern.findAll(this).map {
            it.groups[1]?.value?.toLong()!! to it.groups[2]?.value?.toLong()!!
        }.toList()

}

private val pattern = Regex("mul\\((\\d+),(\\d+)\\)")