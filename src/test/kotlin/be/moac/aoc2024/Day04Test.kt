package be.moac.aoc2024

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day04Test {

    @Nested
    inner class Part1{
        @Test
        fun `part 1 - count xmas`() {
            assertThat(Day04 partOne """
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
    """.trimIndent().lines()).isEqualTo(18L)
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
    }


    @Nested
    inner class Part2{

        private val input = """
            .M.S......
            ..A..MSMS.
            .M.S.MAA..
            ..A.ASMSM.
            .M.S.M....
            ..........
            S.S.S.S.S.
            .A.A.A.A..
            M.M.M.M.M.
            ..........
        """.trimIndent()

        @Test
        fun `part 2`() {
            assertThat(Day04 partTwo input.lines()).isEqualTo(9L)
        }

        @Test
        fun `simple 1 X`() {
            assertThat(Day04 partTwo listOf(
                "M.S...",
                ".A...A",
                "M.S...",
            )).isEqualTo(1L)
        }

    }


}