import java.io.File

fun main(args: Array<String>) {
    fun day5() {

    fun createPair(str: String): Pair<Int, Int> {
        val x = str.substringBefore(",").toInt()
        val y = str.substringAfter(",").toInt()
        return Pair(x, y)
    }

    fun readFileAsLinesUsingUseLines(fileName: String): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> =
        File(fileName).useLines {
            it.map { z ->
                val firstPair = createPair(z.substringBefore(" -> "))
                val secondPair = createPair(z.substringAfter(" -> "))
                Pair(firstPair, secondPair)
            }.toList()
        }

    val day5input = readFileAsLinesUsingUseLines("src/main/resources/day5data.txt")
//        .filter {
//        it.first.first == it.second.first || it.first.second == it.second.second
//    }

    fun getGridDimensions(input: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Pair<Int, Int> {
        var maxX = 0
        var maxY = 0
        input.forEach {
            if (it.first.first > maxX) {
                maxX = it.first.first
            } else if (it.second.first > maxX) {
                maxX = it.second.first
            }
            if (it.first.second > maxY) {
                maxY = it.first.second
            } else if (it.second.second > maxY) {
                maxY = it.second.second
            }
        }
        return Pair(maxX, maxY)
    }

    fun createGrid(p: Pair<Int, Int>): List<List<Int>> {
        val xList = mutableListOf<Int>()
        val list = mutableListOf<List<Int>>()
        for (i in 0..p.first) {
            xList.add(0)
        }
        for (i in 0..p.second) {
            list.add(xList)
        }
        return list
    }

    val grid = createGrid(getGridDimensions(day5input)).toMutableList()

    fun drawLines(i: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>) {
        for (p in i) {
            if (p.first.first == p.second.first && p.first.second != p.second.second) {
                //draw vertical line
                val x = p.first.first
                val tmp = listOf(p.first.second, p.second.second)
                val min = tmp.min() ?: tmp[0]
                val max = tmp.max() ?: tmp[0]
                for (q in min..max) {
                    val l = grid[q].toMutableList()
                    val z = l[x]
                    l.removeAt(x)
                    l.add(x, z + 1)
                    grid.removeAt(q)
                    grid.add(q, l)
                }
            }
            else if (p.first.second == p.second.second && p.first.first != p.second.first) {
                //draw horizontal line
                val y  = p.first.second
                val tmp = listOf(p.first.first, p.second.first)
                val min = tmp.min() ?: tmp[0]
                val max = tmp.max() ?: tmp[0]
                val l = grid[y].toMutableList()
                for (q in min..max){
                    val t = l[q]
                    l.removeAt(q)
                    l.add(q, t + 1)
                    grid.removeAt(y)
                    grid.add(y, l)
                }
            } else if (p.first.first < p.second.first && p.first.second < p.second.second) {
                //draw diagonal line
                //right to left, going down
                val startY = p.first.second
                val endY = p.second.second
                for (q in startY..endY) {
                    val l = grid[q].toMutableList()
                    val z = l[q]
                    l.removeAt(q)
                    l.add(q, z + 1)
                    grid.removeAt(q)
                    grid.add(q, l)
                }
            } else {
                //figure out rows (y max min)
                val tmpY = listOf(p.first.second, p.second.second)
                val minY = tmpY.min() ?: tmpY[0]
                val maxY = tmpY.max() ?: tmpY[0]
                val maxX = listOf(p.first.first, p.second.first).max()!!
                for(q in minY..maxY) {
                    val l = grid[q].toMutableList()
                    val z = l[maxX - q]
                    l.removeAt(maxX - q)
                    l.add(maxX - q, z + 1)
                    grid.removeAt(q)
                    grid.add(q, l)
                }
                //figure out index to edit (starting x - current y)
            }
            //left to right, going down
        }
    }

        fun findOverlap(i: List<List<Int>> ): Int {
            var total = 0
            for(l in i) {
                val overLaps = l.filter { it > 1 }.count()
                total += overLaps
            }
            return total
        }
    drawLines(day5input)

//        grid.forEach {
//            println(it)
//            System.getProperty("line.separator")
//        }
        val overlaps = findOverlap(grid)
            println("The total number of overlaps is $overlaps")
    }

    day5()
}
