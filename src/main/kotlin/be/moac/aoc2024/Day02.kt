package be.moac.aoc2024

import kotlin.math.abs
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day02_input.txt".readLines()

    println("Part one: ${timed { Day02 partOne input }}")
    println("Part two: ${timed { Day02 partTwo input }}")
}

object Day02 {
    infix fun partOne(input: List<String>): Int = input.count { Line(it).isSafe() }
    infix fun partTwo(input: List<String>): Int = input.count { Line(it).isSafePartTwo() }
}

private class Line(val value: List<Int>) {

    fun isSafe(): Boolean = value.isSafe()
    fun isSafePartTwo(): Boolean = value.isSafePartTwo()

    private fun List<Int>.isSafe() =
        (allIncreasing() or allDecreasing()) and safeDifference()

    private fun List<Int>.isSafePartTwo(): Boolean {
        fun checkLine(currentIdx: Int = 0): Boolean =
            when {
                currentIdx >= this.size -> false
                this.remove(currentIdx).isSafe() -> true
                else -> checkLine(currentIdx + 1)
            }
        return checkLine()
    }

    private fun List<Int>.remove(idx: Int) =
        toMutableList().apply { removeAt(idx) }

    private fun List<Int>.allIncreasing(): Boolean =
        this.windowed(2).all { it[1] > it[0] }

    private fun List<Int>.allDecreasing(): Boolean =
        this.windowed(2).all { it[0] > it[1] }

    private fun List<Int>.safeDifference(): Boolean =
        this.windowed(2).all { abs(it[0] - it[1]) in 1..3 }

    companion object {
        operator fun invoke(value: String): Line = Line(value.extractNumbers())
    }

}