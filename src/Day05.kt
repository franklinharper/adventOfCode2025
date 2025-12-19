fun main() {

    val day = "Day04"

    val offsets = listOf(-1, 0, 1)

    fun countNeighborRolls(input: List<String>, i: Int, j: Int): Int {
        var res = 0
        for (iOffset in offsets) {
            for (jOffset in offsets) {
                if (iOffset == 0 && jOffset == 0) continue
                val c = input.getOrNull(i + iOffset)?.getOrNull(j + jOffset)
                if (c == '@') {
                    res++
                }
            }
        }
        return res
    }

    fun part1(input: List<String>): Long {
        var res = 0L
        for (i in input.indices) {
            for (j in input[i].indices) {
                val neighborRollCount = countNeighborRolls(input, i, j)
                if (neighborRollCount < 4 && input[i][j] == '@') {
                    res++
                }
            }
        }
        return res
    }

    fun part2(data: List<String>): Long {
        var input = data
        var res = 0L
        do {
            var done = true
            val nextGen = mutableListOf<String>()
            for (i in input.indices) {
                val nextString = StringBuilder()
                for (j in input[i].indices) {
                    val neighborRollCount = countNeighborRolls(input, i, j)
                    if (neighborRollCount < 4 && input[i][j] == '@') {
                        res++
                        nextString.append('.')
                        done = false
                    } else {
                        nextString.append(input[i][j])
                    }
                }
                nextGen.add(nextString.toString())
            }
            input = nextGen
        } while (!done)
        return res
    }

    val testInput = readInput("${day}_test")

    println($$"Test part1")
    checkEquals(
        actual = part1(testInput),
        expected = 13
    )

    val input = readInput("${day}_input")
    part1(input).println()
    println("Result part1")

    println("Test part2")
    checkEquals(
        actual = part2(testInput),
        expected = 43
    )
    println("Result part2")
    part2(input).println()
}
