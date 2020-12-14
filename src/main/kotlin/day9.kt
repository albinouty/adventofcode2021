import java.io.File

var churn = 25
fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val inputDayNine = readFileAsLinesUsingUseLines("src/main/resources/inputday9.txt").map { it.toLong() }
    fun getPreamble(iter: Int): List<Long> {
        return inputDayNine.subList(iter, churn+iter)
    }
    val values = inputDayNine.subList(churn, inputDayNine.size)
    println(values)

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

    var iterator = 0
    var ans = 0.toLong()
    for(i in values) {
        if(validValues(iterator).contains(i)) {
            iterator += 1
        } else ans = i
    }
    println(ans)
}