import java.io.File

var churn = 5
fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val inputDayNine = readFileAsLinesUsingUseLines("src/main/resources/inputday9.txt").map { it.toLong() }
    for(i in inputDayNine) {
        val tmp = inputDayNine.subList(0, churn)
        val sums = mutableListOf<Long>()
        var iter = 0
        for(x in tmp) {
            if(x != tmp[iter]){
                sums.add(x + tmp[iter]) //you aren't coming up with every combination here.
            }
        }
        if(!sums.contains(inputDayNine[churn])) {
            println("the first number to break the pattern is ${inputDayNine[churn]}")
            break
        }
        churn += churn
    }
}