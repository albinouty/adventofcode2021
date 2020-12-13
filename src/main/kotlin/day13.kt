import java.io.File
import java.util.*
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val input = readFileAsLinesUsingUseLines("src/main/resources/inputday13.txt")
    println(input)
    val timestamp = input[0].toLong()
    val busList = input[1]
        .split(',')
        .filter { i -> i != "x" }
        .map { it.toInt() }

    fun findNearestDepatureTime(bus: Int): Pair<Int, Long> {
        val depatureTimes = mutableListOf<Int>()
        var y = bus
        while (true) {
            if (y > (timestamp - busList.max()!!) &&
                y < (timestamp + busList.max()!!)
            ) {
                depatureTimes.add(y)
                y += bus
            } else if (y > (timestamp + busList.max()!!)) {
                break
            } else y += bus
        }
        val close = depatureTimes
            .filter{ it > timestamp }
            .map { timestamp - it }
            .max()!!
            .absoluteValue
        return Pair(bus, close)
    }
    val busTimes = busList.map { findNearestDepatureTime(it) }
    val shortestWait = busTimes //maybe I could do this in the function?
        .map { it.second }
        .min()!!
    val fastestBus = busTimes.filter{ it.second == shortestWait }
    val ans = fastestBus[0].first * fastestBus[0].second
    println("The fastest bus is ${fastestBus[0].first}. You'll need to wait for ${fastestBus[0].second} minutes. These two numbers multiplied together is $ans")
}