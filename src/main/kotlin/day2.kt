import java.io.File
import kotlin.math.abs

fun main(){
    dayTwoTaskOne(File("day2.txt").readLines())
    dayTwoTaskTwo(File("day2.txt").readLines())
}

fun dayTwoTaskTwo(input:List<String>){

    val idsRanges = input[0].split(",").map { it.split("-")[0].toLong() to it.split("-")[1].toLong() }
    var sumInvalidIds = 0.toLong()
    for (idRange in idsRanges){
        val start = idRange.first
        val end = idRange.second
        for (id in start..end){
            val idString = id.toString()
            var tempString = ""
            for (i in idString){
                tempString += i
                val count = idString.windowed(tempString.length){
                    if (it == tempString)
                        1
                    else
                        0
                }.sum()
                if (tempString.repeat(count) == idString && count > 1){
                    sumInvalidIds += id
                    break
                }
            }
        }
    }

    println("Day 2 part 2 sum of invalid IDs is: $sumInvalidIds")

}

fun dayTwoTaskOne(input:List<String>){

    val idsRanges = input[0].split(",").map { it.split("-")[0].toLong() to it.split("-")[1].toLong() }
    var sumInvalidIds = 0.toLong()
    for (idRange in idsRanges){
        val start = idRange.first
        val end = idRange.second
        for (id in start..end){
            val idString = id.toString()
            if (idString.length % 2 == 0) {
                val part1 = idString.take(idString.length / 2)
                val part2 = idString.takeLast(idString.length / 2)
                if (part1 == part2) {
                    sumInvalidIds += id
                }
            }
        }
    }

    println("Day 2 part 1 sum of invalid IDs is: $sumInvalidIds")
}