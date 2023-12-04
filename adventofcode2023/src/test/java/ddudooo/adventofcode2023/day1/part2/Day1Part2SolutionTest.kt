package ddudooo.adventofcode2023.day1.part2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Part2SolutionTest {
    @Test
    fun solutionSample() {
        val lines = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
        )
        val solution = Day2Solution()
        assertEquals(281, solution.solution(lines))
    }
}
