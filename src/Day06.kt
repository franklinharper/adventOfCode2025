fun main() {

    val day = "Day06"
    val splitPattern = "\\s+".toRegex()

    fun part1(input: List<String>): Long {
        val operators = input.last().trim().split(splitPattern)
        val columnTotals = operators.map { operator ->
            if (operator == "+") 0L else 1L
        }.toMutableList()

        for (s in input.dropLast(1)) {
            val nums = s.trim().split(splitPattern).map(String::toLong)
            for (column in nums.indices) {
                when (operators[column]) {
                    "+" -> columnTotals[column] += nums[column]
                    "*" -> columnTotals[column] *= nums[column]
                }
            }
        }
        return columnTotals.sum()
    }

    fun part2(input: List<String>): Long {
        val dataRows = input.dropLast(1)
        val operators = input.last().trim().split(splitPattern)

        val problemData = mutableListOf<MutableList<Long>>()
        var currentData = mutableListOf<Long>()

        for (ci in dataRows.first().indices) {
            val digits = dataRows
                .map { it[ci] }
                .filter { it != ' ' }
                .joinToString(separator = "")
            if (digits.isEmpty()) {
                problemData.add(currentData)
                currentData = mutableListOf()
            } else {
                currentData.add(digits.toLong())
            }
        }
        problemData.add(currentData)
        return operators.zip(problemData).sumOf { (operator, data) ->
            when (operator) {
                "+" -> data.sum()
                "*" -> data.fold(initial = 1L) { acc, value -> acc * value }
                else -> throw Error("Unknown operator $operator")
            }
        }
    }

    val testInput = readInput("${day}_test")

    checkEquals(
        message = "Test part1",
        actual = part1(testInput),
        expected = 4277556
    )

    val input = readInput("${day}_input")
    println("Result part1: ${part1(input)}")

    checkEquals(
        message = "Test part2",
        actual = part2(testInput),
        expected = 3263827L
    )

    val part2Result = part2(input)
    println("Result part2: $part2Result")

    checkEquals(
        message = "Part2 result",
        actual = part2Result,
        expected = 9770311947567L
    )

}
