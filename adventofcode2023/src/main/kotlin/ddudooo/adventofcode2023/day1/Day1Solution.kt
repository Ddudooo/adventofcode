package ddudooo.adventofcode2023.day1

class Day1Solution {
    fun solution(lines: List<String>): Int {
        return lines.sumOf { line ->
            line.filter { it.isDigit() }.let { "${it.first()}${it.last()}" }.toInt()
        }
    }
}
