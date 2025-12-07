import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import java.io.File
import kotlin.math.abs

fun main(){
    daySixTaskOne(File("day6.txt").readLines())
    daySixTaskTwo(File("day6.txt").readLines())
}

fun daySixTaskTwo(input:List<String>){

    val operations = input.last().split(" ").filter { it.isNotBlank() }
    val grid = input.takeWhile { !it.contains('*') }

    var ewrwr = "|"
    for (index in grid[0].length -1 downTo  0){
        val adasda = grid.map { it[index] }.joinToString("")
        ewrwr += adasda + "|"
    }
    val numbers2 = ewrwr.split(" ".repeat(grid.size)).map { it.split('|').drop(1).dropLast(1) }.reversed()
    val numbers3 = numbers2.map { it.map { it.replace(" ", "").toLong() } }
    val equationResults = mutableListOf<Long>()


    operations.forEachIndexed { index, operation ->
        if (operation == "*") {
            val result = numbers3[index].reduce { acc, i -> acc * i }
            equationResults.add(result)
        } else {
            val result = numbers3[index].reduce { acc, i -> acc + i }
            equationResults.add(result)
        }
    }
    println("Day 6 part 2 grand total is: ${equationResults.sum()}")

}

fun daySixTaskOne(input:List<String>){
    val operations = input.last().split(" ").filter { it.isNotBlank() }
    val numbers = input.takeWhile { !it.contains('*') }.map { it.split(" ") }.map { it.filter { c -> c.isNotEmpty() } }
    val equationResults = mutableListOf<Long>()

    operations.forEachIndexed { index, operation ->
        if (operation == "*") {
            val equationNumbers = numbers.map { it[index].toLong() }
            val result = equationNumbers.reduce { acc, i -> acc * i }
            equationResults.add(result)
        } else {
            val equationNumbers = numbers.map { it[index].toLong() }
            val result = equationNumbers.reduce { acc, i -> acc + i }
            equationResults.add(result)
        }
    }
    println("Day 6 part 1 grand total is: ${equationResults.sum()}")
}