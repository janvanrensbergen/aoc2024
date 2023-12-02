package be.moac.aoc2023

import be.moac.aoc2023.Day02.parse
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun `that Game 1 can be parsed`() {

        val result = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green".parse()

        assertThat(result.id).isEqualTo(1)
        assertThat(result.rounds).containsExactly(
            Day02.Round(blues = 3, reds = 4),
            Day02.Round(blues = 6, reds = 1, greens = 2),
            Day02.Round(greens = 2)
        )
    }

    @Test
    fun `that Game 2 can be parsed`() {

        val result = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue".parse()

        assertThat(result.id).isEqualTo(2)
        assertThat(result.rounds).containsExactly(
            Day02.Round(blues = 1, greens = 2),
            Day02.Round(blues = 4, reds = 1, greens = 3),
            Day02.Round(greens = 1, blues = 1)
        )
    }

    @Test
    fun `that Game 3 can be parsed`() {

        val result = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red".parse()

        assertThat(result.id).isEqualTo(3)
        assertThat(result.rounds).containsExactly(
            Day02.Round(greens = 8, blues = 6, reds = 20),
            Day02.Round(blues = 5, reds = 4, greens = 13),
            Day02.Round(greens = 5, reds = 1)
        )
    }

    @Test
    fun `only 12 red cubes, 13 green cubes, and 14 blue cubes`() {

        val input = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        )

        assertThat(Day02.partOne(input)).isEqualTo(8)
    }

    @Test
    fun `part 2`() {

        val input = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        )

        assertThat(Day02.partTwo(input)).isEqualTo(2286)
    }
}