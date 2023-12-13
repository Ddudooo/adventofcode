package ddudooo.adventofcode2023.day3.part2

import kotlin.math.max
import kotlin.math.min

class Day3Part2Solution {

    private val symbolRegex = Regex("([*])")
    private val digitRegex = Regex("([0-9]+)")

    fun solution(lines: List<String>): Int {
        var sum = 0
        lines.forEachIndexed { rowNum, line ->
            sum += symbolRegex.findAll(line)
                .map(MatchResult::range)
                .map(IntRange::first)
                .map { symbolIdx ->
                    nearLines(lines, rowNum)
                        .flatMap { nearLine -> nearlyDigits(nearLine, symbolIdx) }
                }
                .filter { it.size == 2 }
                .sumOf { it.reduce { acc, i -> i * acc } }
        }

        return sum
    }

    private fun nearLines(
        lines: List<String>,
        rowNum: Int
    ): Set<String> {
        return setOf(
            lines[max(rowNum - 1, 0)],
            lines[rowNum],
            lines[min(rowNum + 1, lines.size - 1)],
        )
    }

    private fun nearlyDigits(nearLine: String, symbol: Int): Sequence<Int> {
        return digitRegex.findAll(nearLine)
            .map { (it.range.first - 1)..(it.range.last + 1) to it.groupValues[0].toInt() }
            .filter { (range, value) -> symbol in range }
            .map { (range, value) -> value }
    }
}
