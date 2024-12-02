package be.moac.aoc2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day02Test {

    private val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Test
    fun `that safe lines are counted`() {
        assertThat(Day02 partOne input.lines()).isEqualTo(2L)
    }

    @Test
    fun `that last digit is found`() {
        assertThat(Day02 partTwo input.lines()).isEqualTo(4L)
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = [
        "27 29 30 33 34 35 37 35 | 1",
        "41 42 45 47 49 51 53 58 | 1",
        "23 26 23 24 27 28 | 0"
    ])
    fun test(value: String, expected: Long) {
        assertThat(Day02 partTwo listOf(value)).isEqualTo(expected)
    }

}