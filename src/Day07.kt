import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): BigInteger {
        var result = BigInteger.ZERO

        input.forEach {
            val split = it.split(": ")
            val calculationResult = split[0].toBigInteger()
            val factors = split[1].split(" ").map { it.toInt().toBigInteger() }.reversed().toMutableList()
            val validOperatorCombinations = mutableListOf<Pair<MutableList<String>, BigInteger>>(
                emptyList<String>().toMutableList() to calculationResult,
            )
            do {
                println(validOperatorCombinations)
                println("Factors: $factors")
                val tempOperatorCombinations = mutableListOf<Pair<MutableList<String>, BigInteger>>()
                validOperatorCombinations.forEach { tempOperatorCombinations.add(it) }
                for (combi in tempOperatorCombinations) {
                    if (combi.second - factors.first() >= BigInteger.ZERO) {
                        validOperatorCombinations.add(
                            combi.first.plus("+").toMutableList() to (combi.second - factors.first())
                        )
                    }
                    if (combi.second.mod(factors.first()) == BigInteger.ZERO) {
                        validOperatorCombinations.add(
                            combi.first.plus("*").toMutableList() to (combi.second / factors.first())
                        )
                    }
                    validOperatorCombinations.remove(combi)
                }
                if (factors.isNotEmpty()) {
                    factors.removeAt(0)
                }
            } while ((factors.isNotEmpty()))

            if (validOperatorCombinations.any { it.second == BigInteger.ZERO }) {
                result += calculationResult
            }
        }

        return result
    }

    fun part2(input: List<String>): BigInteger {
        var result = BigInteger.ZERO

        input.forEach {
            val split = it.split(": ")
            val calculationResult = split[0].toBigInteger()
            val factors = split[1].split(" ").map { it.toInt().toBigInteger() }.reversed().toMutableList()
            val validOperatorCombinations = mutableListOf<Pair<MutableList<String>, BigInteger>>(
                emptyList<String>().toMutableList() to calculationResult,
            )
            do {
                println(validOperatorCombinations)
                println("Factors: $factors")
                val tempOperatorCombinations = mutableListOf<Pair<MutableList<String>, BigInteger>>()
                validOperatorCombinations.forEach { tempOperatorCombinations.add(it) }
                for (combi in tempOperatorCombinations) {
                    if (combi.second - factors.first() >= BigInteger.ZERO) {
                        validOperatorCombinations.add(
                            combi.first.plus("+").toMutableList() to (combi.second - factors.first())
                        )
                    }
                    if (combi.second.mod(factors.first()) == BigInteger.ZERO) {
                        validOperatorCombinations.add(
                            combi.first.plus("*").toMutableList() to (combi.second / factors.first())
                        )
                    }
                    if (combi.second.toString().endsWith(factors.first().toString())) {
                        val stringWithoutSuffix = combi.second.toString().removeSuffix(factors.first().toString())
                        validOperatorCombinations.add(
                            combi.first.plus("||").toMutableList() to if (stringWithoutSuffix.isEmpty()) BigInteger.ZERO else stringWithoutSuffix.toBigInteger()
                        )
                    }
                    validOperatorCombinations.remove(combi)
                }
                if (factors.isNotEmpty()) {
                    factors.removeAt(0)
                }
            } while ((factors.isNotEmpty()))

            if (validOperatorCombinations.any { it.second == BigInteger.ZERO }) {
                result += calculationResult
            }
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day07_test")
//    part1(testInput).println()
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day07")
//    part1(input).println()
    part2(input).println()
}