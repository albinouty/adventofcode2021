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
    val x = 3
    val y = 1

    fun tobogganRide(x: Int, y: Int): Int {
        val ans = expInput.mapIndexed {idx, value ->
            if(idx % y == 0) {
                val check = value[x * (idx/y)]
                if(check == '#'){
                    1
                } else 0
        } else 0

        }
        return ans.sum()
    }

    println(tobogganRide(x, y))

}