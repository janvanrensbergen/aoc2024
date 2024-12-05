package be.moac.aoc2024

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day05Test {

    private val input = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    @Test
    fun `part 1`() {
        assertThat(Day05 partOne input.lines()).isEqualTo(143)
//        assertThat(Day05 partOne "47|53\n    47,53".lines()).isEqualTo(1L)
//        assertThat(Day05 partOne "47|53\n47|54\n53|54\n   47,53,54".lines()).isEqualTo(1L)
//        assertThat(Day05 partOne "47|53\n47|54\n53|54\n   47,53,54\n47,54".lines()).isEqualTo(2L)
//        assertThat(Day05 partOne "53|47\n47|58\n53|57\n   47,53,57".lines()).isEqualTo(0L)
//        assertThat(Day05 partOne "47|53\n57|53\n   47,53,57".lines()).isEqualTo(0L)
    }



    @Test
    fun `part 2`() {
        assertThat(Day05 partTwo input.lines()).isEqualTo(123)
    }


}