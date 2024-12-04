fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        val regex1 = "XMAS".toRegex()
        val regex2 = "SAMX".toRegex()

        // Horizontal search
        input.forEach {
            regex1.findAll(it).forEach {
                result += 1
            }
            regex2.findAll(it).forEach {
                result += 1
            }
        }

        // Vertical search
        val transposedInput = mutableListOf<String>()
        for (i in input[0].indices) {
            val line = input.map {it[i]}.joinToString("")
            transposedInput.add(line)
        }
        transposedInput.forEach {
            regex1.findAll(it).forEach { _ ->
                result += 1
            }
            regex2.findAll(it).forEach {
                result += 1
            }
        }

        // Diagonal search
        val diagonalInput = mutableListOf<String>()
        for (i in input[0].indices) {
            val line1 = (0 .. i).map { input[i - it][it] }.joinToString("")
            diagonalInput.add(line1)
            val line2 = (0 .. i).map { input[input.size - 1 - i + it][input.size - 1 - it] }.joinToString("")
            diagonalInput.add(line2)
            val line3 = (0 .. i).map { input[i - it][input.size - 1 - it] }.joinToString("")
            diagonalInput.add(line3)
            val line4 = (0 .. i).map { input[input.size - 1 - i + it][it] }.joinToString("")
            diagonalInput.add(line4)
        }
        diagonalInput.removeAt(diagonalInput.size - 1)
        diagonalInput.removeAt(diagonalInput.size - 3)
        diagonalInput.forEach {
            regex1.findAll(it).forEach { _ ->
                result += 1
            }
            regex2.findAll(it).forEach {
                result += 1
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        val regex1 = "MAS".toRegex()
        val regex2 = "SAM".toRegex()

        // Diagonal search
        val diagonalInput = mutableListOf<String>()
        for (i in input[0].indices) {
            val line1 = (0 .. i).map { input[i - it][it] }.joinToString("")
            diagonalInput.add(line1)
            val line2 = (0 .. i).map { input[input.size - 1 - i + it][input.size - 1 - it] }.joinToString("")
            diagonalInput.add(line2)
            val line3 = (0 .. i).map { input[i - it][input.size - 1 - it] }.joinToString("")
            diagonalInput.add(line3)
            val line4 = (0 .. i).map { input[input.size - 1 - i + it][it] }.joinToString("")
            diagonalInput.add(line4)
        }
        diagonalInput.removeAt(diagonalInput.size - 1)
        diagonalInput.removeAt(diagonalInput.size - 3)
        diagonalInput.forEach {
            regex1.findAll(it).forEach { _ ->
                result += 1
            }
            regex2.findAll(it).forEach {
                result += 1
            }
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    //check(part1(testInput) == 18)
    part1(testInput).println()
    //check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    //part2(input).println()
}
