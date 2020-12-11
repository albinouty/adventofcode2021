import java.io.File

var x8 = 0
fun main(args: Array<String>) {
    var accumulator = 0
    fun readFileAsLinesUsingUseLines(fileName: String) = File(fileName).useLines { it.map {z ->
        Pair(z.substringBefore(" "), z.substringAfter(" ").toInt())
        }.toList()
    }
    val inputDayEight = readFileAsLinesUsingUseLines("src/main/resources/inputday8.txt")
    println(inputDayEight.size)
    val masList = mutableListOf<Int>()


    fun acc(i: Int) {
        accumulator += i
        x8 += 1
    }

    fun execute(input: Pair<String, Int>) {
        val cmd = input.first
        val num = input.second
        when(cmd) {
            "jmp" -> x8 += num
            "acc" -> acc(num)
            "nop" -> x8 += 1
        }
    }

    fun <T> hasDuplicates(arr: List<T>): Boolean {
        return arr.size != arr.distinct().count()
    }

    while(true) {
        masList.add(x8)
        if(hasDuplicates(masList)) {
            break
        }
        val next = inputDayEight[x8]
        execute(next)
    }
    println("The accumulator is at $accumulator")
    var iter = 0
    for(i in inputDayEight) {
        x8 = 0
        accumulator = 0
        val masterList = mutableListOf<Int>()
        val newList = inputDayEight.mapIndexed { index, pair ->
            if(pair.first == "jmp" && index == iter) {
                pair.copy("nop", pair.second)
            } else if( pair.first == "nop" && index == iter) {
                pair.copy("jmp", pair.second)
            } else pair
        }
        while(true) {
            masterList.add(x8)
            if(x8 > 600) {
                println("Successfully exited, accumulator at $accumulator")
                break
            }
            if(hasDuplicates(masterList)) {
                println("infinite loop, accumulator at $accumulator")
                break
            }
            val next = newList[x8]
            execute(next)
        }
        iter += 1
     }
}