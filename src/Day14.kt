fun main() {
    fun part1(input: List<String>): Int {
        var numberOfRobotsInFirstQuadrant = 0
        var numberOfRobotsInSecondQuadrant = 0
        var numberOfRobotsInThirdQuadrant = 0
        var numberOfRobotsInForthQuadrant = 0

        val steps = 100
//        val numberOfCols = 11
//        val numberOfRows = 7
        val numberOfCols = 101
        val numberOfRows = 103

        input.forEach { line ->
            val substrs = line.removePrefix("p=").split(" v=")
            val startPos = substrs[0].split(",").let { it[0].toInt() to it[1].toInt() }
            val moveDir = substrs[1].split(",").let { it[0].toInt() to it[1].toInt() }
            println("startPos: $startPos, moveDir: $moveDir")

            val endPos = (startPos.first + steps * moveDir.first).mod(numberOfCols) to (startPos.second + steps * moveDir.second).mod(numberOfRows)
            println("endPos: $endPos")

            if (endPos.first < (numberOfCols - 1)/2) {
                if (endPos.second < (numberOfRows - 1)/2) {
                    numberOfRobotsInFirstQuadrant++
                } else if (endPos.second > (numberOfRows - 1)/2) {
                    numberOfRobotsInSecondQuadrant++
                }
            } else if (endPos.first > (numberOfCols - 1)/2) {
                if (endPos.second < (numberOfRows - 1)/2) {
                    numberOfRobotsInThirdQuadrant++
                } else if (endPos.second > (numberOfRows - 1)/2) {
                    numberOfRobotsInForthQuadrant++
                }
            }
        }

        return numberOfRobotsInFirstQuadrant * numberOfRobotsInSecondQuadrant * numberOfRobotsInThirdQuadrant * numberOfRobotsInForthQuadrant
    }

    fun part2(input: List<String>): Int {
        var steps = 0

        val numberOfCols = 101
        val numberOfRows = 103

        while (true) {
            steps++
            println("steps: $steps")

            val endPoses = input.map { line ->
                val substrs = line.removePrefix("p=").split(" v=")
                val startPos = substrs[0].split(",").let { it[0].toInt() to it[1].toInt() }
                val moveDir = substrs[1].split(",").let { it[0].toInt() to it[1].toInt() }

                (startPos.first + steps * moveDir.first).mod(numberOfCols) to (startPos.second + steps * moveDir.second).mod(numberOfRows)
            }

            println(endPoses.groupingBy { it.first }.eachCount().filter { it.value > 20 })
            println(endPoses.groupingBy { it.second }.eachCount().filter { it.value > 20 })

            if (endPoses.groupingBy { it.first }.eachCount().filter { it.value > 20 }.isNotEmpty() && endPoses.groupingBy { it.second }.eachCount().filter { it.value > 20 }.isNotEmpty()) break
        }

        return steps
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day14_test")
//    part1(testInput).println()
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day14")
//    part1(input).println()
    part2(input).println()
}