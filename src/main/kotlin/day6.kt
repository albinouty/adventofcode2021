import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    val reader = BufferedReader(FileReader("src/main/resources/inputday6.txt"))
    val answers = mutableListOf<String>()
    var line = ""
    var x = 0
    while (true) {
        val tmp = reader.readLine()
        if (tmp == null) {
            if (!line.isEmpty()) {
                answers.add(line)
            }
            break
        } else if (tmp.isEmpty()) {
            if (!line.isEmpty()) {
                answers.add(line)
                x += 1
            }
            line = ""
        } else {
            if (line.isEmpty()) {
                line = tmp
            } else {
                line += " $tmp"
            }
        }
    }
    fun countOccurrences(s: String, ch: Char): Int {
        return s.filter { it == ch }.count()
    }

    //part 1
    val countsPartOne = answers.map {
        val chars = mutableListOf<Char>()
        for (i in it) {
            if (i != ' ') {
                chars.add(i)
            }
        }
        chars.distinct().count()
    }
    println("The sum of the counts is ${countsPartOne.sum()}.")

    //part 2
    val countsPartTwo = answers.map {
        val chars = mutableListOf<Char>()
        val numOfPeople = countOccurrences(it, ' ') + 1
        for (i in it) {
            val freq = countOccurrences(it, i)
            if (freq == numOfPeople) {
                chars.add(i)
            }
        }
        chars.distinct().count()
    }
    println("The number of questions to which everyone answered yes is ${countsPartTwo.sum()}.")
}