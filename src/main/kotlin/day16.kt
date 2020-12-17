import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {

    fun getRange(s: String): IntRange {
        val reg = Regex("\\d{1,9}-\\d{1,9}")
        val stringRange = reg.find(s)!!.value
        val first = stringRange.substringBefore('-').toInt()
        val second = stringRange.substringAfter('-').toInt()
        return first..second
    }

    fun getRules(): HashMap<String, List<IntRange>> {
        val reader = BufferedReader(FileReader("src/main/resources/inputday16.txt"))
        val hm = HashMap<String, List<IntRange>>()
        while(true) {
            val tmp = reader.readLine()
            if (tmp.isEmpty()) {
                break
            }
            val key = tmp.substringBefore(':')
            val rangeOne = getRange(tmp.substringBefore("or "))
            val rangeTwo = getRange(tmp.substringAfter("or "))
            val range = listOf(rangeOne, rangeTwo)
            hm[key] = range
        }
        return hm
    }

    fun getYourTicket(): List<Int> {
        val reader = BufferedReader(FileReader("src/main/resources/inputday16.txt"))
        var ticket = ""
        while(true) {
            val tmp = reader.readLine()
            if(tmp.equals("your ticket:")) {
                ticket = reader.readLine()
                break
            }
        }
        val values = ticket.split(',').map { it.toInt() }
        return values
    }

    fun getNearbyTickets(): List<List<Int>> {
        val reader = BufferedReader(FileReader("src/main/resources/inputday16.txt"))
        val outer = mutableListOf<List<Int>>()
        while(true) {
            val tmp = reader.readLine()
            if(tmp.equals("nearby tickets:")){
                while(true) {
                    val y = reader.readLine() ?: break
                    val inner = y.split(',').map { it.toInt() }
                    outer.add(inner)
                }
                break
            }
        }
        return outer
    }

    val nearbyTickets = getNearbyTickets()
    val rules = getRules()
    val ranges = rules.values.flatten()
    val invalidValues = mutableListOf<List<Int>>()
    for(i in nearbyTickets) {
        val invalid = mutableListOf<Int>()
        for (a in i) {
            for(b in ranges) {
                if (a !in b) {
                    invalid.add(a)
                }
            }

        }
        val distinct = invalid.distinct().toMutableList()
        for(c in distinct) {
            val count = invalid.count { it == c }
            val overall = ranges.count()
            if(count != overall){
                invalid.removeAll { it == c }
            }
        }
        invalidValues.add(invalid.distinct())
    }
    println("The ticket scanning error rate is ${invalidValues.flatten().sum()}.")
}