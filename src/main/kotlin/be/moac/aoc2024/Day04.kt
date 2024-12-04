package be.moac.aoc2024

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day04_input.txt".readLines()

    println("Part one: ${timed { Day04 partOne input }}")
    println("Part two: ${timed { Day04 partTwo input }}")
}

object Day04 {

    infix fun partOne(input: List<String>): Long =
        input.parse().let { matrix ->
            matrix.mapIndexed { rowIdx, row ->
                row.mapIndexed { colIdx, _ -> matrix.countXmas(Point(rowIdx, colIdx)) }.sum()
            }
            .sum().toLong()
        }


    infix fun partTwo(input: List<String>): Long = 0L

    private val checks: List<Array<CharArray>.(point: Point) -> Boolean> = listOf(
        { point -> horizontal(point) },
        { point -> vertical(point) },
        { point -> diagonalRight(point) },
        { point -> diagonalLeft(point) },
    )

    private fun Array<CharArray>.countXmas(point: Point) =
        checks.count { check -> this.check(Point(point.row, point.col)) }

    private fun Array<CharArray>.horizontal(point: Point) =
        point.col + 4 <= this[point.row].size && this[point.row].copyOfRange(point.col, point.col+4).isXmas()

    private fun Array<CharArray>.vertical(point: Point) =
        point.row + 3 < size && (point.row .. point.row + 3).map { this[it][point.col] }.isXmas()

    private fun Array<CharArray>.diagonalRight(point: Point) =
        (point.row + 3 < size && point.col + 3 < this[point.row].size) &&
                (0 .. 3).map { idx -> this[point.row + idx][point.col + idx]}.isXmas()

    private fun Array<CharArray>.diagonalLeft(point: Point) =
        (point.row + 3 < size && point.col - 3 >= 0) &&
                (0 .. 3).map { idx -> this[point.row + idx][point.col - idx]}.isXmas()

    private fun List<String>.parse() =
        this.map { it.toCharArray() }.toTypedArray()

    private fun Collection<Char>.isXmas() = this.toCharArray().isXmas()
    private fun CharArray.isXmas() =
        this.joinToString(separator = "").let { it == "XMAS" || it == "SAMX" }


}

data class Point(val row: Int, val col: Int)
