import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): BigInteger {
        val inputString = input.first()

        val fileToSpaceList = mutableListOf<BigInteger?>()
        var file = BigInteger.ZERO
        inputString.mapIndexed { index, c ->
            val cInt = c.toString().toInt()
            if (index.mod(2) == 0) {
                repeat(cInt) {
                    fileToSpaceList.add(file)
                }
                file++
            } else {
                repeat(cInt) {
                    fileToSpaceList.add(null)
                }
            }
        }
        println(file)

        val resultList = mutableListOf<BigInteger>()
        var i = 0
        var j = fileToSpaceList.size - 1
        while (i <= j) {
            if (fileToSpaceList[i] != null) {
                resultList.add(fileToSpaceList[i]!!)
                i++
            } else {
                if (fileToSpaceList[j] != null) {
                    resultList.add(fileToSpaceList[j]!!)
                    i++
                }
                j--
            }
        }

        var result = BigInteger.ZERO
        resultList.forEachIndexed { index, c ->
            result = result.plus( c.times(index.toBigInteger()))
        }

        return result
    }

    fun part2(input: List<String>): BigInteger {
        val inputString = input.first()

        val fileToSpaceList = mutableListOf<BigInteger?>()
        var file = BigInteger.ZERO
        inputString.mapIndexed { index, c ->
            val cInt = c.toString().toInt()
            if (index.mod(2) == 0) {
                repeat(cInt) {
                    fileToSpaceList.add(file)
                }
                file++
            } else {
                repeat(cInt) {
                    fileToSpaceList.add(null)
                }
            }
        }
        println(file)

        val resultList = mutableListOf<BigInteger>()
        var i = 0
        var j = fileToSpaceList.size - 1
        while (i <= j) {
            if (fileToSpaceList[i] != null) {
                resultList.add(fileToSpaceList[i]!!)
                i++
            } else {
                val k = file
                

            }
        }

        var result = BigInteger.ZERO
        resultList.forEachIndexed { index, c ->
            result = result.plus( c.times(index.toBigInteger()))
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day09_test")
    part1(testInput).println()

    val testInput2 = readInput("Day09_test2")
//    part1(testInput2).println()
    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day09")
//    part1(input).println()
//    part2(input).println()
}