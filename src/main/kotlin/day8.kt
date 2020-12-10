import java.io.File

fun main(args: Array<String>) {
    var accumulator = 0
    fun readFileAsLinesUsingUseLines(fileName: String) = File(fileName).useLines { it.map {z ->
        Pair(z.substringBefore(" "), z.substringAfter(" ").toInt())
        }.toList()
    }
    val inputDayEight = readFileAsLinesUsingUseLines("src/main/resources/inputday8.txt")
    println(inputDayEight[1].first)
    println(inputDayEight)
    val masList = mutableListOf<Int>()
    var x = 0
    fun jmp(i: Int) {
        x += i
    }

    fun acc(i: Int) {
        accumulator += i
        x += 1
    }

    fun nop() {
        x += 1
    }

    fun execute(input: Pair<String, Int>) {
        val cmd = input.first
        val num = input.second
        when(cmd) {
            "jmp" -> jmp(num)
            "acc" -> acc(num)
            "nop" -> nop()
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