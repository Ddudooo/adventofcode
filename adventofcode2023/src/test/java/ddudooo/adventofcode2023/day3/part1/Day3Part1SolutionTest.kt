package ddudooo.adventofcode2023.day3.part1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Part1SolutionTest {
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
        val solution = Day3Part1Solution()

        assertEquals(4361, solution.solution(input))
    }
}
