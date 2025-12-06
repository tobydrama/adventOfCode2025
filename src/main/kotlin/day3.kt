import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import java.io.File
import kotlin.math.abs

fun main(){
    dayThreeTaskOne(File("day3.txt").readLines())
    dayThreeTaskTwo(File("day3.txt").readLines())
}

fun dayThreeTaskTwo(input:List<String>){

    val joltages = mutableListOf<Long>()
    input.forEach { bank ->
        var lastIndex = -1
        var num = ""
        for (i in 11 downTo 0){
            val pairNumIndex = getBiggestNumberInIndexRange(bank, i, start = lastIndex + 1)
            lastIndex = pairNumIndex.second
            num += pairNumIndex.first.toString()
        }
        joltages.add(num.toLong())
    }
    println("Day 3 part 2 sum of joltages is ${joltages.sum()} ")
}

fun getBiggestNumberInIndexRange(bank: String, indexLengt: Int, start: Int) :Pair<Int, Int> {
    var indexBiggestNum = start+1
    val subBank = bank.substring(start, bank.length - indexLengt)
    var num = 0

    subBank.forEachIndexed { index, s ->
        if (s.toString().toInt() > num){
            num = s.toString().toInt()
            indexBiggestNum = index + start
        }
    }

    return Pair(num, indexBiggestNum)
}

fun dayThreeTaskOne(input:List<String>){

    val joltage = mutableListOf<Int>()
    for (bank in input) {
        var num1 = 0
        var num2 = 0
        bank.forEachIndexed { index, s ->
            val tempNum = s.toString().toInt()
            if (tempNum > num1 && bank.length != index+1){
                num1 = tempNum
                num2 = bank[index+1].toString().toInt()
            }
            else if (tempNum > num2) { num2 = tempNum }
        }
        val jolt = "$num1$num2".toInt()
        println(jolt)
        joltage.add("$num1$num2".toInt())
    }

    println("Day 2 part 1 sum of joltage is ${joltage.sum()}")
}