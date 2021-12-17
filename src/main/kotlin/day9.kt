import java.io.File

fun main(args: Array<String>) {
    //adjust this for the day's specific data
    fun readFileAsLinesUsingUseLines(fileName: String) = File(fileName).useLines {
        it.toList().map {s ->
            s.map {c ->
                c.toString().toInt()
            }
        }
    }
    val input = readFileAsLinesUsingUseLines("src/main/resources/day9data.txt")
    //for every item in all lists
    //for each item, need to look at the previous list, same index
    //next list, same index
    //same list, previous item
    //same list, next item
    //ignore index out of bounds errors

    fun checkIfLowest(height: Int, currListIndex: Int, currHeightIndex: Int): Boolean {
        val above = input.getOrNull(currListIndex-1)?.getOrNull(currHeightIndex) ?: 10
        val below = input.getOrNull(currListIndex+1)?.getOrNull(currHeightIndex) ?: 10
        val left = input.getOrNull(currListIndex)?.getOrNull(currHeightIndex-1) ?: 10
        val right = input.getOrNull(currListIndex)?.getOrNull(currHeightIndex+1) ?: 10
        val list = listOf(above, below, left, right).filter { height >= it }
        return list.isEmpty()
    }

    fun part1() {
        val lows = mutableListOf<Int>()
        input.forEachIndexed { outerIter, list ->
            list.forEachIndexed { innerIter, height ->
                if(checkIfLowest(height, outerIter, innerIter)) {
                    lows.add(height)
                }
            }
        }
        val risk = lows.map { it + 1 }
        println(risk.sum())
//        input.forEach {
//            println(it)
//            System.getProperty("line.separator")
//        }
    }

    fun part2() {
    }
    part1()
    part2()
}