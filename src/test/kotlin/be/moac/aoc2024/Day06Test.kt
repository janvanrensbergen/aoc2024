package be.moac.aoc2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {

    private val input = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()

    @Test
    fun `part 1`() {
        assertThat(Day06 partOne input.lines()).isEqualTo(41)
    }



    @Test
    fun `part 2`() {
        assertThat(Day06 partTwo input.lines()).isEqualTo(123)
    }


}