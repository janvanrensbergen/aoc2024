package be.moac.aoc2023

import be.moac.aoc2023.Day03.Something.EnginePart
import be.moac.aoc2023.Day03.Something.Symbol
import be.moac.aoc2023.Day03.filterParts
import be.moac.aoc2023.Day03.parse
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day03Test {

    @Test
    fun `that a line with part numbers can be parsed`() {
        assertThat(listOf("467..114..").parse()).containsExactly(
            EnginePart(467, listOf(0 to 0, 1 to 0, 2 to 0)),
            EnginePart(114, listOf(5 to 0, 6 to 0, 7 to 0))
        )
    }

    @Test
    fun `that a line with symbol can be parsed`() {
        assertThat(listOf("...*......").parse()).containsExactly(
            Symbol('*', 3 to 0)
        )
    }

    @Test
    fun `char as int`() {
        assertThat(Integer.parseInt('5'.toString())).isEqualTo(5)
    }

    @Test
    fun `that the engine parts that are not adjacent to a symbol are filtered out`() {
        assertThat(listOf(
            "467..114..",
            "...*......"
        ).parse().filterParts()).containsExactly(
            EnginePart(467, listOf(0 to 0, 1 to 0, 2 to 0)),
        )
    }

    @Test
    fun `that a engine part has adjacent points`() {
        assertThat(EnginePart(0, listOf(5 to 5)).adjacentPoints).containsExactlyInAnyOrder(
            4 to 4, 5 to 4, 6 to 4,
            4 to 5,         6 to 5,
            4 to 6, 5 to 6, 6 to 6,
        )

        assertThat(EnginePart(0, listOf(5 to 5, 6 to 5, 7 to 5)).adjacentPoints).containsExactlyInAnyOrder(
            4 to 4, 5 to 4, 6 to 4, 7 to 4, 8 to 4,
            4 to 5,                         8 to 5,
            4 to 6, 5 to 6, 6 to 6, 7 to 6, 8 to 6
        )
    }

    @Test
    fun `that the som of the engine parts is correct`() {
        assertThat(Day03.partOne(listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...${'$'}.*....",
            ".664.598..",
        ))).isEqualTo(4361)
    }
}


