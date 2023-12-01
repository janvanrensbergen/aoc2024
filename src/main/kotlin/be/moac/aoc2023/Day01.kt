package be.moac.aoc2023

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day01_input.txt".readLines { it }

    println("Part one: ${timed { Day01 partOne input }}")
    println("Part two: ${timed { Day01 partTwo input }}")
}

object Day01 {

    infix fun partOne(input: List<String>): Long = 0L


    infix fun partTwo(input: List<String>): Long = 0L


}