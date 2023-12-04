package ddudooo.adventofcode2023.day1.part2

class Day1Part2Solution {
    private val digits = listOf(
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
    )

    fun solution(lines: List<String>): Int {
        return lines.sumOf { line -> convertLine(line) }
    }

    private fun convertLine(line: String): Int {
        return line.indices.mapNotNull { idx ->
            val find = findDigit(line, idx)
            if (find < -1) {
                line[idx].digitToIntOrNull()
            } else {
                find
            }
        }.let { it.first() * 10 + it.last() }
    }

    private fun findDigit(line: String, idx: Int): Int {
        return digits.indexOfFirst { line.drop(idx).startsWith(it) }
    }
}
