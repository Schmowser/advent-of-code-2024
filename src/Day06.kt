fun main() {
    fun getVisitedPositions(
        guardPositionIn: Pair<Int, Int>,
        obstacles: List<Pair<Int, Int>>,
        numberOfRows: Int,
        numberOfColumns: Int,
    ): Set<Pair<Int, Int>> {
        val setVisitedPositions = mutableSetOf<Pair<Int, Int>>()
        var guardPosition = guardPositionIn

        while (true) {
            // Go Up
            val obstaclesInSightU = mutableListOf(-1 to guardPosition.second, numberOfRows to guardPosition.second)
            obstaclesInSightU.addAll(obstacles.filter { it.second == guardPosition.second })
            obstaclesInSightU.add(guardPosition)
            obstaclesInSightU.sortBy { it.first }

            val obstacleRangesU = obstaclesInSightU.windowed(2, 1, false).map { IntRange(it[0].first + 1, it[1].first) }

            val currentRangeU = obstacleRangesU.last { guardPosition.first in it }
            for (i in currentRangeU) {
                setVisitedPositions.add(i to guardPosition.second)
            }
            guardPosition = currentRangeU.first to guardPosition.second

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }

            // Go Right
            val obstaclesInSightR = mutableListOf(guardPosition.first to -1, guardPosition.first to numberOfColumns)
            obstaclesInSightR.addAll(obstacles.filter { it.first == guardPosition.first })
            obstaclesInSightR.add(guardPosition)
            obstaclesInSightR.sortBy { it.second }

            val obstacleRangesR = obstaclesInSightR.windowed(2, 1, false).map { IntRange(it[0].second, it[1].second - 1) }

            val currentRangeR = obstacleRangesR.last { guardPosition.second in it }
            for (i in currentRangeR) {
                setVisitedPositions.add(guardPosition.first to i)
            }
            guardPosition = guardPosition.first to currentRangeR.last

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }

            // Go Down
            val obstaclesInSightD= mutableListOf(-1 to guardPosition.second, numberOfRows to guardPosition.second)
            obstaclesInSightD.addAll(obstacles.filter { it.second == guardPosition.second })
            obstaclesInSightD.add(guardPosition)
            obstaclesInSightD.sortBy { it.first }

            val obstacleRangesD = obstaclesInSightD.windowed(2, 1, false).map { IntRange(it[0].first, it[1].first - 1) }
            val currentRangeD = obstacleRangesD.first { guardPosition.first in it }
            for (i in currentRangeD) {
                setVisitedPositions.add(i to guardPosition.second)
            }
            guardPosition = currentRangeD.last to guardPosition.second

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }

            // Go Left
            val obstaclesInSightL = mutableListOf(guardPosition.first to -1, guardPosition.first to numberOfColumns)
            obstaclesInSightL.addAll(obstacles.filter { it.first == guardPosition.first })
            obstaclesInSightL.add(guardPosition)
            obstaclesInSightL.sortBy { it.second }

            val obstacleRangesL = obstaclesInSightL.windowed(2, 1, false).map { IntRange(it[0].second + 1, it[1].second) }

            val currentRangeL = obstacleRangesL.first { guardPosition.second in it }
            for (i in currentRangeL) {
                setVisitedPositions.add(guardPosition.first to i)
            }
            guardPosition = guardPosition.first to currentRangeL.first

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }
        }

        return setVisitedPositions
    }

    fun part1(input: List<String>): Int {
        val obstacleRegex = "#".toRegex()
        val guardChar = '^'
        var guardPosition: Pair<Int, Int> = -1 to -1

        val obstacles = input.flatMapIndexed { index, line ->
            if (line.contains("^")) {
                guardPosition = index to line.indexOf(guardChar)
            }
            obstacleRegex.findAll(line)
                .map { index to it.range.first }
        }

        return getVisitedPositions(
            guardPosition,
            obstacles,
            input.size,
            input[0].length,
        ).size
    }

    fun hasLoop(
        guardPositionIn: Pair<Int, Int>,
        obstacles: List<Pair<Int, Int>>,
        numberOfRows: Int,
        numberOfColumns: Int,
    ): Boolean {
        val setVisitedPositions = mutableSetOf<Pair<Int, Int>>()
        var guardPosition = guardPositionIn
        val listOfTurningPoints = mutableListOf<Pair<Int, Int>>()

        while (true) {
            // Go Up
            val obstaclesInSightU = mutableListOf(-1 to guardPosition.second, numberOfRows to guardPosition.second)
            obstaclesInSightU.addAll(obstacles.filter { it.second == guardPosition.second })
            obstaclesInSightU.add(guardPosition)
            obstaclesInSightU.sortBy { it.first }

            val obstacleRangesU = obstaclesInSightU.windowed(2, 1, false).map { IntRange(it[0].first + 1, it[1].first) }

            val currentRangeU = obstacleRangesU.last { guardPosition.first in it }
            for (i in currentRangeU) {
                setVisitedPositions.add(i to guardPosition.second)
            }
            guardPosition = currentRangeU.first to guardPosition.second
            if (listOfTurningPoints.count { it == guardPosition } > 1) {
                println(listOfTurningPoints.plus(guardPosition))
                return true
            } else {
                listOfTurningPoints.add(guardPosition)
            }

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }

            // Go Right
            val obstaclesInSightR = mutableListOf(guardPosition.first to -1, guardPosition.first to numberOfColumns)
            obstaclesInSightR.addAll(obstacles.filter { it.first == guardPosition.first })
            obstaclesInSightR.add(guardPosition)
            obstaclesInSightR.sortBy { it.second }

            val obstacleRangesR = obstaclesInSightR.windowed(2, 1, false).map { IntRange(it[0].second, it[1].second - 1) }

            val currentRangeR = obstacleRangesR.last { guardPosition.second in it }
            for (i in currentRangeR) {
                setVisitedPositions.add(guardPosition.first to i)
            }
            guardPosition = guardPosition.first to currentRangeR.last
            if (listOfTurningPoints.count { it == guardPosition } > 1) {
                println(listOfTurningPoints.plus(guardPosition))
                return true
            } else {
                listOfTurningPoints.add(guardPosition)
            }

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }

            // Go Down
            val obstaclesInSightD= mutableListOf(-1 to guardPosition.second, numberOfRows to guardPosition.second)
            obstaclesInSightD.addAll(obstacles.filter { it.second == guardPosition.second })
            obstaclesInSightD.add(guardPosition)
            obstaclesInSightD.sortBy { it.first }

            val obstacleRangesD = obstaclesInSightD.windowed(2, 1, false).map { IntRange(it[0].first, it[1].first - 1) }
            val currentRangeD = obstacleRangesD.first { guardPosition.first in it }
            for (i in currentRangeD) {
                setVisitedPositions.add(i to guardPosition.second)
            }
            guardPosition = currentRangeD.last to guardPosition.second
            if (listOfTurningPoints.count { it == guardPosition } > 1) {
                println(listOfTurningPoints.plus(guardPosition))
                return true
            } else {
                listOfTurningPoints.add(guardPosition)
            }

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }

            // Go Left
            val obstaclesInSightL = mutableListOf(guardPosition.first to -1, guardPosition.first to numberOfColumns)
            obstaclesInSightL.addAll(obstacles.filter { it.first == guardPosition.first })
            obstaclesInSightL.add(guardPosition)
            obstaclesInSightL.sortBy { it.second }

            val obstacleRangesL = obstaclesInSightL.windowed(2, 1, false).map { IntRange(it[0].second + 1, it[1].second) }

            val currentRangeL = obstacleRangesL.first { guardPosition.second in it }
            for (i in currentRangeL) {
                setVisitedPositions.add(guardPosition.first to i)
            }
            guardPosition = guardPosition.first to currentRangeL.first
            if (listOfTurningPoints.count { it == guardPosition } > 1) {
                println(listOfTurningPoints.plus(guardPosition))
                return true
            } else {
                listOfTurningPoints.add(guardPosition)
            }

            if (guardPosition.first == 0 || guardPosition.first == numberOfRows - 1 || guardPosition.second == 0 || guardPosition.second == numberOfColumns - 1) {
                break
            }
        }

        return false
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val obstacleRegex = "#".toRegex()
        val guardChar = '^'
        var guardPosition: Pair<Int, Int> = -1 to -1

        val obstacles = input.flatMapIndexed { index, line ->
            if (line.contains("^")) {
                guardPosition = index to line.indexOf(guardChar)
            }
            obstacleRegex.findAll(line)
                .map { index to it.range.first }
        }

        val obstacleCandidates = getVisitedPositions(
            guardPosition,
            obstacles,
            input.size,
            input[0].length,
        ).minus(guardPosition)

        for (candidate in obstacleCandidates) {

            println("Candidate $candidate")
            if (hasLoop(
                guardPosition,
                obstacles.plus(candidate),
                input.size,
                input[0].length,
            )) {
                result += 1
            }
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
//    part1(input).println()
    part2(input).println()
}