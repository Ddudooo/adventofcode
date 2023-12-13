package ddudooo.adventofcode2023.day3.part1

import kotlin.math.max
import kotlin.math.min

class Day3Part1Solution {

    fun solution(lines: List<String>): Int {
        return parseMap(lines).sumAdjacentSymbolNumbers()
    }

    private fun parseMap(lines: List<String>): GameMap {
        return GameMap(lines.indices.map { rowNum -> parseRow(rowNum, lines[rowNum]) })
    }

    private fun parseRow(rowNum: Int, line: String): GameMapRow {
        return GameMapRow(rowNum, parseLineToPoints(rowNum, line))
    }

    private fun parseLineToPoints(rowNum: Int, line: String): List<GameMapPoint> {
        val chars = line.toCharArray()
        return chars.indices.map { idx -> GameMapPoint(idx, rowNum, chars[idx]) }
    }
}

data class GameMap(val rows: List<GameMapRow>) {
    init {
        rows.indices.forEach {
            val row = rows[it]
            if (row.hasSymbol()) {
                row.getSymbolEntries().forEach { (idx, rowNum, _) ->
                    val nearlyPoints = nearlyPoints(rowNum, idx, row)

                    val adjacentDigits = nearlyPoints.filter { point -> point.isDigit() }
                        .map { point -> point.setAdjacentSymbol() }

                    adjacentDigits.forEach { digitPoint ->
                        rows[digitPoint.rowNum].getNearlyDigitEntries(digitPoint.idx).forEach { nearlyDigitPoint ->
                            nearlyDigitPoint.setAdjacentSymbol()
                        }
                    }
                }
            }
        }
    }

    private fun nearlyPoints(
        rowNum: Int,
        idx: Int,
        row: GameMapRow
    ): List<GameMapPoint> {
        return listOf(
            rows[min(max(rowNum - 1, 0), rows.size)].entries[min(max(idx, 0), row.entries.size)],
            rows[min(max(rowNum + 1, 0), rows.size)].entries[min(max(idx, 0), row.entries.size)],
            rows[min(max(rowNum - 1, 0), rows.size)].entries[min(max(idx - 1, 0), row.entries.size)],
            rows[min(max(rowNum + 1, 0), rows.size)].entries[min(max(idx + 1, 0), row.entries.size)],
            rows[min(max(rowNum - 1, 0), rows.size)].entries[min(max(idx + 1, 0), row.entries.size)],
            rows[min(max(rowNum + 1, 0), rows.size)].entries[min(max(idx - 1, 0), row.entries.size)],
            rows[min(max(rowNum, 0), rows.size)].entries[min(max(idx - 1, 0), row.entries.size)],
            rows[min(max(rowNum, 0), rows.size)].entries[min(max(idx + 1, 0), row.entries.size)],
        )
    }

    fun sumAdjacentSymbolNumbers(): Int {
        return rows.sumOf { row -> row.getAdjacentSymbolNumbers().sum() }
    }
}

data class GameMapRow(val rowNum: Int, val entries: List<GameMapPoint>) {

    fun hasSymbol(): Boolean {
        return entries.any { it.isSymbol() }
    }

    fun getSymbolEntries(): List<GameMapPoint> {
        return entries.filter { it.isSymbol() }
    }

    fun getNearlyDigitEntries(idx: Int): List<GameMapPoint> {
        if (!entries[idx].isDigit()) {
            return listOf()
        }

        val nearlyDigitEntries = mutableListOf<GameMapPoint>()
        forwardDigitEntries(idx).let { nearlyDigitEntries.addAll(it) }
        backwardDigitsEntries(idx).let { nearlyDigitEntries.addAll(it) }

        return nearlyDigitEntries
    }


    private fun forwardDigitEntries(
        idx: Int
    ): MutableList<GameMapPoint> {
        val digitPoints = mutableListOf<GameMapPoint>()

        val forward = entries.subList(0, idx)

        for (index in forward.indices.reversed()) {
            if (!forward[index].isDigit()) {
                break
            }
            forward[index].takeIf { it.isDigit() }?.takeIf { it.idx != idx }?.let { digitPoints.add(it) }
        }

        return digitPoints
    }

    private fun backwardDigitsEntries(
        idx: Int
    ): MutableList<GameMapPoint> {
        val digitPoints = mutableListOf<GameMapPoint>()

        val back = entries.subList(idx, entries.size)
        for (point in back) {
            if (!point.isDigit()) {
                break
            }
            point.takeIf { it.isDigit() }?.takeIf { it.idx != idx }?.let { digitPoints.add(it) }
        }

        return digitPoints
    }

    fun getAdjacentSymbolNumbers(): List<Int> {
        return entries.map { it.getAdjacentSymbolNumberOrEmpty() }
            .joinToString("")
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.toInt() }
    }
}

data class GameMapPoint(val idx: Int, val rowNum: Int, val value: Char) {
    private var adjacentSymbol: Boolean = false

    fun isSymbol(): Boolean {
        return !value.isDigit() && value != '.'
    }

    fun isDigit(): Boolean {
        return value.isDigit()
    }

    private fun isAdjacentSymbol(): Boolean {
        return adjacentSymbol
    }

    fun setAdjacentSymbol(): GameMapPoint {
        adjacentSymbol = true
        return this;
    }

    fun getAdjacentSymbolNumberOrEmpty(): Char {
        return this.takeIf { it.isAdjacentSymbol() }?.takeIf { it.isDigit() }?.value ?: ' '
    }
}
