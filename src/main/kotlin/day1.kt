import java.io.File

fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val day1input = readFileAsLinesUsingUseLines("src/main/resources/day1data.txt").map { it.toInt() }

    fun day1() {
        /**
        Part 1
         */
        fun depthCalculator(list: List<Int>): Int {
            var g = 0
            var count = 0
            for(i in list) {
                if(g == 0) {
                    g += 1
                } else {
                    if(i - list[g-1] > 0) {
                        count += 1
                    }
                    g += 1
                }
            }
            return count
        }
        println("Part 1 answer is ${depthCalculator(day1input)}")

        /**
        Part 2
         */
        val triVals = mutableListOf<Int>()
        var index = 0
        for(i in day1input) {
            if(index == (day1input.lastIndex - 1)){
                break
            } else {
                val num = i + day1input[index+1] + day1input[index+2]
                triVals.add(num)
                index += 1
            }
        }
        println("Part 2 answer is ${depthCalculator(triVals)}")
    }
    day1()
}
