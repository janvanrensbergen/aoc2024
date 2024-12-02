package be.moac.aoc2024

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day01Test {


    @Test
    fun `that first digit is found`() {
          val input = """
              3   4
              4   3
              2   5
              1   3
              3   9
              3   3
          """.trimIndent().lines()

        Assertions.assertThat(Day01 partOne input).isEqualTo(11L)
    }

    @Test
    fun `that last digit is found`() {

    }

}