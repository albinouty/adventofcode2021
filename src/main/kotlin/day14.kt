import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    fun readFirstLine(fileName: String): List<Char> = File(fileName).useLines { it.first().toList() }

    fun generateRules(): Map<String, Char> {
        val reader = BufferedReader(FileReader("src/main/resources/day14data.txt"))
        val map = mutableMapOf<String, Char>()
        //skip first two lines because they are not the boards
        for (i in 1..2) {
            reader.readLine()
        }
        reader.forEachLine {
            val b = it.substringBefore(" -> ")
            val a = it.substringAfter(" -> ").toCharArray()[0]
            map[b] = a
        }
        return map
    }
    val template = readFirstLine("src/main/resources/day14data.txt")

    fun insert(template: List<Char>, rules: Map<String, Char>): List<Char> {
        var tmpString = ""
        template.forEachIndexed { indx, char ->
            if(indx == template.size -1) {
                //do nothing
            } else if(indx.toLong() == 0.toLong()) {
                val str = "$char${template[indx+1]}"
                val insertValue = rules.getValue(str)
                val newString = "$char$insertValue${template[indx+1]}"
                tmpString = newString
            } else {
                val str = "$char${template[indx+1]}"
                val insertValue = rules.getValue(str)
                val newString = "$insertValue${template[indx+1]}"
                tmpString = "$tmpString$newString"
            }
        }
        val newTemplate = tmpString.toList()
        return newTemplate
    }

    var temp = mutableListOf<Char>()
    val rules = generateRules()
    for(i in 1..5) {
        if(i == 1) {
            temp = insert(template, rules).toMutableList()
        } else {
            temp = insert(temp, rules).toMutableList()
        }
    }

    val numbersByElement = temp.groupingBy { it }.eachCount()
//    var what = ""
//    temp.map {
//        what += it
//    }
    val max = numbersByElement.maxBy { it.value }!!.value
    val min = numbersByElement.minBy { it.value }!!.value
    println("The answer to part 1 is ${max - min}")
}