package be.moac.aoc2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day03Test {

    /**
     * For example, consider the following section of corrupted memory:
     *
     * xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
     * Only the four highlighted sections are real mul instructions. Adding up the result of each instruction produces 161 (2*4 + 5*5 + 11*8 + 8*5).
     */

    @Test
    fun `part 1`() {
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

        assertThat(Day03 partOne input).isEqualTo(161L)
    }

    @Test
    fun `part 2`() {

    }

}