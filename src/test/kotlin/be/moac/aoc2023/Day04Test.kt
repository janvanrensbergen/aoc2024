package be.moac.aoc2023

import be.moac.aoc2023.Day04.parse
import be.moac.aoc2023.Day04.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.pow

class Day04Test {


    @Test
    fun `that a card can be parsed`() {
        assertThat("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53".parse()).isEqualTo(Card(listOf(41, 48, 83, 86, 17), listOf(83, 86,  6, 31, 17,  9, 48, 53)))
        assertThat("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19".parse()).isEqualTo(Card(listOf(13, 32, 20, 16, 61), listOf(61, 30, 68, 82, 17, 32, 24, 19)))
        assertThat("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1".parse()).isEqualTo(Card(listOf( 1, 21, 53, 59, 44), listOf(69, 82, 63, 72, 16, 21, 14,  1)))
        assertThat("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83".parse()).isEqualTo(Card(listOf(41, 92, 73, 84, 69), listOf(59, 84, 76, 51, 58,  5, 54, 83)))
        assertThat("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36".parse()).isEqualTo(Card(listOf(87, 83, 26, 28, 32), listOf(88, 30, 70, 12, 93, 22, 82, 36)))
        assertThat("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11".parse()).isEqualTo(Card(listOf(31, 18, 13, 56, 72), listOf(74, 77, 10, 23, 35, 67, 36, 11)))
    }


    @Test
    fun `a card can give matching numbers`() {
        assertThat(Card(listOf(41, 48, 83, 86, 17), listOf(83, 86,  6, 31, 17,  9, 48, 53)).result).containsExactlyInAnyOrder(48, 83, 17, 86)
        assertThat(Card(listOf(13, 32, 20, 16, 61), listOf(61, 30, 68, 82, 17, 32, 24, 19)).result).containsExactlyInAnyOrder(32, 61)
        assertThat(Card(listOf( 1, 21, 53, 59, 44), listOf(69, 82, 63, 72, 16, 21, 14,  1)).result).containsExactlyInAnyOrder(1, 21)
        assertThat(Card(listOf(41, 92, 73, 84, 69), listOf(59, 84, 76, 51, 58,  5, 54, 83)).result).containsExactlyInAnyOrder(84)
        assertThat(Card(listOf(87, 83, 26, 28, 32), listOf(88, 30, 70, 12, 93, 22, 82, 36)).result).isEmpty()
        assertThat(Card(listOf(31, 18, 13, 56, 72), listOf(74, 77, 10, 23, 35, 67, 36, 11)).result).isEmpty()
    }

    @Test
    fun `a card can calculate the points`() {
        assertThat(Card(listOf(41, 48, 83, 86, 17), listOf(83, 86,  6, 31, 17,  9, 48, 53)).points).isEqualTo(8)
        assertThat(Card(listOf(13, 32, 20, 16, 61), listOf(61, 30, 68, 82, 17, 32, 24, 19)).points).isEqualTo(2)
        assertThat(Card(listOf( 1, 21, 53, 59, 44), listOf(69, 82, 63, 72, 16, 21, 14,  1)).points).isEqualTo(2)
        assertThat(Card(listOf(41, 92, 73, 84, 69), listOf(59, 84, 76, 51, 58,  5, 54, 83)).points).isEqualTo(1)
        assertThat(Card(listOf(87, 83, 26, 28, 32), listOf(88, 30, 70, 12, 93, 22, 82, 36)).points).isEqualTo(0)
        assertThat(Card(listOf(31, 18, 13, 56, 72), listOf(74, 77, 10, 23, 35, 67, 36, 11)).points).isEqualTo(0)
    }

    @Test
    fun `part one`() {
        assertThat(Day04.partOne(listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
        ))).isEqualTo(13)
    }
}