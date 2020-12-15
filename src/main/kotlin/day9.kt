import java.io.File

var churn = 25
fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val inputDayNine = readFileAsLinesUsingUseLines("src/main/resources/inputday9.txt").map { it.toLong() }
    fun getPreamble(iter: Int): List<Long> {
        return inputDayNine.subList(iter, churn+iter)
    }
    val values = inputDayNine.subList(churn, inputDayNine.size)

    fun validValues(z: Int): List<Long> {
        val preamble = getPreamble(z)
        val sums = mutableListOf<Long>()
        for (i in preamble) {
            for (x in preamble) {
                if (i != x) {
                    sums.add(i + x)
                }
            }
        }
        return sums.toList()
    }

    //part 1
    var iterator = 0
    var ans = 0.toLong()
    for(i in values) {
        if(validValues(iterator).contains(i)) {
            iterator += 1
        } else {
            ans = i
            break
        }
    }
    println("The answer is $ans")

    //part 2
//    var part2: Long  = 0
//    val range = inputDayNine.subList(0, inputDayNine.indexOf(ans))
//    val mutableRange = range.toMutableList()
//    for(i in range) {
//        var iter = 0
//        var x = 0.toLong()
//        x += i
//        if(x == ans) {
//            part2 = range[0] + range[iter]
//            break
//        } else {
//            ran
//        }
//        iter += 1
//    }
}