fun main() {
    fun part1(input: List<String>): Int {
        var result = 1

        val obstacleRegex = "#".toRegex()
        val guardChar = '^'
        var guardPosition: Pair<Int, Int> = -1 to -1
        val setVisitedPositions = mutableSetOf<Pair<Int, Int>>()

        val obstacles = input.flatMapIndexed { index, line ->
            if (line.contains("^")) {
                guardPosition = index to line.indexOf(guardChar)
            }
            obstacleRegex.findAll(line)
                .map { index to it.range.first }
        }

        println(obstacles)
        println(guardPosition)

        while (true) {
            // Go Up
            println("Go Up")
            val obstaclesInSightU = mutableListOf(-1 to guardPosition.second, input.size to guardPosition.second)
            obstaclesInSightU.addAll(obstacles.filter { it.second == guardPosition.second })
            obstaclesInSightU.add(guardPosition)
            obstaclesInSightU.sortBy { it.first }

            println(obstaclesInSightU)

            val obstacleRangesU = obstaclesInSightU.windowed(2, 1, false).map { IntRange(it[0].first + 1, it[1].first) }

            println(obstacleRangesU)

            val currentRangeU = obstacleRangesU.last { guardPosition.first in it }
//            result += currentRangeU.last - currentRangeU.first
            for (i in currentRangeU) {
                setVisitedPositions.add(i to guardPosition.second)
            }
            guardPosition = currentRangeU.first to guardPosition.second

            println("XXX $setVisitedPositions")
            println(guardPosition)

            if (guardPosition.first == 0 || guardPosition.first == input.size - 1 || guardPosition.second == 0 || guardPosition.second == input[0].length - 1) {
                break
            }

            // Go Right
            println("Go Right")
            val obstaclesInSightR = mutableListOf(guardPosition.first to -1, guardPosition.first to input[0].length)
            obstaclesInSightR.addAll(obstacles.filter { it.first == guardPosition.first })
            obstaclesInSightR.add(guardPosition)
            obstaclesInSightR.sortBy { it.second }

            println(obstaclesInSightR)

            val obstacleRangesR = obstaclesInSightR.windowed(2, 1, false).map { IntRange(it[0].second, it[1].second - 1) }

            println(obstacleRangesR)

            val currentRangeR = obstacleRangesR.last { guardPosition.second in it }
            println(currentRangeR)
//            result += currentRangeR.last - currentRangeR.first
            for (i in currentRangeR) {
                setVisitedPositions.add(guardPosition.first to i)
            }
            guardPosition = guardPosition.first to currentRangeR.last

            println("XXX $setVisitedPositions")
            println(guardPosition)

            if (guardPosition.first == 0 || guardPosition.first == input.size - 1 || guardPosition.second == 0 || guardPosition.second == input[0].length - 1) {
                break
            }

            // Go Down
            println("Go Down")
            val obstaclesInSightD= mutableListOf(-1 to guardPosition.second, input.size to guardPosition.second)
            obstaclesInSightD.addAll(obstacles.filter { it.second == guardPosition.second })
            obstaclesInSightD.add(guardPosition)
            obstaclesInSightD.sortBy { it.first }

            println(obstaclesInSightD)

            val obstacleRangesD = obstaclesInSightD.windowed(2, 1, false).map { IntRange(it[0].first, it[1].first - 1) }

            println(obstacleRangesD)

            val currentRangeD = obstacleRangesD.first { guardPosition.first in it }
            println(currentRangeD)
//            result += currentRangeD.last - currentRangeD.first
            for (i in currentRangeD) {
                setVisitedPositions.add(i to guardPosition.second)
            }
            guardPosition = currentRangeD.last to guardPosition.second

            println("XXX $setVisitedPositions")
            println(guardPosition)

            if (guardPosition.first == 0 || guardPosition.first == input.size - 1 || guardPosition.second == 0 || guardPosition.second == input[0].length - 1) {
                break
            }

            // Go Left
            println("Go Left")
            val obstaclesInSightL = mutableListOf(guardPosition.first to -1, guardPosition.first to input[0].length)
            obstaclesInSightL.addAll(obstacles.filter { it.first == guardPosition.first })
            obstaclesInSightL.add(guardPosition)
            obstaclesInSightL.sortBy { it.second }

            println(obstaclesInSightL)

            val obstacleRangesL = obstaclesInSightL.windowed(2, 1, false).map { IntRange(it[0].second + 1, it[1].second) }

            println(obstacleRangesL)

            val currentRangeL = obstacleRangesL.first { guardPosition.second in it }
            println(currentRangeL)
//            result += currentRangeL.last - currentRangeL.first
            for (i in currentRangeL) {
                setVisitedPositions.add(guardPosition.first to i)
            }
            guardPosition = guardPosition.first to currentRangeL.first

            println("XXX $setVisitedPositions")
            println(guardPosition)

            if (guardPosition.first == 0 || guardPosition.first == input.size - 1 || guardPosition.second == 0 || guardPosition.second == input[0].length - 1) {
                break
            }
        }

        return setVisitedPositions.size
    }

    fun part2(input: List<String>): Int {
        var result = 0

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    part1(testInput).println()
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
//    part2(input).println()
}