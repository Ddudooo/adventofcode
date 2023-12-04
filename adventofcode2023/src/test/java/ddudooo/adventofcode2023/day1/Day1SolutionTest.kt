package ddudooo.adventofcode2023.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1SolutionTest {

    @Test
    fun solutionSample() {
        val lines = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
        )
        val solution = Day1Solution()
        assertEquals(142, solution.solution(lines))
    }
}
