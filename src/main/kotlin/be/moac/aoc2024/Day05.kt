package be.moac.aoc2024

import be.moac.aoc2024.Day05.parsePages
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/Day05_input.txt".readLines()

    println("Part one: ${timed { Day05 partOne input }}")
    println("Part two: ${timed { Day05 partTwo input }}")
}

object Day05 {

    infix fun partOne(input: List<String>): Int =
        with(input.parseRules()) {
            input.parsePages()
                .filter { it.isValid(this) }
                .sumOf { it.middle() }
        }

    infix fun partTwo(input: List<String>): Int =
        with(input.parseRules()) {
            input.parsePages()
                .filterNot { it.isValid(this) }
                .map { it.sorted(this) }
                .sumOf { it.middle() }
        }


    private fun List<String>.parseRules(): Rules =
        this.filter { it.contains("|") }
            .map { line ->
                line.split("|").let { Rule(it[0].trim().toInt(), it[1].trim().toInt()) }
            }
            .let { Rules(it) }

    private fun List<String>.parsePages(): List<Pages> =
        this.filterNot { it.contains("|") }
            .filterNot { it.isBlank() }
            .map { line -> Pages(line.split(",").map { it.trim().toInt() }) }


}

private data class Rules(private val rules: List<Rule>) {

    fun isValid(value: List<Pair<Int, Int>>): Boolean =
        value.all { findRulesFor(it).all { rule -> rule.isValid(it) } }

    fun findRulesFor(value: Pair<Int, Int>): List<Rule> =
        rules.filter {
            (it.first == value.first || it.first == value.second)
                    && (it.second == value.first || it.second == value.second)
        }
}

private data class Rule(val first: Int, val second: Int) {
    fun isValid(value: Pair<Int, Int>): Boolean =
        first == value.first && second == value.second
}

private data class Pages(private val numbers: List<Int>) {

    fun sorted(rules: Rules): Pages = Pages(numbers.sortedWith { left, right ->
        rules.findRulesFor(left to right).firstOrNull()?.let {
            when {
                left == it.first && right == it.second -> -1
                left == it.second && right == it.first -> 1
                else -> 0
            }
        } ?: 0
    })

    fun middle(): Int = (numbers.size / 2).let { numbers[it] }

    fun isValid(rules: Rules): Boolean {
        fun check(pair: Pair<Int, Pages>?, result: Boolean): Boolean =
            when {
                pair == null -> result
                else -> {
                    check(
                        pair.second.takeFirst(),
                        result && pair.second.pairUpWith(pair.first)
                            .let { pairs -> rules.isValid(pairs) })
                }
            }

        return check(takeFirst(), true)
    }

    fun takeFirst(): Pair<Int, Pages>? =
        numbers.firstOrNull()?.let { it to Pages(numbers.drop(1)) }

    fun pairUpWith(value: Int): List<Pair<Int, Int>> =
        numbers.map { value to it }
}