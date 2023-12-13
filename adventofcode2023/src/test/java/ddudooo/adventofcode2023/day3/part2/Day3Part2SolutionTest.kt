package ddudooo.adventofcode2023.day3.part2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Part2SolutionTest {
    @Test
    fun solutionTest() {
        val input = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )
        val solution = Day3Part2Solution()

        assertEquals(467835, solution.solution(input))
    }
}
