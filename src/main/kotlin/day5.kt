import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    val reader = BufferedReader(FileReader("src/main/resources/inputday5.txt"))
    val bfPtrn = Regex("^[B|F]{7}")
    val cPtrn = Regex("[R|L]{3}")
    val rowsMas = IntArray(128) {1 * (it + 1)}
    val seatsMas = IntArray(8) {1 * (it + 1)}

    fun resolveArr(input: String, upper: Char): Int {
        var arr = if(upper == 'B') {
            rowsMas.clone()
        } else seatsMas.clone()
        for(i in input.indices)
            arr = if(input[i] == upper) {
                val n = (arr.size + 1)/2
                val b = arr.size
                if(arr.size == 2) {
                    return arr[1] - 1
                } else arr.copyOfRange(n, b)
            } else {
                val n = (arr.size + 1) /2
                if(arr.size == 2) {
                    return arr[0] - 1
                } else arr.copyOfRange(0, n)
            }
        return 0
    }

    fun findSeat(input: List<Int>): Int {
        var x = input[0]
        for(i in input) {
            if(i == x) {
                x += 1
            } else break
        }
        return x
    }

    val seatIds = mutableListOf<Int>()
    while(true) {
        val tmp = reader.readLine() ?: break
        val rowLoc = bfPtrn.find(tmp)!!.value
        val colLoc = cPtrn.find(tmp)!!.value
        val row = resolveArr(rowLoc, 'B')
        val col = resolveArr(colLoc, 'R')
        val seat = (row * 8) + col
        seatIds.add(seat)
    }
    //part 1
    println("The highest seat ID on a boarding pass is ${seatIds.max()}")

    //part 2
    seatIds.sort()
    println("Your seat is ${findSeat(seatIds)}")
}
