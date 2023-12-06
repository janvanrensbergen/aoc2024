package be.moac.aoc2023

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day06_input.txt".readLines { it }
    val inputTwo: List<String> = "/day06_2_input.txt".readLines { it }

    println("Part one: ${timed(1) { Day06 partOne input }}")
    println("Part two: ${timed(1) { Day06 partTwo inputTwo }}")
}

object Day06 {

    infix fun partOne(input: List<String>): Long =
        input.parse()
            .map { it.amountToBreakRecord() }
            .fold(1) {acc, n -> acc * n}


    infix fun partTwo(input: List<String>): Long = partOne(input)

    fun List<String>.parse(): List<Race> =
        this.first().extractNumbers("Time:") { it.toLong() }
            .zip(this.last().extractNumbers ("Distance:") {it.toLong()})
            { time, distance -> Race(time, distance) }

    data class Race(val time: Long, val distance: Long) {

        fun play(hold: Long): Long = (time - hold) * hold

        fun amountToBreakRecord(): Int =
            (1L until time)
                .map { play(it) }
                .count { it > distance }
    }
}