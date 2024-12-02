fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            val intList = line.split(" ").map { it.toInt() }
            val diffList = mutableListOf<Int>()

            for (i in 0 until (intList.size - 1)) {
                diffList.add(intList[i] - intList[i + 1])
            }
            println(diffList)

            if (diffList.all { it in 1..3 } || diffList.all { it in -3..-1 }) {
                println("+1")
                result += 1
                println(result)
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            val intList = ArrayList(line.split(" ").map { it.toInt() })
            val listOfCandidateList = arrayListOf<ArrayList<Int>>()
            for (i in 0 until intList.size) {
                val temporaryList = ArrayList(intList.toList())
                temporaryList.removeAt(i)
                listOfCandidateList.add(temporaryList)
            }

            println("Candidate list: $listOfCandidateList")

            if (listOfCandidateList.any {
                val diffList = mutableListOf<Int>()

                for (i in 0 until (it.size - 1)) {
                    diffList.add(it[i] - it[i + 1])
                }
                println(diffList)

                diffList.all { it in 1..3 } || diffList.all { it in -3..-1 }
            }) {
                println("+1")
                result += 1
                println(result)
            }
        }
        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
