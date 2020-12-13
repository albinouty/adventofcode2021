import java.io.File
import java.util.*
import kotlin.math.absoluteValue


fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
    val inputDayTwelve = readFileAsLinesUsingUseLines("src/main/resources/inputday12.txt")
    println(inputDayTwelve)
    var north = 0
    var south = 0
    var east = 0
    var west = 0
    var currDirection = "E"
    var deg = 0

    //figure out how to make a huge list of the cardinal directions.
    fun staticDirection(c: String, i: Int) {
        when(c) {
            "N" -> north += i
            "S" -> south += i
            "W" -> west += i
            "E" -> east += i
        }
    }

    fun moveForward(i: Int) {
        staticDirection(currDirection, i)
    }

    val directions = listOf("N", "E", "S", "W", "N", "E", "S", "W", "N", "E", "S", "W")
    fun compass(i: Int): String {
        currDirection =  when(currDirection) {
            "W" -> directions[3 + i]
            "E" -> directions[5 + i]
            "N" -> directions[4 + i]
            "S" -> directions[6 + i]
            else -> directions[1]
        }
        return currDirection
    }

    fun resolveDegree(i: Int) {
        var diff = i/90
        if (i > 180) {
            diff *= -1
        }
        currDirection = compass(diff)
    }

    fun movementDispatch(input: String) {
        val command = input.substring(0,1)
        val payload = input.substring(1).toInt()
        when(command) {
            "N", "S", "E", "W" -> staticDirection(command, payload)
            "L", "R" -> resolveDegree(payload)
            "F" -> moveForward(payload)
        }
    }

    for(i in inputDayTwelve) {
        movementDispatch(i)
    }

    val ans = (north - south).absoluteValue + (east - west).absoluteValue //figure out how to get the right values for the manhattan calc. north 3 and south 11 is south 8

    println(ans)


}