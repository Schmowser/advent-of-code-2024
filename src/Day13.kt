import java.math.BigInteger
import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        var x1 = 0
        var x2 = 0
        var y1 = 0
        var y2 = 0
        var c1 = 0
        var c2 = 0

        for (i in input.indices) {
            val line = input[i]
            when {
                i.mod(4) == 0 -> {
                    val substr = line.replace("Button A: X+", "").split(", Y+")
                    x1 = substr[0].toInt()
                    y1 = substr[1].toInt()
                }
                i.mod(4) == 1 -> {
                    val substr = line.replace("Button B: X+", "").split(", Y+")
                    x2 = substr[0].toInt()
                    y2 = substr[1].toInt()
                }
                i.mod(4) == 2 -> {
                    val substr = line.replace("Prize: X=", "").split(", Y=")
                    c1 = substr[0].toInt()
                    c2 = substr[1].toInt()
                }
                i.mod(4) == 3 -> {
                    val nenner = x1 * y2 - y1 * x2
                    if (nenner != 0) {
                        val x = (y2 * c1 - x2 * c2).toDouble().div(nenner)
                        val y = (-y1 * c1 + x1 * c2).toDouble().div(nenner)
                        println("x: $x, y: $y")
                        if (x - x.roundToInt() == 0.0 && y - y.roundToInt() == 0.0) {
                            result += x.toInt() * 3 + y.toInt()
                            println("result: $result")
                        }
                    } else {
                        println("0!")
                    }
                    x1 = 0
                    x2 = 0
                    y1 = 0
                    y2 = 0
                    c1 = 0
                    c2 = 0
                }
            }
        }

        val nenner = x1 * y2 - y1 * x2
        if (nenner != 0) {
            val x = (y2 * c1 - x2 * c2).toDouble().div(nenner)
            val y = (-y1 * c1 + x1 * c2).toDouble().div(nenner)
            println("x: $x, y: $y")
            if (x - x.roundToInt() == 0.0 && y - y.roundToInt() == 0.0) {
                result += x.toInt() * 3 + y.toInt()
                println("result: $result")
            }
        } else {
            println("ERROR")
        }

        return result
    }

    fun part2(input: List<String>): Double {
        var result = 0.0
        var x1 = BigInteger.ZERO
        var x2 = BigInteger.ZERO
        var y1 = BigInteger.ZERO
        var y2 = BigInteger.ZERO
        var c1 = BigInteger.ZERO
        var c2 = BigInteger.ZERO

        for (i in input.indices) {
            val line = input[i]
            when {
                i.mod(4) == 0 -> {
                    val substr = line.replace("Button A: X+", "").split(", Y+")
                    x1 = substr[0].toBigInteger()
                    y1 = substr[1].toBigInteger()
                }
                i.mod(4) == 1 -> {
                    val substr = line.replace("Button B: X+", "").split(", Y+")
                    x2 = substr[0].toBigInteger()
                    y2 = substr[1].toBigInteger()
                }
                i.mod(4) == 2 -> {
                    val substr = line.replace("Prize: X=", "").split(", Y=")
                    c1 = substr[0].toBigInteger().plus(10000000000000.toBigInteger())
                    c2 = substr[1].toBigInteger().plus(10000000000000.toBigInteger())
                }
                i.mod(4) == 3 -> {
                    val nenner = x1 * y2 - y1 * x2
                    if (nenner != BigInteger.ZERO) {
                        val x = (y2 * c1 - x2 * c2).toDouble().div(nenner.toInt())
                        val y = (-y1 * c1 + x1 * c2).toDouble().div(nenner.toInt())
                        println("x: $x, y: $y")
                        if (x.mod(1.0) == 0.0 && y.mod(1.0) == 0.0) {
                            result += x.times(3).plus(y)
                            println("result: $result")
                        }
                    } else {
                        println("0!")
                    }
                    x1 = BigInteger.ZERO
                    x2 = BigInteger.ZERO
                    y1 = BigInteger.ZERO
                    y2 = BigInteger.ZERO
                    c1 = BigInteger.ZERO
                    c2 = BigInteger.ZERO
                }
            }
        }

        val nenner = x1 * y2 - y1 * x2
        if (nenner != BigInteger.ZERO) {
            val x = (y2 * c1 - x2 * c2).toDouble().div(nenner.toInt())
            val y = (-y1 * c1 + x1 * c2).toDouble().div(nenner.toInt())
            println("x: $x, y: $y")
            if (x - x.roundToInt() == 0.0 && y - y.roundToInt() == 0.0) {
                result += x.toInt() * 3 + y.toInt()
                println("result: $result")
            }
        } else {
            println("ERROR!")
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day13_test")
//    part1(testInput).println()
//    part2(testInput).toBigDecimal().println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day13")
//    part1(input).println()
    part2(input).toBigDecimal().println()
}