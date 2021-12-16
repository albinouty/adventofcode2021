import java.io.File
import kotlin.math.abs
//read file into list of ints
//get max number in list
//iterate from 1 to max number
//create a variable to hold how much fuel would be used. Start it at zero.
//for each iteration, loop through the list getting the absolute value of the iteration minus the list number.
//Add the result of this subtraction to variable previously made.
//When done looping through the list, add the subtraction result variable to a map where the key is the iteration (from the outermost loop, representing the position) and the value is how much fuel would get spent to get there.
//Find the min of the map and get how much fuel would be spent
fun main(args: Array<String>) {
    fun readFirstLine(fileName: String): String = File(fileName).useLines { it.toList() }[0]
    fun day7part1() {
        val data = readFirstLine("src/main/resources/day7data.txt").split(",").toList().map { it.toInt() }
        val maxCrab = data.max()!!
        val fuelUsage = mutableMapOf<Int, Int>()
        repeat(maxCrab) {
            var fuel = 0
            for (i in data) {
                fuel += abs(it - i)
            }
            fuelUsage[it] = fuel
        }
        val min = fuelUsage.minBy { it.value }!!
        println("The minimum usage of fuel would be ${min.value} and this would be at position ${min.key}")
    }
    fun day7part2() {
        fun readFirstLine(fileName: String): String = File(fileName).useLines { it.toList() }[0]
        val data = readFirstLine("src/main/resources/day7data.txt").split(",").toList().map { it.toInt() }
        val maxPosition = data.max()!!
        val fuelUsage = mutableMapOf<Int, Int>()
        repeat(maxPosition) {pos ->
            var fuel = 0
            for (crab in data) {
                val fuelUsage = abs(pos - crab)
                for(num in 1..fuelUsage) {
                    fuel += num
                }
            }
            fuelUsage[pos] = fuel
        }
        val min = fuelUsage.minBy { it.value }!!
        println("The minimum usage of fuel would be ${min.value} and this would be at position ${min.key}")
    }
    day7part1()
    day7part2()
}