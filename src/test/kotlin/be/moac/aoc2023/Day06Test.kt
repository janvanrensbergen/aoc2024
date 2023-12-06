package be.moac.aoc2023

import be.moac.aoc2023.Day06.Race
import be.moac.aoc2023.Day06.parse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day06Test {

    private val input = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent()


    @Test
    fun `that input can be parsed`() {
        assertThat(input.lines().parse()).containsExactly(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200),
        )
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = [
        "7 | 9 | 0 | 0  ",
        "7 | 9 | 1 | 6  ",
        "7 | 9 | 2 | 10 ",
        "7 | 9 | 3 | 12 ",
        "7 | 9 | 4 | 12 ",
        "7 | 9 | 5 | 10 ",
        "7 | 9 | 6 | 6 ",
        "7 | 9 | 7 | 0  ",
    ])
    fun `that when you hold 1 millisecond you travel 6 millimeter`(raceTime: Int, raceDistance: Int, hold: Int, result: Int ) {
        assertThat(Race(raceTime, raceDistance).play(hold)).isEqualTo(result)
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = [
        "7  | 9   | 4  ",
        "15 | 40  | 8  ",
        "30 | 200 | 9  ",
    ])
    fun `that there are 4 different ways to break the distance`(raceTime: Int, raceDistance: Int, result: Int) {
        assertThat(Race(raceTime, raceDistance).amountToBreakRecord()).isEqualTo(result)
    }

    @Test
    fun `part one`() {
        assertThat(Day06.partOne(input.lines())).isEqualTo(288)
    }
}