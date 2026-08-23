fun main() {

    val day = "Day07"

    fun part1(input: List<String>): Int {
        data class SimulationState(val activeColumns: Set<Int>, val splitCount: Int)

        val initialColumn = input.first().indexOf('S')
        return input.drop(1)
            .fold(SimulationState(activeColumns = setOf(initialColumn), splitCount = 0)) { simulation, row ->
                val splitColumns = simulation.activeColumns.filter { column -> row[column] == '^' }
                val nextColumns = simulation.activeColumns
                    .flatMap { column ->
                        if (column in splitColumns) listOf(column - 1, column + 1)
                        else listOf(column)
                    }
                    .toSet()

                SimulationState(
                    activeColumns = nextColumns,
                    splitCount = simulation.splitCount + splitColumns.size
                )
            }
            .splitCount
    }

    // Calculate the number of timelines active after a single particle completes all of
    // its possible journeys through the manifold.
    fun part2(input: List<String>): Long {

        val memo = Array(input.size + 1) { LongArray(input.first().length) { 0 }}

        fun dfs(row: Int, col: Int): Long {
            val timeLines = when {
                row == input.size -> 1L // Particle has reached the bottom
                memo[row][col] > 0L -> memo[row][col]
                input[row][col] == '^' -> dfs(row + 1, col - 1) + dfs(row + 1, col + 1)
                else -> dfs(row + 1, col)
            }
            memo[row][col] = timeLines
            return timeLines
        }

        val initialParticleCol = input.first().indexOf('S')
        return dfs(row = 1, col = initialParticleCol)
    }

    val testInput = readInput("${day}_test")

    checkEquals(
        message = "Test part1",
        actual = part1(testInput),
        expected = 21
    )

    val input = readInput("${day}_input")
    println("Result part1: ${part1(input)}")

    // Part 2
    val resultTestPart2 = part2(testInput)
    println("Result test part2: $resultTestPart2")

    checkEquals(
        message = "Test part2",
        actual = resultTestPart2,
        expected = 40
    )

    println("Start part2")
    val part2Result = part2(input)
    println("Result part2: $part2Result")

    checkEquals(
        message = "Part2 result",
        actual = part2Result,
        expected = 34339203133559L
    )
}
