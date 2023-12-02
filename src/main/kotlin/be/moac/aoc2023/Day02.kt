package be.moac.aoc2023

import be.moac.aoc2023.Day01.first
import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day02_input.txt".readLines { it }

    println("Part one: ${timed { Day02 partOne input }}")
    println("Part two: ${timed { Day02 partTwo input }}")
}

object Day02 {

    infix fun partOne(input: List<String>): Int =
        input.map { it.parse() }
            .filter { it.possible() }
            .sumOf { it.id }

    infix fun partTwo(input: List<String>): Long =
        input.map { it.parse() }
            .sumOf { it.power() }


    fun String.parse(): Game {
        val (game, rounds) = this.split(":")
        return Game(game.removePrefix("Game ").toInt(), rounds.split(";").map { it.parseRound() })
    }

    private fun String.parseRound(): Round {
        return this.split(",")
            .fold(Round()) { acc, s ->
                when {
                    s.endsWith("blue") -> acc.copy(blues = s.trim().removeSuffix(" blue").toInt())
                    s.endsWith("red") -> acc.copy(reds = s.trim().removeSuffix(" red").toInt())
                    s.endsWith("green") -> acc.copy(greens = s.trim().removeSuffix(" green").toInt())
                    else -> acc
                }
            }
    }

    data class Game(val id: Int, val rounds: List<Round>) {

        fun possible(): Boolean =
            rounds.none { it.greens > 13 || it.reds > 12 || it.blues > 14 }

        fun power(): Long =
            rounds.maxOf { it.blues.toLong() } *
            rounds.maxOf { it.greens.toLong() } *
            rounds.maxOf { it.reds.toLong() }
    }

    data class Round(val greens: Int = 0, val reds: Int = 0, val blues: Int = 0)
}