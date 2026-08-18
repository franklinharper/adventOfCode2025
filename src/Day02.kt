fun main() {

    val day = "Day02"

    fun isRepeat(id: Long): Boolean {
        val s = id.toString()
        val length = s.length
        if(length % 2 != 0) return false
        val firstHalf = s.substring(startIndex = 0, endIndex = length / 2)
        val secondHalf = s.substring(length / 2)
        return firstHalf == secondHalf
    }

    fun parseRanges(input: String): List<LongRange> = input.split(',').map { range ->
        val (start, end) = range.split('-').map(String::toLong)
        start..end
    }

    fun part1(input: String): Long {
        var res = 0L
        for (range in parseRanges(input)) {
            for (id in range) {
                if (isRepeat(id)) {
                    res += id
                }
            }
        }
        return res
    }

    fun isInvalid(id: Long): Boolean {
        val s = id.toString()
        val halfLength = s.length / 2
        for (repeatLength in 1..halfLength) {
            val repeats = s.substring(0, repeatLength).repeat(s.length/repeatLength)
            if( s == repeats) return true
        }
        return false
    }

    fun part2(input: String): Long {
        var res = 0L
        for (range in parseRanges(input)) {
            for (id in range) {
                if (isInvalid(id)) {
                    res += id
                }
            }
        }
        return res
    }

    val testInput = readInput("${day}_test")[0]

    println("Test part1")
    checkEquals(
    actual = part1(testInput),
        expected = 1227775554
    )

    val input = readInput("${day}_input")[0]
    println("Result part1")
    part1(input).println()

    println("Test part2")
    checkEquals(
        actual = part2(testInput),
        expected = 4174379265
    )
    println("Result part2")
    part2(input).println()
}
