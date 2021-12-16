import java.io.File

fun main(args: Array<String>) {
    fun readFirstLine(fileName: String): String = File(fileName).useLines { it.toList() }[0]
    fun day6() {
        val z = 0.toLong()
        val counts: MutableList<Long> = listOf(z,z,z,z,z,z,z,z,z).toMutableList()
        val input = readFirstLine("src/main/resources/day6data.txt").split(",").map { it.toLong() }
        for(fish in input) {
            val x = fish.toInt()
            counts[x] += 1.toLong()
        }
        repeat(3) {
            counts.add(8, counts.removeAt(0))
            counts[6] += counts[8]
        }
        println(counts.sum())
    }
    day6()
}