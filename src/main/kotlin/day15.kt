import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val input = mutableListOf(0,3,6)
    val time = measureTimeMillis {
        var iter = input.size
        while (true) {
            val sub = input.size - 1
            val lastElement = input.last()
            val container = input.subList(0, sub)
            iter += if (!container.contains(lastElement)
            ) {
                input.add(0)
                1
            } else {
                val last = container.indexOfLast { it == lastElement } + 1
                input.add(iter - last)
                1
            }
            if (iter == 2020) {
                break
            }
        }
        //part 1
        println("the answer is ${input[2019]}")
    }
    println(time/1000)

    //part 2

    val hash = HashMap<Int, List<Int>>()
    val input2 = listOf(0,3,6)
    var iterator2 = 0
    for(i in input2) {
        hash.put(i, listOf(iterator2))
        iterator2 += 1
    }

    while(true) {
        break
    }

//    fun resolve(b: Int) {
//        if(!hash.containsKey(b)) {
//            hash.replace(0, )
//        }
//    }
    println(hash)
}