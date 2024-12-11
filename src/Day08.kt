import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val antennas = mutableSetOf<Triple<Char, Int, Int>>()
        input.mapIndexed { row, line ->
            line.mapIndexed { col, c ->
                if (c != '.') {
                    antennas.add(Triple(c, row, col))
                }
            }
        }

        println(antennas)

        val antinodes = mutableSetOf<Pair<Int, Int>>()
        for (freq in antennas.map { it.first }.distinct()) {
            val antennasOfFreq = antennas.filter { it.first == freq }
            for (antenna in antennasOfFreq) {
                for (otherAntenna in antennasOfFreq.minus(antenna)) {
                    val diffX = otherAntenna.second - antenna.second
                    val diffY = otherAntenna.third - antenna.third
                    val antinode1 = (antenna.second - diffX) to (antenna.third - diffY)
                    val antinode2 = (otherAntenna.second + diffX) to (otherAntenna.third + diffY)
                    if (antinode1.first in input.indices && antinode1.second in 0 until input.first().length) {
                        antinodes.add(antinode1)
                    }
                    if (antinode2.first in input.indices && antinode2.second in 0 until input.first().length) {
                        antinodes.add(antinode2)
                    }
                }
            }
        }

        return antinodes.size
    }

    fun part2(input: List<String>): Int {
        val antennas = mutableSetOf<Triple<Char, Int, Int>>()
        input.mapIndexed { row, line ->
            line.mapIndexed { col, c ->
                if (c != '.') {
                    antennas.add(Triple(c, row, col))
                }
            }
        }

        println(antennas)

        val antinodes = mutableSetOf<Pair<Int, Int>>()
        for (freq in antennas.map { it.first }.distinct()) {
            val antennasOfFreq = antennas.filter { it.first == freq }
            for (antenna in antennasOfFreq) {
                for (otherAntenna in antennasOfFreq.minus(antenna)) {
                    val diffX = otherAntenna.second - antenna.second
                    val diffY = otherAntenna.third - antenna.third
                    for (i in 0 .. 50) {
                        val antinode1 = (antenna.second - i * diffX) to (antenna.third - i * diffY)
                        val antinode2 = (otherAntenna.second + i * diffX) to (otherAntenna.third + i * diffY)
                        if (antinode1.first in input.indices && antinode1.second in 0 until input.first().length) {
                            antinodes.add(antinode1)
                        }
                        if (antinode2.first in input.indices && antinode2.second in 0 until input.first().length) {
                            antinodes.add(antinode2)
                        }
                    }
                }
            }
        }

        return antinodes.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day08_test")
//    part1(testInput).println()
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day08")
//    part1(input).println()
    part2(input).println()
}