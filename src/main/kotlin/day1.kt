import java.io.File
import kotlin.math.abs

fun main(){
    dayOneTaskOne(File("day1.txt").readLines())
    dayOneTaskTwo(File("day1.txt").readLines())
}

fun dayOneTaskTwo(input:List<String>){
    var dialValue = 50
    var numberOfRotations = 0

    for (i in input){

        val leftOrRight = i[0]
        var numberOfZero= i.drop(1).toInt() / 100
        val number = i.drop(1).toInt() % 100

        if (leftOrRight == 'L'){
            val tempDialValue = dialValue - number
            if ( tempDialValue <= 0 && dialValue!=0) {numberOfZero++}
            dialValue = (100 + tempDialValue) % 100

        } else {
            if ((dialValue + number)>= 100) {numberOfZero++}
            dialValue = (dialValue + number) % 100
        }
        numberOfRotations += numberOfZero
    }
    println()
    println("Day 2 part 2 The total number of rotations is: $numberOfRotations")

}

fun dayOneTaskOne(input:List<String>){
    var dialValue = 50
    var numberOfRotations = 0

    for (i in input){
        val leftOrRight = i[0]
        val number = i.drop(1).toInt() % 100

        if (leftOrRight == 'L'){
            val tempDialValue = dialValue - number
            dialValue = (100 + tempDialValue) % 100

        } else {
            dialValue = (dialValue + number) % 100
        }

        if (dialValue == 0){
            numberOfRotations += 1
        }
    }
    println("Day 1 part 1 The total number of rotations is: $numberOfRotations")
}