fun main() {
    fun part1(input: List<String>): Int {
        var pos = 50
        var res = 0
        for (s in input) {
            val value = s.substring(1).toInt()
            val offset = if (s.startsWith("L")) -value else value
            pos = (pos + offset).mod(100)
            println(pos)
            if (pos == 0) res++
        }
        println("///")
        return res
    }

    fun countZeros(pos: Int, s: String): Pair<Int, Int> {
        var newPos = pos
        var zeroCount = 0
        val offset = if (s.startsWith("L")) -1 else 1
        val value = s.substring(1).toInt()
        repeat(times = value) {
            newPos += offset
            if (newPos == -1) newPos = 99
            if (newPos == 100) newPos = 0
            if (newPos == 0) zeroCount++
//            println(newPos)
        }
        println("///")
        return Pair(newPos, zeroCount)
    }

    fun part2(input: List<String>): Int {
        var pos = 50
        var zeroCount = 0
        for (s in input) {
            val (newPos, zeros) = countZeros(pos, s)
            pos = newPos
            println(pos)
            zeroCount += zeros
        }
        println("***")
        return zeroCount
    }

    val testInput = readInput("Day01_test")
    println("Test part1")
    checkEquals(
        actual = part1(testInput),
        expected = 3
    )

    println("Test part2")
    checkEquals(
        actual = part2(testInput),
        expected = 6
    )

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01_input")
    println("Result part1")
    part1(input).println()
    println("Result part2")
    part2(input).println()
}
