package be.moac.aoc2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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


    /**
     * xmul(2,4)&mul[3,7]!^don't()_mul(5,5)don't()+mul(32,64](mul(11,8)undo()?mul(8,5))
     * This corrupted memory is similar to the example from before, but this time the mul(5,5) and mul(11,8) instructions are disabled because there is a don't() instruction before them.
     * The other mul instructions function normally, including the one at the end that gets re-enabled by a do() instruction.
     *
     * This time, the sum of the results is 48 (2*4 + 8*5).
     */
    @Test
    fun `part 2`() {
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)don't()+mul(32,64](mul(11,8)undo()?mul(8,5))"
        assertThat(Day03 partTwo input).isEqualTo(48L)
    }

}