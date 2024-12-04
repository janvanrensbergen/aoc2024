package be.moac.aoc2024

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Test

class Day04Test {

    private val input = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()

    @Test
    fun `part 1 - count xmas`() {
        assertThat(Day04 partOne input.lines()).isEqualTo(18L)
    }

    @Test
    fun `part 1 - count xmas horizontal`() {
        assertSoftly { softly ->
            softly.assertThat(Day04 partOne listOf("XMASJOSJOS")).isEqualTo(1L)
            softly.assertThat(Day04 partOne listOf("SAMXJOSJOS")).isEqualTo(1L)
            softly.assertThat(Day04 partOne listOf("XMASJOSJOS","JOSJOXMASS","JOSJOSXMAS")).isEqualTo(3L)
            softly.assertThat(Day04 partOne listOf("SAMXJOSJOS","JOSJOSAMXS","JOSJOSSAMX")).isEqualTo(3L)
        }
    }

    @Test
    fun `part 1 - count xmas vertical`() {
        assertSoftly { softly ->
            softly.assertThat(Day04 partOne listOf(
                "XJSSJOS",
                "MJAXSOS",
                "AJMMAOS",
                "SJXAMOS",
                "FJOSXOS",
            )).isEqualTo(4L)
        }
    }

    @Test
    fun `part 1 - count xmas diagonal right`() {
        assertSoftly { softly ->
            softly.assertThat(Day04 partOne listOf(
                ".......XS.......",
                "XS......MA......",
                ".MA..XS..AM.....",
                "..AM..MA..SX....",
                "...SX..AM.......",
                "........SX......",
            )).isEqualTo(6L)
        }
    }
    @Test
    fun `part 1 - count xmas diagonal left`() {
        assertSoftly { softly ->
            softly.assertThat(Day04 partOne listOf(
                ".........SX...",
                "...SX...AM....",
                "..AM...MA..SX.",
                ".MA...XS..AM..",
                "XS.......MA...",
                "........XS....",
            )).isEqualTo(6L)
        }
    }




    @Test
    fun `part 2`() {

    }

}