package be.moac.aoc2023

import be.moac.aoc2023.Day03.Something.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day03_input.txt".readLines { it }

    println("Part one: ${timed { Day03 partOne input }}")
    println("Part two: ${timed(1) { Day03 partTwo input }}")
}

object Day03 {

    infix fun partOne(input: List<String>): Int =
        input.parse()
            .filterParts()
            .filterIsInstance<EnginePart>()
            .sumOf { it.number }

    infix fun partTwo(input: List<String>): Long =
        input.parse()
            .findGears()
            .sumOf { gear ->
                val (first, second) = gear.parts.map { it.number.toLong() }
                first * second
            }

    fun List<String>.parse(): List<Something> =
        this.flatMapIndexed { y, line ->
            line.foldIndexed<List<Something>>(listOf()) { x, acc, current ->
                when {
                    current.isDigit() -> {
                        when {
                            acc.isEmpty() -> acc + EnginePart(current.toString().toInt(), listOf(x to y))
                            acc.last() is EnginePart -> {
                                val last: EnginePart = acc.last() as EnginePart
                                acc.dropLast(1) + (last + Triple(current, x, y))
                            }

                            else -> acc + EnginePart(current.toString().toInt(), listOf(x to y))
                        }
                    }

                    current == '.' -> acc + Point
                    else -> acc + Symbol(current, x to y)
                }
            }.filterNot { it == Point }
        }

    fun List<Something>.filterParts(): List<Something> = this.filter {
        when (it) {
            is Symbol -> false
            is EnginePart -> this.containsSymbolAt(it.adjacentPoints)
            Point -> false
        }
    }

    private fun List<Something>.containsSymbolAt(positions: List<Pair<Int, Int>>): Boolean =
        this.filterIsInstance<Symbol>()
            .any { positions.contains(it.position) }

    fun List<Something>.findGears(): List<Gear> =
        this.filterIsInstance<Symbol>()
            .filter { it.value == '*' }
            .map { Gear(this.findPartsAt(it.adjacentPoints)) }
            .filter { it.parts.size == 2 }

    private fun List<Something>.findPartsAt(positions: List<Pair<Int, Int>>): List<EnginePart> =
        this.filterIsInstance<EnginePart>()
            .filter { it.positions.any { p -> positions.contains(p) } }


    data class Gear(val parts: List<EnginePart>)

    sealed interface Something {
        data class EnginePart(val number: Int, val positions: List<Pair<Int, Int>>) : Something {

            private val minX: Int get() = positions.minOf { it.first }
            private val maxX: Int get() = positions.maxOf { it.first }
            private val y: Int get() = positions.first().second

            val adjacentPoints: List<Pair<Int, Int>> =
                (minX - 1..maxX + 1).map { it to y - 1 } +
                        (minX - 1 to y) + (maxX + 1 to y) +
                        (minX - 1..maxX + 1).map { it to y + 1 }

            operator fun plus(value: Triple<Char, Int, Int>): EnginePart =
                this.copy(
                    number = "$number${value.first}".toInt(),
                    positions = positions + (value.second to value.third)
                )
        }

        data class Symbol(val value: Char, val position: Pair<Int, Int>) : Something {

            private val x get() = position.first
            private val y get() = position.second

            val adjacentPoints: List<Pair<Int, Int>> = listOf(
                x - 1 to y - 1, x to y - 1, x + 1 to y - 1,
                x - 1 to y,                 x + 1 to y,
                x - 1 to y + 1, x to y + 1, x + 1 to y + 1,
            )

        }

        data object Point : Something
    }
}