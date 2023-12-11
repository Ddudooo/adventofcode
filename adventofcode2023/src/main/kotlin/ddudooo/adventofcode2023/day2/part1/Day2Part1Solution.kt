package ddudooo.adventofcode2023.day2.part1

class Day2Part1Solution(maxRed: Int = 12, maxGreen: Int = 13, maxBlue: Int = 14) {
    private val limitGame: Game = Game(maxRed, maxGreen, maxBlue)

    fun solution(lines: List<String>): Int {
        return lines.sumOf { line -> game(line) }
    }

    private fun game(line: String): Int {
        return parseLine(line).takeIf { it.isNotOver(limitGame) }?.gameNumber ?: 0
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
    fun isNotOver(limitGame: Game): Boolean {
        return games.all { it.isNotOver(limitGame) }
    }
}

data class Game(val red: Int = 0, val green: Int = 0, val blue: Int= 0) {
    fun plus(other: Game): Game {
        return Game(red + other.red, green + other.green, blue + other.blue)
    }

    fun isNotOver(game: Game): Boolean {
        return red <= game.red && green <= game.green && blue <= game.blue
    }
}
