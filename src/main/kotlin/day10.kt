import java.io.File

//get list of jolts
//order the list sequentially
//map ordered list, compare current to next and use difference as the value
//sum all 1s
//sum all 3s
//multiply together
fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val inputDayTen = readFileAsLinesUsingUseLines("src/main/resources/inputday10.txt").map { it.toInt() }.sorted()
var x10 = 0
fun main(args: Array<String>) {
    val diff = mutableListOf<Int>()

    println(inputDayTen)
    for(i in inputDayTen) {
        x10 += 1
        if(x10 > inputDayTen.size - 1) {
            break
        }
        diff.add(inputDayTen[x8] - i)
    }
    val diffCount = diff.groupingBy { it }.eachCount()
    var ones = 0
    var threes = 0
    for(i in diffCount) {
        if(i.key == 1) {
            ones = i.value + 1
        } else if(i.key == 3)  {
            threes = i.value + 1
        }
    }
   val ans = (3.toFloat() * inputDayTen.size * ones.toFloat())  + threes.toFloat()
    println(diffCount)
    println(diff)
    println("The answer is ${ones*threes}")
    println("the number of unique combinations is $ans")
}
