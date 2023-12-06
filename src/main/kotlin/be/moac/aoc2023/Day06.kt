package be.moac.aoc2023

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day06_input.txt".readLines { it }

    println("Part one: ${timed(1) { Day06 partOne input }}")
    println("Part two: ${timed(1) { Day06 partTwo input }}")
}

object Day06 {

    infix fun partOne(input: List<String>): Int =
        input.parse()
            .map { it.amountToBreakRecord() }
            .fold(1) {acc, n -> acc * n}


    infix fun partTwo(input: List<String>): Int = 0

    fun List<String>.parse(): List<Race> =
        this.first().asIntList("Time:")
            .zip(this.last().asIntList("Distance:"))
            { time, distance -> Race(time, distance) }

    data class Race(val time: Int, val distance: Int) {

        fun play(hold: Int): Int = (time - hold) * hold

        fun amountToBreakRecord(): Int =
            (1 until time)
                .map { play(it) }
                .count { it > distance }
    }
}