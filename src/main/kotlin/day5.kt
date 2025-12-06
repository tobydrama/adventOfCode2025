import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import java.io.File
import kotlin.math.abs

fun main(){
    //dayFiveTaskOne(File("day5.txt").readLines())
    dayFiveTaskTwo(File("day5.txt").readLines())
}

fun dayFiveTaskTwo(input:List<String>){

    val freshIngridiantIdRanges = input.takeWhile { it != "" }.map {
        val splitRange = it.split("-")
        (splitRange[0].toLong()..splitRange[1].toLong())
    }

    val sorted = freshIngridiantIdRanges.sortedBy { it.first }
    val result = mutableListOf<LongRange>()

    var current = sorted.first()

    for (range in sorted.drop(1)) {
        if (range.first <= current.last + 1) {
            current = current.first..maxOf(current.last, range.last)
        } else {
            result += current
            current = range
        }
    }

    result += current

    result.forEach { println(it) }


    println("Day 5 part 2 number of fresh ingredients is: ${result.sumOf { it.last - it.first +1}}")

}

fun dayFiveTaskOne(input:List<String>){

    var freshIngridinatCount = 0
    val freshIngridiantIdRanges = input.takeWhile { it != "" }.map {
        val splitRange = it.split("-")
        (splitRange[0].toLong()..splitRange[1].toLong())
    }
    val availableIngredientIDs = input.dropWhile { it != "" }.drop(1).map { it.toLong() }

    for (i in availableIngredientIDs) {
        for (range in freshIngridiantIdRanges){
            if (i in range){
                freshIngridinatCount++
                break
            }
        }
    }

    println("Day 5 part 1 number of fresh ingredients is: $freshIngridinatCount")
}