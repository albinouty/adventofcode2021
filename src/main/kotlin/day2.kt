import java.io.File

fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String): List<Pair<String, Int>> = File(fileName).useLines { it.map {
            z -> Pair(z.substringBefore(" "), z.substringAfter(" ").toInt())
        }.toList()
    }
    val day2input = readFileAsLinesUsingUseLines("src/main/resources/day2data.txt")
    fun day2() {
        /**
        Part 1
         */
        var dist = 0
        var depth = 0
        for(i in day2input) {
            when(i.first) {
                "forward" -> dist += i.second
                "down" -> depth += i.second
                "up" -> depth -= i.second
                else -> throw Exception("FATAL ERROR: Unrecognized command in file")
            }
        }
        println("Horizontal position is $dist and depth is $depth. These values multiplied together is ${dist * depth}.")

        /**
        Part 2
         */
    }
    day2()
}