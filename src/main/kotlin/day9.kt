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

    fun getBasin(coordinate: Pair<Int, Int>, height: Int): List<Int> {
        val next = height+1
        val basin = mutableListOf<Int>()
        val nextMap = mutableMapOf<Pair<Int, Int>, Int>()
        basin.add(height)
        val above = input.getOrNull(coordinate.first-1)?.getOrNull(coordinate.second) ?: 13
        if(above == next) {
            basin.add(above)
            nextMap[Pair(coordinate.first-1, coordinate.second)] = above
        }
        val below = input.getOrNull(coordinate.first+1)?.getOrNull(coordinate.second) ?: 13
        if(below == next) {
            basin.add(below)
            nextMap[Pair(coordinate.first+1, coordinate.second)] = below
        }
        val left = input.getOrNull(coordinate.first)?.getOrNull(coordinate.second-1) ?: 13
        if(left == next) {
            basin.add(left)
            nextMap[Pair(coordinate.first, coordinate.second-1)] = left
        }
        val right = input.getOrNull(coordinate.first)?.getOrNull(coordinate.second+1) ?: 13
        if(right == next) {
            basin.add(right)
            nextMap[Pair(coordinate.first, coordinate.second+1)] = right
        }
        if(nextMap.isNotEmpty()) {
            nextMap.forEach {
                getBasin(it.key, it.value).forEach { p ->
                    basin.add(p)
                }
            }
        }
        return basin
    }

    fun part2() {
        val lows = mutableMapOf<Pair<Int, Int>, Int>()
        input.forEachIndexed { outerIter, list ->
            list.forEachIndexed { innerIter, height ->
                if(checkIfLowest(height, outerIter, innerIter)) {
                    val lowLocation = Pair(outerIter, innerIter)
                    lows[lowLocation] = height
                }
            }
        }

        val basins = mutableListOf<Int>()
        lows.forEach {
            //the lowpoint is put into a list
            //call a function which will start on the lowpoint and look up, down, left, and right in the  main input variable
            //any of those values that are LARGER than it by 1 need to have two things happen
            //1 - that value needs to be put into the list with the original lowpoint
            //2 - put into map along with its position in the main input variable (list b)
            //call the same function as above
            //if the function is called and the map is zero, return nothing
            val what = getBasin(it.key, it.value)
            println(what)
        }

        println(lows)
    }
    part1()
    part2()
}