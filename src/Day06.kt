fun main() {

    val day = "Day06"
    val splitRegex = Regex(pattern = """\s+""")

    fun part1(input: List<String>): Long {
        val operators = input.last().split(regex = splitRegex)
        val parsedData = input.dropLast(1).map { line ->
            line.trim().split(splitRegex).map { it.toLong() }
        }
        val problemIndices = operators.indices
        // For each problem create a list containing the data for that problem.
        // I.e. Transpose the input data. All the data from col 0 is now in row 0, etc.
        val problemData = problemIndices.map { problemIndex ->
            parsedData.map { row -> row[problemIndex] }
        }
        val problemTotals = operators.zip(problemData).map { (operator, data) ->
           when (operator) {
               "+" ->  data.sum()
               "*" ->  data.fold(1L) { acc, value -> acc * value }
               else -> error("Unexpected operator: $operator")
           }
        }
        return problemTotals.sum()
    }

    fun part2(input: List<String>): Long {
        val operators = input.last().split(regex = splitRegex)
        val dataRows = input.dropLast(1)

        val problemData = mutableListOf<MutableList<Long>>()
        var currentData = mutableListOf<Long>()

        val columnIndices = dataRows.first().indices
        for(columnIndex in columnIndices) {
            val digits = dataRows
                .map { row -> row[columnIndex] }
                .filter { it != ' ' }
                .joinToString("")

            if (digits.isEmpty()) {
               problemData.add(currentData)
               currentData = mutableListOf()
            } else {
                currentData.add(digits.toLong())
            }
        }
        problemData.add(currentData)

        val problemTotals = operators.zip(problemData).map { (operator, data) ->
            when (operator) {
                "+" ->  data.sum()
                "*" ->  data.fold(1L) { acc, value -> acc * value }
                else -> error("Unexpected operator: $operator")
            }
        }
        return problemTotals.sum()
    }

    val testInput = readInput("${day}_test")

    checkEquals(
        message = "Test part1",
        actual = part1(testInput),
        expected = 4277556
    )

    val input = readInput("${day}_input")
    val part1Result = part1(input)
    println("Result part1: $part1Result")

    checkEquals(
        message = "Result part1",
        actual = part1Result,
        expected = 6891729672676
    )

    val part2Result = part2(input)
    println("Result part2: $part2Result")

    checkEquals(
        message = "Part2 result",
        actual = part2Result,
        expected = 9770311947567L
    )
}
