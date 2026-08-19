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
        val operators = input.last().trim().split(splitPattern)
        var currentProblem = 0

        val problemTotals = operators.map { operator ->
            if (operator == "+") 0L else 1L
        }.toMutableList()

        val columnCount = input.first().length

        for (ci in 0 until columnCount) {
           var entireColumnBlank = true
           var num = 0L
           for (ri in 0 .. input.lastIndex - 1) {
               val char = input[ri][ci]
               if (char != ' ') {
                   entireColumnBlank = false
                   num = num * 10 + (char - '0')
               }
           }
            if (entireColumnBlank) {
                currentProblem++
            } else {
                when (operators[currentProblem]) {
                    "+" -> problemTotals[currentProblem] += num
                    "*" -> problemTotals[currentProblem] *= num
                }
            }
        }
        return problemTotals.sum()
    }

    val testInput = readInput("${day}_test")

    checkEquals(
        message  = "Test part1",
        actual = part1(testInput),
        expected = 4277556
    )

    val input = readInput("${day}_input")
    println("Result part1: ${part1(input)}")

    checkEquals(
        message  = "Test part2",
        actual = part2(testInput),
        expected = 3263827
    )

    println("Result part2: ${part2(input)}")
}
