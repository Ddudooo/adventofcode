package ddudooo.adventofcode2023.day2.part2

class Day2Part2Solution {
    fun solution(lines: List<String>): Int {
        return game(lines)
    }

    private fun game(lines: List<String>): Int {
        return lines.map { line -> parseLine(line).maxColorsOfGame() }.sumOf { it.power() }
    }

    private fun parseLine(line: String): Games {
        val games = line.substringAfter(": ").split("; ").map { game -> parseGame(game) }.toList()
        val gameNumber = line.substringBefore(": ").substringAfter(" ").toInt()
        return Games(gameNumber, games)
    }

    private fun parseGame(line: String): Game {
        return line.split(", ").map { colorInfo -> parseColorInfo(colorInfo)}.reduce { acc, game -> acc.plus(game) }
    }

    private fun parseColorInfo(colorInfo: String): Game {
        val count = colorInfo.substringBefore(" ").toInt()
        val color = colorInfo.substringAfter(" ")
        return when (color) {
            "red" -> Game(red = count)
            "green" -> Game(green = count)
            "blue" -> Game(blue = count)
            else -> Game()
        }
    }
}

data class Games(val gameNumber: Int, val games: List<Game>) {
    fun maxColorsOfGame(): Game {
        return Game(
            games.maxOf { it.red },
            games.maxOf { it.green },
            games.maxOf { it.blue }
        )
    }
}

data class Game(val red: Int = 0, val green: Int = 0, val blue: Int= 0) {
    fun plus(other: Game): Game {
        return Game(red + other.red, green + other.green, blue + other.blue)
    }

    fun power(): Int {
        return red * green * blue
    }
}
