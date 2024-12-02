package be.moac.aoc2024

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day01_input.txt".readLines { it }

    println("Part one: ${timed(1) { Day01 partOne input }}")
//    println("Part two: ${timed { Day01 partTwo input }}")
}

object Day01 {

    infix fun partOne(input: List<String>): Long =
        input.parse().sumOf {
            if(it.second > it.first) it.second - it.first else it.first - it.second
        }

    infix fun partTwo(input: List<String>): Long = 0L

    private fun List<String>.parse(): List<Pair<Long, Long>> =
        this.filterNot { it.isBlank() }
        .map { line ->
            val split = line.split("   ")
            split[0].toLong() to split[1].toLong()
        }
        .fold(listOf<Long>() to listOf<Long>()) { acc, current ->
            (acc.first + current.first).sorted() to (acc.second + current.second).sorted()
        }.let { it.first.zip(it.second) }


}