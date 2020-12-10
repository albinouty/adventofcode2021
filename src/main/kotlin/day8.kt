import java.io.File

fun main(args: Array<String>) {
    var accumulator = 0
    fun readFileAsLinesUsingUseLines(fileName: String) = File(fileName).useLines { it.map {z ->
        Pair(z.substringBefore(" "), z.substringAfter(" ").toInt())
        }.toList()
    }
    val inputDayEight = readFileAsLinesUsingUseLines("src/main/resources/inputday8.txt")
    val masList = mutableListOf<Int>()
    var x = 0

    fun acc(i: Int) {
        accumulator += i
        x += 1
    }

    fun execute(input: Pair<String, Int>) {
        val cmd = input.first
        val num = input.second
        when(cmd) {
            "jmp" -> x += num
            "acc" -> acc(num)
            "nop" -> x += 1
        }
    }

    fun <T> hasDuplicates(arr: List<T>): Boolean {
        return arr.size != arr.distinct().count()
    }

    while(true) {
        masList.add(x)
        if(hasDuplicates(masList)) {
            break
        }
        val next = inputDayEight[x]
        execute(next)
    }
    println(accumulator)
}