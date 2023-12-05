package be.moac.aoc2023

import be.moac.aoc2023.Day05.Location.Found
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() {
    val input: List<String> = "/day05_input.txt".readLines { it }

    println("Part one: ${timed(1) { Day05 partOne input }}")
    println("Part two: ${timed { Day05 partTwo input }}")
}

object Day05 {

    infix fun partOne(input: List<String>): Long =
        input.parse().findLowest()



    infix fun partTwo(input: List<String>): Int  = 0


    fun List<String>.parse(): Almanac = Almanac(
        seeds = this.first().parseSeeds(),
        mappings = this.drop(2).parseMappings()
    )

    private fun String.parseSeeds(): List<Seed> =
        numberSpacesRegex.findAll(this.removePrefix("seeds:"))
            .map { Seed(it.value.trim().toLong()) }
            .toList()

    private fun List<String>.parseMappings(): List<Mapping> =
        this.foldIndexed(listOf<Mapping>() to null as Mapping?) {
            index, (acc, current), nextLine ->
                when {
                    nextLine.isBlank() -> acc + current!! to null as Mapping?
                    current == null -> acc to nextLine.parseMapping()
                    index == this.lastIndex -> acc + current.copy(ranges = current.ranges + nextLine.parseRange())to null
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

    data class Almanac(val seeds: List<Seed> = listOf(), val mappings: List<Mapping> = listOf()) {

        private val _mappings by lazy { mappings.associateBy { it.from } }

        fun findLocation(from: String, source: Long): Location {
            fun internal(mapping: Mapping?, source: Long, result: Location): Location =
                when {
                    mapping == null -> result
                    mapping.to == "location" -> Found(mapping.map(source))
                    else -> internal(_mappings[mapping.to], mapping.map(source), result)
                }

            return internal(_mappings[from], source, Location.NotFound)
        }

        fun findLowest(): Long =
            seeds.map { findLocation("seed", it.value) }
                .filterIsInstance<Found>()
                .minOf { it.value }
    }
    data class Seed(val value: Long)
    data class Mapping(val from: String, val to: String, val ranges: List<MappingRange> = listOf()) {

        fun map(source: Long) : Long =
            ranges
                .filter { source in it.source .. it.source + it.length }
                .map { it.destination + (source - it.source) }
                .firstOrNull() ?: source
    }

    data class MappingRange(val destination: Long, val source: Long,  val length: Long)

    sealed interface Location {
        data class Found(val value: Long): Location
        data object NotFound: Location
    }

    private val numberSpacesRegex = "\\b\\d+\\b".toRegex()
}