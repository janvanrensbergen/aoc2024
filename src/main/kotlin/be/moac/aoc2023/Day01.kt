package be.moac.aoc2023

import be.moac.aoc2023.Day01.first
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day01_input.txt".readLines { it }

    println("Part one: ${timed { Day01 partOne input }}")
    println("Part two: ${timed { Day01 partTwo input }}")
}

object Day01 {

    infix fun partOne(input: List<String>): Long =
        input
            .mapIndexed { index, s -> "${s.firstDigit()}${s.lastDigit()}".toLong().also { println("$index - $it") } }
            .sum()

    infix fun partTwo(input: List<String>): Long =
        input
            .mapIndexed { index, s -> "${s.first()}${s.last()}".toLong().also { println("$index - $it,") } }
            .sum()

    fun String.firstDigit(): Char = this.first { it.isDigit() }
    fun String.lastDigit(): Char = this.last { it.isDigit() }

    private val digits = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )


    fun String.first(): Int {
        return digits.entries
            .map {
                val a = this.indexOf(it.key)
                val b = this.indexOf("${it.value}")
                when {
                    a == -1 -> b
                    b == -1 -> a
                    a < b -> a
                    else -> b
                } to it
            }
            .filterNot { it.first == -1 }
            .minByOrNull { it.first }?.second?.value!!
    }

    fun String.last(): Int {
        return digits.entries
            .map {
                val a = this.lastIndexOf(it.key)
                val b = this.lastIndexOf("${it.value}")
                when {
                    a == -1 -> b
                    b == -1 -> a
                    a > b -> a
                    else -> b
                } to it

            }
            .filterNot {
                it.first == -1
            }
            .maxByOrNull {
                it.first
            }?.second?.value!!
    }
}