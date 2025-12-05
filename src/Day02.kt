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

    fun part1(input: String): Long {
        var res = 0L
        val ranges = input.split(',')
        for (s in ranges) {
            val range = s.split('-')
            val start = range[0].toLong()
            val end = range[1].toLong()
            for (id in start..end) {
                if (isRepeat(id)) {
                    println("repeat id $id")
                    res += id
                }
            }
        }
        println("///")
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
        val ranges = input.split(',')
        for (s in ranges) {
            val range = s.split('-')
            println("range: $range")
            val start = range[0].toLong()
            val end = range[1].toLong()
            for (id in start..end) {
                if (isInvalid(id)) {
                    println("invalid id $id")
                    res += id
                }
            }
        }
        println("///")
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
