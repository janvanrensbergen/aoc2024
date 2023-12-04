package be.moac.aoc2023

import be.moac.aoc2023.Day02.parse
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


    infix fun partTwo(input: List<String>): Int  {
        val cards = input.map { it.parse() }
        return cards.mapIndexed { index, card ->
            (index + 1 .. index +  card.result.size)
                .forEach { i -> if (i < cards.size) cards[i].inc(card.numberOfCopies) }
            card
        }
        .sumOf { it.numberOfCopies }
    }

    fun String.parse(): Card {
        val (winning, myNumbers) = this.split(":").last().split("|")
        return Card(
            regex.findAll(winning).map { it.value.toLong() }.toList(),
            regex.findAll(myNumbers).map { it.value.toLong() }.toList(),
        )
    }

    data class Card(val winningNumbers: List<Long>, val numbers: List<Long>, var numberOfCopies: Int = 1) {
        val result: Set<Long> get() = numbers.intersect(winningNumbers.toSet())

        val points: Long get() = result.fold(0) { acc, _ -> when {
            acc == 0L -> 1
            else -> acc * 2
        } }
        fun inc(value: Int) {
            this.numberOfCopies += value
        }
    }
}

private val regex = "\\b\\d+\\b".toRegex()