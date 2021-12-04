import java.io.File

fun main(args: Array<String>) {
    /**
    Part 1
     */
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }

    fun day1() {
        var g = 0
        var count = 0
        val day1input = readFileAsLinesUsingUseLines("src/main/resources/day1data.txt").map { it.toInt() }
        for(i in day1input) {
            if(g == 0) {
                g += 1
            } else {
                if(i - day1input[g-1] > 0) {
                    count += 1
                }
                g += 1
            }
        }
        println(count)
    }
    day1()

    /**
    Part 2
     */


}
