import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import java.io.File
import kotlin.math.abs

fun main(){
    daySevenTaskOne(File("day7.txt").readLines())
    daySevenTaskTwo(File("day7.txt").readLines())
}

fun daySevenTaskTwo(grid: List<String>) {
    val rowCount = grid.size
    val colCount = grid[0].length

    val start = grid.indices
        .firstNotNullOf { r ->
            grid[r].indexOf('S').takeIf { it != -1 }?.let { r to it }
        }

    val memo = HashMap<Pair<Int, Int>, Long>()

    fun countTimelinesFrom(position: Pair<Int, Int>): Long =
        memo.getOrPut(position) {
            var (row, col) = position

            while (++row < rowCount) {
                if (grid[row][col] == '^') {
                    var total = 0.toLong()
                    if (col > 0) total += countTimelinesFrom(row to col - 1)
                    if (col + 1 < colCount) total += countTimelinesFrom(row to col + 1)
                    return@getOrPut total
                }
            }

            1.toLong()
        }

    println( countTimelinesFrom(start))
}

fun daySevenTaskOne(input:List<String>){

    var beamSplittCount = 0

    val grid = mutableListOf<MutableList<coordinate>>() //resue coordinate from day 4
    for (y in input.indices){
        val row = mutableListOf<coordinate>()
        for (x in input[y].indices){
            row.add(coordinate(x, y, input[y][x]))
        }
        grid.add(row)
    }

    for (row in grid){
        for (coord in row){
            if (coord.symbol == '@' && checkAround(coord, grid) < 4 ){
                grid[coord.y][coord.x] = coordinate(coord.x, coord.y, 'X')
            }
        }
    }


    for (row in grid){
        for (coord in row){
            if (coord.symbol == 'S'){
                grid[coord.y+1][coord.x] = coordinate(coord.x, coord.y+1, '|')
            }
            if (hasBeamRightAbove(coord, grid)){
                if ( coord.symbol == '^'){
                    beamSplittCount++
                    grid[coord.y][coord.x-1] = coordinate(coord.x-1, coord.y, '|')
                    grid[coord.y][coord.x+1] = coordinate(coord.x+1, coord.y, '|')
                }   else {
                    grid[coord.y][coord.x] = coordinate(coord.x, coord.y, '|')
                }
            }
        }
        println("----------------------------------------")
        println(beamSplittCount)
        printGrid(grid)
    }

    println("Day 7 part 1 grand total is: $beamSplittCount")
}

fun printGrid(grid: MutableList<MutableList<coordinate>>){
    for (row in grid){
        for (coord in row){
            print(coord.symbol+ " ")
        }
        println()
    }
}

fun hasBeamRightAbove(coord: coordinate, grid: MutableList<MutableList<coordinate>>): Boolean {
    if (coord.y == 0) return false
    return grid[coord.y-1][coord.x].symbol == '|'
}