import kotlin.math.round

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        val ruleSet = mutableSetOf<Pair<String, String>>()

        input.forEach { line ->
            if (line.contains("|")) {
                val rule = line.split("|")
                ruleSet.add(rule[0] to rule[1])
            } else if (line.contains(",")) {
                val page = line.split(",")
                for (i in page.indices) {
                    for (j in i + 1 until page.size) {
                        if (page[i] to page[j] !in ruleSet) {
                            return@forEach
                        }
                    }
                }
                result += page[page.size / 2].toInt()
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val ruleSet = mutableSetOf<Pair<String, String>>()
        val incorrectOrderedPagesWithError = mutableSetOf<Pair<List<String>, Int>>()

        input.forEach { line ->
            if (line.contains("|")) {
                val rule = line.split("|")
                ruleSet.add(rule[0] to rule[1])
            } else if (line.contains(",")) {
                val page = line.split(",")
                for (i in page.indices) {
                    for (j in i + 1 until page.size) {
                        if (page[i] to page[j] !in ruleSet) {
                            incorrectOrderedPagesWithError.add(page to j)
                            return@forEach
                        }
                    }
                }
            }
        }

        val fixedPages = mutableSetOf<List<String>>()

        while (incorrectOrderedPagesWithError.isNotEmpty()) {
            val newPages = incorrectOrderedPagesWithError.map {
                val newPage = it.first.toMutableList()
                (newPage[it.second - 1] to newPage[it.second]).apply {
                    newPage[it.second - 1] = second
                    newPage[it.second] = first
                }
                newPage
            }

            incorrectOrderedPagesWithError.clear()

            newPages.forEach { page ->
                for (i in page.indices) {
                    for (j in i + 1 until page.size) {
                        if (page[i] to page[j] !in ruleSet) {
                            incorrectOrderedPagesWithError.add(page to j)
                            return@forEach
                        }
                    }
                }
                fixedPages.add(page)
            }
        }

        return fixedPages.sumOf { page ->
            page[page.size / 2].toInt()
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}