fun main() {

    val day = "Day03"

    fun findNextDigitIndex(digits: List<Int>, targetLength: Int): Int {
        // The digits are never zero.
        // => all numbers using N digits are > numbers using N - 1 digits.
        // => the next digit is the max of the digits that allow creating an N digit number.
//        val maxDigitValue = digits.subList(0, (digits.size - remainingDigits)).max()

        // size targetLength drop
        // 1    1            0

        // 2    1            0
        // 2    2            1

        // 3    1            0
        // 3    2            1
        // 3    3            2

        // 4    1            0
        // 4    2            1
        // 4    3            2
        // 4    4            3

        // 8   1             0
        // 8   2             1
        // 8   3             2
        // 8   4             3
        // 8   5             4
        // 8   6             5
        // 8   7             6
        // 8   8             7

        // S    L            S - (S - L) - 1

        val digitsToIgnore = digits.size - (digits.size - targetLength) - 1
        val nextDigitValue = digits.dropLast(digitsToIgnore).max()
        return digits.indexOf(nextDigitValue)
    }

    fun findMax(digits: List<Int>, targetLength: Int): Long {
        println("findMax digits: $digits, targetLength: $targetLength")
        var max = 0L
        var currentDigits = digits
        var currentTargetLength = targetLength
        while (currentTargetLength > 0) {
            val nextDigitIndex = findNextDigitIndex(currentDigits, currentTargetLength)
            max = max * 10 + currentDigits[nextDigitIndex]
            currentDigits = currentDigits.drop(nextDigitIndex + 1)
            currentTargetLength--
        }
        println("findMax max: $max")
        return max
    }

    fun part1(input: List<String>): Long {
        var res = 0L
        for (s in input) {
            val digits = s.map { it.digitToInt() }
            res += findMax(digits, targetLength = 2)
        }
        return res
    }

    fun part2(input: List<String>): Long {
        var res = 0L
        for (s in input) {
            val digits = s.map { it.digitToInt() }
            res += findMax(digits, 12)
        }
        return res
    }

    val testInput = readInput("${day}_test")

    println($$"Test part1")
    checkEquals(
        actual = part1(testInput),
        expected = 357
    )

    val input = readInput("${day}_input")
    part1(input).println()
    println("Result part1")

    println("Test part2")
    checkEquals(
        actual = part2(testInput),
        expected = 3121910778619
    )
    println("Result part2")
    part2(input).println()
}
