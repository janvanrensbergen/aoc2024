package be.moac.aoc2023

import be.moac.aoc2023.Day01.first
import be.moac.aoc2023.Day01.firstDigit
import be.moac.aoc2023.Day01.last
import be.moac.aoc2023.Day01.lastDigit
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01Test {


    @Test
    fun `that first digit is found`() {
        assertThat("1abc2".firstDigit()).isEqualTo('1')
        assertThat("pqr3stu8vwx".firstDigit()).isEqualTo('3')
        assertThat("a1b2c3d4e5f".firstDigit()).isEqualTo('1')
        assertThat("treb7uchet".firstDigit()).isEqualTo('7')
    }

    @Test
    fun `that last digit is found`() {
        assertThat("1abc2".lastDigit()).isEqualTo('2')
        assertThat("pqr3stu8vwx".lastDigit()).isEqualTo('8')
        assertThat("a1b2c3d4e5f".lastDigit()).isEqualTo('5')
        assertThat("treb7uchet".lastDigit()).isEqualTo('7')
    }

    @Test
    fun `part one`() {

        val input = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
        )

        assertThat(Day01.partOne(input)).isEqualTo(142)
    }


    @Test
    fun `that all written numbers get replaced with digits`() {
        assertThat("two1nine".first()).isEqualTo(2)
        assertThat("two1nine".last()).isEqualTo(9)
        assertThat("eightwothree".first()).isEqualTo(8)
        assertThat("eightwothree".last()).isEqualTo(3)
        assertThat("abcone2threexyz".first()).isEqualTo(1)
        assertThat("abcone2threexyz".last()).isEqualTo(3)
        assertThat("xtwone3four".first()).isEqualTo(2)
        assertThat("xtwone3four".last()).isEqualTo(4)
        assertThat("4nineeightseven2".first()).isEqualTo(4)
        assertThat("4nineeightseven2".last()).isEqualTo(2)
        assertThat("sevenine".first()).isEqualTo(7)
        assertThat("sevenine".last()).isEqualTo(9)
        assertThat("eighthree".first()).isEqualTo(8)
        assertThat("eighthree".last()).isEqualTo(3)
        assertThat("zoneight234".first()).isEqualTo(1)
        assertThat("zoneight234".last()).isEqualTo(4)
        assertThat("7pqrstsixteen".first()).isEqualTo(7)
        assertThat("7pqrstsixteen".last()).isEqualTo(6)
        assertThat("tgppgp9".first()).isEqualTo(9)
        assertThat("tgppgp9".last()).isEqualTo(9)
        assertThat("jmgnfive7ffglffpjlvbtvl935zz".first()).isEqualTo(5)
        assertThat("jmgnfive7ffglffpjlvbtvl935zz".last()).isEqualTo(5)
    }

    @Test
    fun `part two`() {
        val input = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
        )

        assertThat(Day01.partTwo(input)).isEqualTo(281)
    }

}