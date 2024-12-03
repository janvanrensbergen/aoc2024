package be.moac.aoc2024

import kotlin.text.RegexOption.DOT_MATCHES_ALL
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: String = "/day03_input.txt".readResource()

    println("Part one: ${timed { Day03 partOne input }}")
    println("Part two: ${timed { Day03 partTwo input }}")
}

object Day03 {
    infix fun partOne(input: String): Long =
        input.parse().sum()

    infix fun partTwo(input: String): Long =
        input.removeDisabledParts().parse().sum()


    private fun List<Pair<Long, Long>>.sum() =
        this.sumOf { it.first * it.second }

    private fun String.removeDisabledParts(): String =
        when(val match = patternRemove.find(this)) {
            null -> this
            else -> this.replace(match.value, "").removeDisabledParts()
        }

    private fun String.parse(): List<Pair<Long, Long>> =
        pattern.findAll(this).map {
            it.groups[1]?.value?.toLong()!! to it.groups[2]?.value?.toLong()!!
        }.toList()

}

private val pattern = Regex("mul\\((\\d+),(\\d+)\\)")
private val patternRemove = Regex("don't\\(\\).*?do\\(\\)", DOT_MATCHES_ALL)