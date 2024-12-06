package be.moac.aoc2024

import be.moac.aoc2024.Direction.Up
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/Day06_input.txt".readLines()

    println("Part one: ${timed(1) { Day06 partOne input }}")
    println("Part two: ${timed { Day06 partTwo input }}")
}

object Day06 {

    infix fun partOne(input: List<String>): Int =
        input.parse().let { grid -> move(grid = grid, currentGuardPosition = grid.guardPosition(), setOf(grid.guardPosition().coordinates)) }

    infix fun partTwo(input: List<String>): Int = 0

    private fun move(grid: Grid, currentGuardPosition: Position, visited: Set<Coordinates> = setOf()): Int {
//        println("$currentGuardPosition   $numberOfMoves    ")
//        grid.print(currentGuardPosition)
        return when {
            grid.isInside(currentGuardPosition.coordinates) -> move(grid, grid.moveGuard(currentGuardPosition), visited + setOf(currentGuardPosition.coordinates))
            else -> visited.size
        }
    }

    private fun List<String>.parse(): Grid =
        this.map { it.toCharArray() }.toTypedArray()

    private fun Grid.guardPosition(): Position =
        indices.flatMap { rowIdx -> this[rowIdx].indices.map { colIdx -> rowIdx to colIdx } }
            .first { (row, col) -> this[row][col] == '^' }
            .toPosition()

    private fun Grid.isInside(coordinates: Coordinates): Boolean =
        coordinates.col in this[0].indices && coordinates.row in indices

    private fun Grid.moveGuard(currentGuardPosition: Position): Position {
        val newCoordinates = currentGuardPosition.coordinates + currentGuardPosition.direction.move

        return when  {
            !isInside(newCoordinates) -> currentGuardPosition.copy(coordinates = newCoordinates)
            this[newCoordinates.row][newCoordinates.col] == '#' -> this.moveGuard(currentGuardPosition.copy(direction = currentGuardPosition.direction.turnRight()))
            else -> currentGuardPosition.copy(coordinates = newCoordinates)
        }
    }

    private fun Grid.print(currentGuardPosition: Position) {
        this.indices.forEach { rowIdx ->
            this[rowIdx].mapIndexed { colIdx, c ->
                when {
                    currentGuardPosition.coordinates == Coordinates(rowIdx, colIdx) -> '^'
                    c == '#' -> c
                    else -> '.'
                }
            }.joinToString(separator = "")
                .print()
        }
    }

}

private fun Pair<Int, Int>.toPosition(): Position = Position(first, second)

private typealias Grid = Array<CharArray>

private data class Position(val coordinates: Coordinates, val direction: Direction) {
    companion object {
        operator fun invoke(row: Int, col: Int) = Position(Coordinates(row, col), Up)
        operator fun invoke(row: Int, col: Int, directionn: Direction) = Position(Coordinates(row, col), directionn)
    }
}

private data class Coordinates(val row: Int, val col: Int) {
    operator fun plus(other: Coordinates) = Coordinates(row + other.row, col + other.col)
}

private sealed interface Direction {
    fun turnRight(): Direction
    val move: Coordinates

    data object Up : Direction {
        override fun turnRight(): Direction = Right
        override val move: Coordinates = Coordinates(-1, 0)
    }
    data object Right : Direction{
        override fun turnRight(): Direction = Down
        override val move: Coordinates = Coordinates(0, 1)
    }
    data object Down : Direction{
        override fun turnRight(): Direction = Left
        override val move: Coordinates = Coordinates(1, 0)
    }
    data object Left : Direction{
        override fun turnRight(): Direction = Up
        override val move: Coordinates = Coordinates(0, -1)
    }
}



