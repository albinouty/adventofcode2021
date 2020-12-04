import java.io.File

fun main(args: Array<String>) {
    //read input
    //repeat input n times
    //since we start at position 1 (index 0) we need to add one to each x coordinate
    //immediately retrieve the string at whatever index y is
    //multiply x by whatever the index is to get how far in the string we must look
    //get the value at that index
    //if it is ".", do nothing.
    //If it is '#', add a value to the ans var
    val expand = 100
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val inputDayThree = readFileAsLinesUsingUseLines("src/main/resources/inputday3.txt")
    val expInput = inputDayThree.map { it.repeat(expand) }
    //day 2 input
    val uno = listOf(1, 1)
    val dos = listOf(3, 1)
    val tres = listOf(5, 1)
    val quatro = listOf(7, 1)
    val cinco = listOf(1, 2)

    fun tobogganRide(slope: List<Int>): Int {
        val ans = expInput.mapIndexed {idx, value ->
            if(idx % slope[1] == 0) {
                val check = value[slope[0] * (idx/slope[1])]
                if(check == '#'){
                    1
                } else 0
            } else 0
        }
        return ans.sum()
    }

    //part 1
    println(tobogganRide(dos))

    //part 2
    val ultraList =  listOf(uno, dos, tres, quatro, cinco)
    val indAns = ultraList.map { tobogganRide(it).toLong() }
    val ans2 = indAns.reduce {acc, i -> acc * i }
    println(ans2)
}