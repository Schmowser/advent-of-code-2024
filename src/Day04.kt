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

        val regex1 = "M.S".toRegex()
        val regex2 = "S.M".toRegex()
        val regex3 = "M.M".toRegex()
        val regex4 = "S.S".toRegex()

        val lineToStartToEnd = mutableListOf<MasData>()

       // Horizontal search
        input.forEachIndexed { index, it ->
            println(it)
            for (i in 0 until it.length - 2) {
                when (it[i].toString() + it[i + 2].toString()) {
                    "MS" -> lineToStartToEnd.add(MasData(index, i, i + 2, MasType.MS))
                    "MM" -> lineToStartToEnd.add(MasData(index, i, i + 2, MasType.MM))
                    "SS" -> lineToStartToEnd.add(MasData(index, i, i + 2, MasType.SS))
                    "SM" -> lineToStartToEnd.add(MasData(index, i, i + 2, MasType.SM))
                    else -> {}
                }
            }
        }

        lineToStartToEnd.forEach { str ->
            if (lineToStartToEnd.any {
                it.row == str.row + 2
                && it.startIndex == str.startIndex
                && it.endIndex == str.endIndex
                && it.type.getOpposite() == str.type
            }) {
                if (input[str.row + 1][str.startIndex + 1] == 'A') {
                    result += 1
                    //println("Treffer! $str")
                }
            }
            println(str)
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    //check(part1(testInput) == 18)
    //part1(testInput).println()
    part2(testInput).println()
    //check(part2(testInput) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    //part1(input).println()
    part2(input).println()
}

data class MasData(
    val row: Int,
    val startIndex: Int,
    val endIndex: Int,
    val type: MasType,
)

enum class MasType {
    MS,
    SM,
    MM,
    SS;

    fun getOpposite(): MasType {
        return when (this) {
            MS -> MS
            SM -> SM
            MM -> SS
            SS -> MM
        }
    }
}

fun parse(a: String): MasType {
    return if (a.startsWith("M")) {
        if (a.endsWith("M")) {
            MasType.MM
        } else {
            MasType.MS
        }
    } else {
        if (a.endsWith("M")) {
            MasType.SS
        } else {
            MasType.SS
        }
    }
}