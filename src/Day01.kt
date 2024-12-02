fun main() {
    fun part1(input: List<String>): Int {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()

        input.map { line ->
            val lineInts = line.split("   ")
            firstList.add(lineInts[0].toInt())
            secondList.add(lineInts[1].toInt())
        }

        firstList.sort()
        secondList.sort()

        var sum = 0
        for (i in 0 until firstList.size) {
            sum += Math.abs(firstList[i] - secondList[i])
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()

        input.map { line ->
            val lineInts = line.split("   ")
            firstList.add(lineInts[0].toInt())
            secondList.add(lineInts[1].toInt())
        }

        val numberOfIntInSecondList = mutableMapOf<Int, Int>()
        secondList.forEach {
            numberOfIntInSecondList[it] = numberOfIntInSecondList[it]?.plus(1) ?: 1
        }

        var result = 0
        firstList.forEach {
            result += it * (numberOfIntInSecondList[it] ?: 0)
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
