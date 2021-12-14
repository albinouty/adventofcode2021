import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    fun readFirstLineOfFile(fileName: String): String = File(fileName).useLines { it.first() }
    val rawNumbers = readFirstLineOfFile("src/main/resources/day4data.txt")
    val numbers: List<Int> = rawNumbers.split(",").toList().map { it.toInt() }
    val reader = BufferedReader(FileReader("src/main/resources/day4data.txt"))

    //skip first two lines because they are not the boards
    for (i in 1..2) {
        reader.readLine()
    }
    val array = mutableListOf<List<String>>()
    reader.forEachLine {
        if(it.length >  0) {
            val innerList = it.split(" ").toMutableList()
            innerList.removeAll {y ->
                y == ""
            }
            innerList.map { n -> n.toInt() }
         array.add(innerList)
        }
    }
    val boards = array.map { out ->
        out.map {
            it.toInt()
        }
    }.chunked(5)

//    fun markBoardv1(number: Int, arr: List<Int>): List<Int> {
//        val tmp = arr.toMutableList()
//        tmp.replaceAll{
//            if(it == number){
//                -1
//            } else it
//        }
//        return tmp
//    }
//
//    fun markBoardv2(number: Int, arr: List<List<Int>>): List<List<Int>> {
//        val llTmp = mutableListOf<List<Int>>()
//        arr.forEach{
//            val tmp = it.toMutableList()
//            tmp.replaceAll{i ->
//                if(i == number){
//                    -1
//                } else i
//            }
//            llTmp.add(tmp)
//        }
//        return llTmp
//    }

    fun markBoard(number: Int, arr: List<List<List<Int>>>): List<List<List<Int>>> {
        val llTmp = mutableListOf<List<Int>>()
        val boardsTmp = mutableListOf<List<List<Int>>>()
        arr.map { b ->
            b.forEach{
                val tmp = it.toMutableList()
                tmp.replaceAll{i ->
                    if(i == number){
                        -1
                    } else i
                }
                llTmp.add(tmp)
            }
        }
        boardsTmp.add(llTmp)
        return boardsTmp[0].chunked(5)
    }

//    fun checkBoardsv1(arr: List<Int>): Boolean {
//        val hash = arr.toHashSet()
//        return hash.size == 1
//    }

    fun checkVerticalWin(arr: List<List<Int>>, idx: Int): Boolean {
        val vertArr = mutableListOf<Int>()
        for (i in arr) {
            vertArr.add(i[idx])
        }
        val hash2 = vertArr.toHashSet()
        return if(hash2.size == 1) {
            return true
        } else false
    }

    fun checkBoards(arr: List<List<Int>>): Boolean {
        var ans = false
        var iter = 0
        for(i in arr) {
            val vertArr = mutableListOf<Int>()
            val hash = i.toHashSet()
            if (hash.size == 1) {
                ans = true
                break
            } else {
                for (id in arr) {
                    vertArr.add(id[iter])
                }
                val hash2 = vertArr.toHashSet()
                if(hash2.size == 1) {
                    ans = true
                    break
                }
            }
            iter += 1
        }
        return ans
    }

    fun draw(board: List<List<List<Int>>>) {
        var interBoard = board.toMutableList()
        var ans = -1
        for(i in numbers) {
            interBoard = markBoard(i, interBoard).toMutableList()
            for(n in interBoard) {
                if(checkBoards(n)) {
                    println("The winning number is $i and board number ${interBoard.indexOf(n) + 1} is victorious")
                    ans = i
                    val finalNums = mutableListOf<Int>()
                        for(x in n) {
                            x.filter { p ->
                                p != -1
                            }.map { w ->
                                finalNums.add(w)
                            }
                        }
                    val finalSum = finalNums.sum()
                    println("The final sum is $finalSum, and this multiplied by the winning number ($i) is equal to ${i * finalSum}")
                    break
                }
            }
            if(ans != -1) {
                break
            }
        }
    }

    fun drawPartTwo(board: List<List<List<Int>>>) {
        var interBoard = board.toMutableList()
        val winningBoards = mutableListOf<Int>()
        var ans = -1
        for(i in numbers) {
            interBoard = markBoard(i, interBoard).toMutableList()
            for(n in interBoard) {
                if(checkBoards(n)) {
                    winningBoards.add(interBoard.indexOf(n))
                    if(winningBoards.toHashSet().size == interBoard.size) {
                        ans = i
                        println("The last winning number is $i and board number ${interBoard.indexOf(n) + 1} is the last to be victorious")
                        val finalNums = mutableListOf<Int>()
                        for(x in n) {
                            x.filter { p ->
                                p != -1
                            }.map { w ->
                                finalNums.add(w)
                            }
                        }
                        val finalSum = finalNums.sum()
                        println("The final sum is $finalSum, and this multiplied by the winning number ($i) is equal to ${i * finalSum}")
                        break
                    }
                }
            }
            if(ans != -1) {
                break
            }
        }
    }
    fun day4() {
        println(checkBoards(boards[0]))
        draw(boards)
        drawPartTwo(boards)
    }
    day4()
}