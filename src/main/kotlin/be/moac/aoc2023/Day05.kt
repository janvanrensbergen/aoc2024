package be.moac.aoc2023

import be.moac.aoc2023.Day05.Location.Found
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day05_input.txt".readLines { it }

    println("Part one: ${timed(1) { Day05 partOne input }}")
    println("Part two: ${timed(1) { Day05 partTwo input }}")
}

object Day05 {

    infix fun partOne(input: List<String>): Long =
        input.parse().findLowest()


    infix fun partTwo(input: List<String>): Long =
        input.parse().findLowestPartTwo()


    fun List<String>.parse(): Almanac =
        Almanac(
            seeds = this.first().parseSeeds(),
            rangeSeeds = this.first().parseSeedsPartTwo(),
            mappings = this.drop(2).parseMappings()
        )

    private fun String.parseSeeds(): List<SingleSeed> =
        numberSpacesRegex.findAll(this.removePrefix("seeds:"))
            .map { SingleSeed(it.value.trim().toLong()) }
            .toList()

    private fun String.parseSeedsPartTwo(): List<RangeSeed> =
        numberSpacesRegex.findAll(this.removePrefix("seeds:"))
            .map { it.value.toLong() }
            .windowed(size = 2, step = 2)
            .map { (start, length) -> RangeSeed(start, length) }
            .toList()

    private fun List<String>.parseMappings(): List<Mapping> =
        this.foldIndexed(listOf<Mapping>() to null as Mapping?) { index, (acc, current), nextLine ->
            when {
                nextLine.isBlank() -> acc + current!! to null as Mapping?
                current == null -> acc to nextLine.parseMapping()
                index == this.lastIndex -> acc + current.copy(ranges = current.ranges + nextLine.parseRange()) to null
                else -> acc to current.copy(ranges = current.ranges + nextLine.parseRange())
            }
        }.first

    private fun String.parseMapping(): Mapping {
        val (from, to) = this.removeSuffix(" map:").split("-to-")
        return Mapping(from, to)
    }

    private fun String.parseRange(): MappingRange {
        val (dest, source, length) = numberSpacesRegex.findAll(this).map { it.value.trim().toLong() }.toList()
        return MappingRange(dest, source, length)
    }

    data class Almanac(
        val seeds: List<SingleSeed> = listOf(),
        val rangeSeeds: List<RangeSeed> = listOf(),
        val mappings: List<Mapping> = listOf()
    ) {

        private val _mappings by lazy { mappings.associateBy { it.from } }

        fun findLocation(from: String, source: Long, length: Long = 0): Long {
            fun internal(mapping: Mapping?, source: Long, result: Location): Location =
                when {
                    mapping == null -> result
                    mapping.to == "location" -> Found(mapping.map(source))
                    else -> internal(_mappings[mapping.to], mapping.map(source), result)
                }


            var result: Location = Location.NotFound

            (source .. source + length).forEach {
                val r = internal(_mappings[from], it, Location.NotFound)
                if (r < result) {
                    result = r
                }
            }

            return (result as Found).value


        }

        fun findLowest(): Long =
            seeds.map { findLocation("seed", it.value) }
                .min()

        fun findLowestPartTwo(): Long =
            rangeSeeds.map { findLocation("seed", it.start, it.length) }
                .min()
    }

    data class SingleSeed(val value: Long)
    data class RangeSeed(val start: Long, val length: Long)

    data class Mapping(val from: String, val to: String, val ranges: List<MappingRange> = listOf()) {

        fun map(source: Long): Long =
            ranges
                .filter { source in it.source..it.source + it.length }
                .map { it.destination + (source - it.source) }
                .firstOrNull() ?: source
    }

    data class MappingRange(val destination: Long, val source: Long, val length: Long)

    sealed interface Location {

        operator fun compareTo(other: Location): Int

        data class Found(val value: Long) : Location {
            override operator fun compareTo(other: Location): Int =
                when {
                    other is Found -> this.value.compareTo(other.value)
                    else -> this.value.compareTo(Long.MAX_VALUE)
                }
        }
        data object NotFound : Location {
            override operator fun compareTo(other: Location): Int =
                when {
                    other is Found -> Long.MAX_VALUE.compareTo(other.value)
                    else -> Long.MAX_VALUE.compareTo(Long.MAX_VALUE)
                }
        }
    }

    private val numberSpacesRegex = "\\b\\d+\\b".toRegex()
}