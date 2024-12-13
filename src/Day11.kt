import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        var stones = input.first().split(' ').map { it.toBigInteger() }

        repeat(25) {
            val tempStones = mutableListOf<BigInteger>()
            for (stone in stones) {
                when {
                    stone == BigInteger.ZERO -> tempStones.add(BigInteger.ONE)
                    stone.toString().length.mod(2) == 0 -> tempStones.addAll(
                        listOf(
                            stone.toString().substring(0, stone.toString().length / 2).toBigInteger(),
                            stone.toString().substring(stone.toString().length / 2, stone.toString().length).toBigInteger(),
                        )
                    )
                    else -> tempStones.add(stone * 2024.toBigInteger())
                }
            }
            stones = tempStones
        }

        return stones.size
    }

    fun part2(input: List<String>): Int {
        var stones = input.first().split(' ').map { it.toBigInteger() }

        repeat(75) {
            println("Blink $it")
            val tempStones = mutableListOf<BigInteger>()
            for (stone in stones) {
                when {
                    stone == BigInteger.ZERO -> tempStones.add(BigInteger.ONE)
                    stone.toString().length.mod(2) == 0 -> tempStones.addAll(
                        listOf(
                            stone.toString().substring(0, stone.toString().length / 2).toBigInteger(),
                            stone.toString().substring(stone.toString().length / 2, stone.toString().length).toBigInteger(),
                        )
                    )
                    else -> tempStones.add(stone * 2024.toBigInteger())
                }
            }
            stones = tempStones
        }

        return stones.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day11_test")
//    part1(testInput).println()
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day11")
    part1(input).println()
//    part2(input).println()

    // 0 -> 1 -> 2024 -> 20 24 -> 2 0 2 4 -> 4048 (...) 4048 8096 -> 40 48 (...) 40 48 80 96 -> 4 0 4 8 (...) 4 0 4 8 8 0 9 6 ->
    // 1 -> 1 -> 1 -> 2 -> 4 -> 4 -> 
}