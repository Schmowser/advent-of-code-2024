fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { str ->
            val regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
            regex.findAll(str).forEach {
                val substr = str.substring(it.range)
                val twoFactors = substr.removePrefix("mul(").removeSuffix(")").split(",")
                println(substr)
                result += twoFactors[0].toInt() * twoFactors[1].toInt()
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        var multEnabled = true

        input.forEach { str ->
            val regex = "(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\))|(don't\\(\\))".toRegex()
            regex.findAll(str).forEach {
                when(it.value) {
                    "do()" -> multEnabled = true
                    "don't()" -> multEnabled = false
                    else -> {
                        if (multEnabled) {
                            val substr = str.substring(it.range)
                            val twoFactors = substr.removePrefix("mul(").removeSuffix(")").split(",")
                            println(substr)
                            result += twoFactors[0].toInt() * twoFactors[1].toInt()
                        }
                    }
                }
            }
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    //part1(testInput).println()
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
