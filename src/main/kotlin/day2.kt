import java.io.File

fun main(args: Array<String>) {
    //read file into list, one element per line
    //map the list into an array of ints and for each item in the list do the following:
    //get limits with regex
    //get letter in question
    //read the password to see if it complies with the password rule
    //assign a value of 1 or 0, depending on the results
    //sum the array to get the count of passwords complying to the rule

    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val inputDayTwo = readFileAsLinesUsingUseLines("src/main/resources/inputday2.txt")
    val c = ":"

    fun getLetter(str: String): Char {
        return str.substringBefore(c).last()
    }

    fun getBounds(str: String): List<Int?> {
        val delim = "-"
        val ptrn = Regex("[^\\d]*[\\d]+[^\\d]+([\\d]+)")
        val result = ptrn.find(str)
        val bounds = result?.value
        val lower = bounds?.substringBefore(delim)?.toInt()
        val upper = bounds?.substringAfter(delim)?.toInt()
        return listOf(lower, upper)
    }

    fun checkPassword(pass: String, letter: Char, bounds: List<Int?>): Int {
        val count = pass.filter { it == letter }.count()
        return if(count >= bounds[0]!! && count <= bounds[1]!!) {
            1
        } else 0
    }

    fun getPass(str: String): String {
        return str.substringAfter(c)
    }

    val answer = inputDayTwo.map {
        val letter = getLetter(it)
        val bounds = getBounds(it)
        val pass = getPass(it)
        checkPassword(pass, letter, bounds)
    }
    val sum = answer.sum()
    println(sum)
}