package be.moac.aoc2023

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day04_input.txt".readLines { it }

    println("Part one: ${timed { Day04 partOne input }}")
    println("Part two: ${timed { Day04 partTwo input }}")
}

object Day04 {

    infix fun partOne(input: List<String>): Long =
        input.map { it.parse() }.sumOf { it.points }


    infix fun partTwo(input: List<String>): Long = 0


    fun String.parse(): Card {
        val (winning, myNumbers) = this.split(":").last().split("|")
        return Card(
            regex.findAll(winning).map { it.value.toLong() }.toList(),
            regex.findAll(myNumbers).map { it.value.toLong() }.toList(),
        )
    }

    data class Card(val winningNumbers: List<Long>, val numbers: List<Long>) {
        val result: Set<Long> get() = numbers.intersect(winningNumbers.toSet())

        val points: Long get() = result.fold(0) { acc, _ -> when {
            acc == 0L -> 1
            else -> acc * 2
        } }
    }

}

private val regex = "\\b\\d+\\b".toRegex()