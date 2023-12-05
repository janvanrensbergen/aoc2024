package be.moac.aoc2023

import be.moac.aoc2023.Day05.Location.Found
import be.moac.aoc2023.Day05.MappingRange
import be.moac.aoc2023.Day05.SingleSeed
import be.moac.aoc2023.Day05.parse
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class Day05Test {

    private val input = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent()

    @Test
    fun `that parsing the input will create a Almanac with seeds`() {

        val result = input.lines().parse()
        assertThat(result).isInstanceOf(Day05.Almanac::class.java)
        assertThat(result.seeds).containsExactly(
            SingleSeed(79),
            SingleSeed(14),
            SingleSeed(55),
            SingleSeed(13)
        )
    }

    @Test
    fun `that parsing will create the mappings`() {
        val result = input.lines().parse()

        assertThat(result.mappings).contains(
            Day05.Mapping(
                "seed", "soil", listOf(
                    MappingRange(50, 98, 2),
                    MappingRange(52, 50, 48)
                )
            ),
            Day05.Mapping(
                "soil", "fertilizer", listOf(
                    MappingRange(0, 15, 37),
                    MappingRange(37, 52, 2),
                    MappingRange(39, 0, 15)
                )
            ),
            Day05.Mapping(
                "humidity", "location", listOf(
                    MappingRange(60, 56, 37),
                    MappingRange(56, 93, 4),
                )
            )
        )
    }

    @Test
    fun `that locations can be found for seeds`() {
        val almanac = input.lines().parse()

        assertSoftly {
            it.assertThat(almanac.findLocation("seed", 79)).isEqualTo(82)
            it.assertThat(almanac.findLocation("seed", 14)).isEqualTo(43)
            it.assertThat(almanac.findLocation("seed", 55)).isEqualTo(86)
            it.assertThat(almanac.findLocation("seed", 13)).isEqualTo(35)
        }

    }

    @Test
    fun `that when no mapping is found, destination is same as source`() {
        assertThat(Day05.Mapping("from", "to", listOf()).map(10)).isEqualTo(10)
    }

    @Test
    fun `that mapping is found`() {
        assertThat(Day05.Mapping("from", "to", listOf(MappingRange(10, 20, 5))).map(25)).isEqualTo(15)
        assertThat(Day05.Mapping("from", "to", listOf(
            MappingRange(10, 20, 2),
            MappingRange(12, 22, 5),
        )).map(25)).isEqualTo(15)
    }

    @Test
    fun `part one must return the lowest location`() {
        assertThat(Day05.partOne(input.lines())).isEqualTo(35)
    }

    @Test
    fun `part two must also return the lowest location`() {
        assertThat(Day05.partTwo(input.lines())).isEqualTo(46)
    }
}