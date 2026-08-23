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
        val rowCount = input.size
        // Puzzle input has a top-row S, rectangular rows, and no edge splitters.
        val columnCount = input.first().length
        val timelinesFrom = Array(rowCount) { LongArray(columnCount) }

        // DFS with memoization.
        fun countTimelines(row: Int, column: Int): Long {
            if (row == rowCount) return 1L
            if (timelinesFrom[row][column] != 0L) return timelinesFrom[row][column]

            // '^' is the particle splitter
            val timelines = if (input[row][column] == '^') {
                countTimelines(row + 1, column - 1) + countTimelines(row + 1, column + 1)
            } else {
                countTimelines(row + 1, column)
            }
            timelinesFrom[row][column] = timelines
            return timelines
        }

        val startColumn = input.first().indexOf('S')
        return countTimelines(row = 1, column = startColumn)
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
