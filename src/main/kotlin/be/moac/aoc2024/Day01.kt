package be.moac.aoc2024

import kotlin.math.abs
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day01_input.txt".readLines { it }

    println("Part one: ${timed(1) { Day01 partOne input }}")
    println("Part two: ${timed { Day01 partTwo input }}")
}

object Day01 {

    infix fun partOne(input: List<String>): Long =
        input.parse().sumOf { abs(it.second - it.first) }

    infix fun partTwo(input: List<String>): Long {
        val (first, second) = input.parse().unzip()
        return first.sumOf { it * second.count { foo -> foo == it } }
    }

    private fun List<String>.parse(): List<Pair<Long, Long>> =
        this.map { line -> line.extractNumbers<Long>().let { it.first() to it.last() } }
            .fold(listOf<Long>() to listOf<Long>()) { acc, current -> (acc.first + current.first).sorted() to (acc.second + current.second).sorted() }
            .let { it.first.zip(it.second) }


}