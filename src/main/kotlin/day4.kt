import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import java.io.File
import kotlin.math.abs

fun main(){
    //dayFourTaskOne(File("day4.txt").readLines())
    dayFourTaskTwo(File("day4.txt").readLines())
}

fun dayFourTaskTwo(input:List<String>){

    val grid = mutableListOf<MutableList<coordinate>>()
    for (y in input.indices){
        val row = mutableListOf<coordinate>()
        for (x in input[y].indices){
            row.add(coordinate(x, y, input[y][x]))
        }
        grid.add(row)
    }
    val girdSize = grid.flatten()
    for (i in 0..girdSize.size) {
        val oldGrid = grid.map { it.map { coord -> coord.symbol }.toMutableList() }.toMutableList()
        for (row in grid) {
            for (coord in row) {
                if (coord.symbol == '@' && checkAroundPart2(coord, grid) < 4) {
                    grid[coord.y][coord.x] = coordinate(coord.x, coord.y, 'X')
                }
            }
        }
        if (oldGrid == grid) {
            break
        }

    }

    for (row in grid){
        for (coord in row){
            print(coord.symbol+ " ")
        }
        println()
    }

    println("The total number of rolls of paper that the forklift can access is  ${grid.flatten().filter { it.symbol == 'X' }.size}")

}

fun dayFourTaskOne(input:List<String>){

    val grid = mutableListOf<MutableList<coordinate>>()
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
            print(coord.symbol+ " ")
        }
        println()
    }

    println("The total number of rolls of paper that the forklift can access is  ${grid.flatten().filter { it.symbol == 'X' }.size}")
}

fun checkAroundPart2(coord: coordinate, grid: MutableList<MutableList<coordinate>>): Int {

    val x = coord.x
    val y = coord.y
    var paiperCount = 0

    for (yCoords in -1..1){
        for (xCorrds in -1..1){
            if (x+xCorrds < 0 || y+yCoords < 0 || x+xCorrds >= grid[0].size || y+yCoords >= grid.size || (xCorrds == 0 && yCoords ==0)){
                continue //outside of map or same coordinate
            }
            val checkXCoords = x+xCorrds
            val checkYCoords = y+yCoords

            if (grid[checkYCoords][checkXCoords].symbol == '@') {paiperCount++}
        }
    }
    return paiperCount

}

fun checkAround(coord: coordinate, grid: MutableList<MutableList<coordinate>>): Int {

    val x = coord.x
    val y = coord.y
    var paiperCount = 0

    for (yCoords in -1..1){
        for (xCorrds in -1..1){
            if (x+xCorrds < 0 || y+yCoords < 0 || x+xCorrds >= grid[0].size || y+yCoords >= grid.size || (xCorrds == 0 && yCoords ==0)){
                continue //outside of map or same coordinate
            }
            val checkXCoords = x+xCorrds
            val checkYCoords = y+yCoords

            if (grid[checkYCoords][checkXCoords].symbol == '@' || grid[checkYCoords][checkXCoords].symbol == 'X'){paiperCount++}
        }
    }
    return paiperCount

}

class coordinate(val x: Int, val y: Int, symbol: Char){
    val symbol: Char = symbol
    override fun toString(): String {
        return "($x, $y)"
    }
}