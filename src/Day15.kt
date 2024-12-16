fun main() {
    fun part1(input: List<String>): Int {
        val state = mutableListOf<CharArray>()
        var robot = 0 to 0
        var moves = ""

        input.forEachIndexed { index, line ->
            if (line.startsWith("#")) {
                state.add(line.toCharArray())
                if (line.contains("@")) {
                    robot = index to line.indexOf('@')
                }
            } else {
                moves += line
            }
        }

        var step = 0
        while (moves.isNotBlank()) {
//            state.forEach {
//                println(it.joinToString())
//            }
            println(robot)
//            println(moves)
            println("")

            val move = moves[0]
            println("Move: $move")
            val dir = when (move) {
                '<' -> 0 to -1
                '^' -> -1 to 0
                '>' -> 0 to 1
                'v' -> 1 to 0
                else -> 0 to 0
            }

            val comboToMove = mutableListOf('@')
            var comboHead = robot

            while (comboToMove.isNotEmpty()) {
                when (state[comboHead.first + dir.first][comboHead.second + dir.second]) {
                    '.' -> {
                        state[comboHead.first + dir.first][comboHead.second + dir.second] = comboToMove.last()
                        state[comboHead.first][comboHead.second] = '.'
                        if (comboToMove.last() == '@') {
                            robot = robot.first + dir.first to robot.second + dir.second
                        } else {
                            comboHead = comboHead.first - dir.first to comboHead.second - dir.second
                        }
                        comboToMove.removeLast()
                    }
                    '#' -> {
                        comboToMove.clear()
                    }
                    'O' -> {
                        comboToMove += 'O'
                        comboHead = comboHead.first + dir.first to comboHead.second + dir.second
                    }
                    else -> {}
                }
            }

            moves = moves.substring(1)
        }

        var result = 0

        state.forEachIndexed { row, chars ->
            chars.forEachIndexed { col, c ->
                if (c == 'O') {
                    result += 100 * row + col
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val state = mutableListOf<CharArray>()
        var robot = 0 to 0
        var moves = ""

        input.forEachIndexed { index, line ->
            if (line.startsWith("#")) {
                var realLine = ""

                line.forEach {
                    realLine += when (it) {
                        '.' -> ".."
                        '#' -> "##"
                        'O' -> "[]"
                        '@' -> "@."
                        else -> ""
                    }
                }
                state.add(realLine.toCharArray())
                if (realLine.contains("@")) {
                    robot = index to realLine.indexOf('@')
                }
            } else {
                moves += line
            }
        }

        var step = 0
        while (moves.isNotBlank()) {
//            state.forEach {
//                println(it.joinToString())
//            }
//            println(robot)
//            println(moves)
            println("")

            step++
            val move = moves[0]
            println("Move $step: $move")
            val dir = when (move) {
                '<' -> 0 to -1
                '^' -> -1 to 0
                '>' -> 0 to 1
                'v' -> 1 to 0
                else -> 0 to 0
            }

            if (dir.first == 0) {
                val comboToMove = mutableListOf('@')
                var comboHead = robot

                if (step == 313) {
                    println("ERROR")
                }
                while (comboToMove.isNotEmpty()) {
                    when (state[comboHead.first + dir.first][comboHead.second + dir.second]) {
                        '.' -> {
                            state[comboHead.first + dir.first][comboHead.second + dir.second] = comboToMove.last()
                            state[comboHead.first][comboHead.second] = '.'
                            if (comboToMove.last() == '@') {
                                robot = robot.first + dir.first to robot.second + dir.second
                            } else {
                                comboHead = comboHead.first - dir.first to comboHead.second - dir.second
                            }
                            comboToMove.removeLast()
                        }
                        '#' -> {
                            comboToMove.clear()
                        }
                        '[' -> {
                            comboToMove += '['
                            comboHead = comboHead.first + dir.first to comboHead.second + dir.second
                        }
                        ']' -> {
                            comboToMove += ']'
                            comboHead = comboHead.first + dir.first to comboHead.second + dir.second
                        }
                        else -> {}
                    }
                }
            } else {
                var movingCombo = mutableListOf(mutableListOf('@'))
                var comboToMove = (robot.first .. robot.first) to (robot.second .. robot.second)

                if (step == 313) {
                    println("ERROR")
                }
                while (true) {
                    val nextUp = comboToMove.second.map {
                        state[comboToMove.first.last + dir.first][it]
                    }
                    when {
                        nextUp.all { it == '.' } -> {
                            if (dir.first == 1) {
                                for (i in comboToMove.first.last downTo comboToMove.first.first) {
                                    for (j in comboToMove.second) {
//                                        if (i - comboToMove.first.first != 0) {
                                        try {
                                            val nextMovingCombo = movingCombo[i - comboToMove.first.first + 1][j - comboToMove.second.first]
                                            if (movingCombo[i - comboToMove.first.first][j - comboToMove.second.first] == '.') {
                                                if (nextMovingCombo != '.') {
                                                    state[i + dir.first][j] = movingCombo[i - comboToMove.first.first][j - comboToMove.second.first]
                                                }
                                            } else {
                                                state[i + dir.first][j] = movingCombo[i - comboToMove.first.first][j - comboToMove.second.first]
                                            }
                                        } catch (_: Exception) {
                                            state[i + dir.first][j] = movingCombo[i - comboToMove.first.first][j - comboToMove.second.first]
                                        }
                                    }
                                }
                                for (j in comboToMove.second) {
                                    if (state[comboToMove.first.first][j] == '@') {
                                        state[comboToMove.first.first][j] = '.'
                                    }
                                }
                            } else {
                                for (i in comboToMove.first.last .. (comboToMove.first.first)) {
                                    for (j in comboToMove.second) {
                                        try {
                                            val nextMovingCombo = movingCombo[movingCombo.size - (i - comboToMove.first.last)][j - comboToMove.second.first]
                                            if (movingCombo[movingCombo.size - 1 - (i - comboToMove.first.last)][j - comboToMove.second.first] == '.') {
                                                if (nextMovingCombo != '.') {
                                                    state[i + dir.first][j] = movingCombo[movingCombo.size - 1 - (i - comboToMove.first.last)][j - comboToMove.second.first]
                                                }
                                            } else {
                                                state[i + dir.first][j] = movingCombo[movingCombo.size - 1 - (i - comboToMove.first.last)][j - comboToMove.second.first]
                                            }
                                        } catch (_: Exception) {
                                            state[i + dir.first][j] = movingCombo[movingCombo.size - 1 - (i - comboToMove.first.last)][j - comboToMove.second.first]
                                        }
                                    }
                                }
                                for (j in comboToMove.second) {
                                    if (state[comboToMove.first.first][j] == '@') {
                                        state[comboToMove.first.first][j] = '.'
                                    }
                                }
                            }
                            robot = robot.first + dir.first to robot.second + dir.second
                            break
                        }
                        nextUp.all { it == '#' } -> {
                            break
                        }
                        nextUp.any { it == '[' || it == ']' } -> {
                            val lastC = nextUp.indexOfLast { it == '[' }
                            val firstC = nextUp.indexOf(']')

                            if (lastC != -1 && firstC == -1) {
                                if (movingCombo.last()[lastC] != '.') {
                                    if (lastC == nextUp.lastIndex) {
                                        movingCombo.forEach { it.add('.') }
                                        movingCombo.add(nextUp.plus(']').toMutableList())
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to comboToMove.second.first .. (comboToMove.second.last + 1)
                                    } else {
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to comboToMove.second
                                        movingCombo.add(nextUp.toMutableList())
                                    }
                                }
                            }
                            if (firstC != -1 && lastC == -1) {
                                if (movingCombo.last()[firstC] != '.') {
                                    if (firstC == 0) {
                                        movingCombo.forEach { it.add(0, '.') }
                                        movingCombo.add(mutableListOf('[').plus(nextUp).toMutableList())
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to (comboToMove.second.first - 1) .. comboToMove.second.last
                                    } else {
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to comboToMove.second
                                        movingCombo.add(nextUp.toMutableList())
                                    }
                                }
                            }
                            if (firstC != -1 && lastC != -1) {
                                if (movingCombo.last()[lastC] != '.' && movingCombo.last()[firstC] != '.') {
                                    if (lastC == nextUp.lastIndex && firstC != 0) {
                                        movingCombo.forEach { it.add('.') }
                                        movingCombo.add(nextUp.plus(']').toMutableList())
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to comboToMove.second.first .. (comboToMove.second.last + 1)
                                    }
                                    if (firstC == 0 && lastC != nextUp.lastIndex) {
                                        movingCombo.forEach { it.add(0, '.') }
                                        movingCombo.add(mutableListOf('[').plus(nextUp).toMutableList())
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to (comboToMove.second.first - 1) .. comboToMove.second.last
                                    }
                                    if (lastC == nextUp.lastIndex && firstC == 0) {
                                        movingCombo.forEach {
                                            it.add(0, '.')
                                            it.add('.')
                                        }
                                        movingCombo.add(mutableListOf('[').plus(nextUp).plus(']').toMutableList())
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to (comboToMove.second.first - 1) .. (comboToMove.second.last + 1)
                                    }
                                    if (lastC != nextUp.lastIndex && firstC != 0) {
                                        comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to comboToMove.second
                                        movingCombo.add(nextUp.toMutableList())
                                    }
                                } else {
                                    comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to comboToMove.second
                                    val nextLine = nextUp.mapIndexed { index, c ->
                                        if (c != '.') {
                                            if (movingCombo.last()[index] != '.') {
                                                c
                                            } else {
                                                '.'
                                            }
                                        } else {
                                            '.'
                                        }
//                                        if (index != firstC || index != lastC) {
//                                            '.'
//                                        } else {
//                                            c
//                                        }
                                    }.toMutableList()
                                    movingCombo.add(nextLine)
                                }
                            }
                        }
                        else -> {
                            comboToMove = comboToMove.first.first.. (comboToMove.first.last + dir.first) to comboToMove.second
                            movingCombo.add(nextUp.toMutableList())
                        }
                    }
                }
            }

            moves = moves.substring(1)
        }

        state.forEach {
            println(it.joinToString())
        }

        var result = 0

        state.forEachIndexed { row, chars ->
            chars.forEachIndexed { col, c ->
                if (c == '[') {
                    result += 100 * row + col
                }
            }
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day15_test")
//    part1(testInput).println()
//    part2(testInput).println()

    val testInput2 = readInput("Day15_test2")
    //    part1(testInput2).println()
//        part2(testInput2).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day15")
//    part1(input).println()
    part2(input).println()
}