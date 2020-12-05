import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    val reader = BufferedReader(FileReader("src/main/resources/inputday5.txt"))
    val bfPtrn = Regex("^[B|F]{7}")
    val cPtrn = Regex("[R|L]{3}")
    val rowsMas = IntArray(128) {1 * (it + 1)}
    val rowLocations = mutableListOf<String>()

    fun resolveRow(input: String): Int {
        var rows = rowsMas.clone()
        for(i in input.indices)
            if(input[i] == 'B') {
                val n = (rows.size + 1)/2
                val b = rows.size
                if(rows.size == 2) {
                    return rows[1] - 1
                } else rows = rows.copyOfRange(n, b)
            } else {
                val n = (rows.size + 1) /2
                if(rows.size == 2) {
                    return rows[0] - 1
                } else rows = rows.copyOfRange(0, n)
            }
        return 0
    }

    while(true) {
        val tmp = reader.readLine() ?: break
        val rowLoc = bfPtrn.find(tmp)!!.value
        println(resolveRow(rowLoc))
    }


}
