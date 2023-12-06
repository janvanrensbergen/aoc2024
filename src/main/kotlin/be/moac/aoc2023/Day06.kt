package be.moac.aoc2023

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day06_input.txt".readLines { it }

    println("Part one: ${timed(1) { Day06 partOne input }}")
    println("Part two: ${timed(1) { Day06 partTwo input }}")
}

object Day06 {

    infix fun partOne(input: List<String>): Long = 8


    infix fun partTwo(input: List<String>): Long = 0

}