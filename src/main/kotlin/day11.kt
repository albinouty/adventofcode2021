//import java.awt.Point
//import java.io.File
//
//fun main(args: Array<String>) {
//    val inputLines = File("src/main/resources/day11data.txt").readLines()
//
//    fun Point.neighbours(): List<Point> = listOf(
//        Point(x, y + 1),
//        Point(x, y - 1),
//        Point(x + 1, y),
//        Point(x - 1, y),
//        Point(x + 1, y + 1),
//        Point(x + 1, y - 1),
//        Point(x - 1, y + 1),
//        Point(x - 1, y - 1)
//    )
//
//    fun parseInput(): MutableMap<Point, Int> {
//        val result = mutableMapOf<Point, Int>()
//
//        inputLines.forEachIndexed { y, line ->
//            line.forEachIndexed { x, octupus ->
//                result[Point(x, y)] = octupus.toString().toInt()
//            }
//        }
//
//        fun MutableMap<Point, Int>.increaseEnergyLevels() {
//            this.forEach {
//                this[it.key] = this[it.key]!! + 1
//            }
//        }
//
//        fun MutableMap<Point, Int>.causeFlashes(alreadyFlashed: Set<Point>): Int {
//            val octopiToFlash = this.filter { it.value >= 10 }
//
//            if (octopiToFlash.isEmpty()) return 0
//
//            var flashCount = 0
//            val workingAlreadyFlashed = alreadyFlashed.toMutableSet()
//
//            octopiToFlash.forEach { (position, _) ->
//                flashCount += 1
//                this[position] = 0
//                workingAlreadyFlashed.add(position)
//
//                position.neighbours().filter { !workingAlreadyFlashed.contains(it) }.forEach { neighbour ->
//                    val neighbourEnergy = this[neighbour]
//
//                    if (neighbourEnergy != null) {
//                        this[neighbour] = neighbourEnergy + 1
//                    }
//                }
//            }
//
//
//            fun part1() {
//                val octopi = parseInput()
//                var total = 0
//
//                (0 until 100).forEach { _ ->
//                    octopi.increaseEnergyLevels()
//                    total += octopi.causeFlashes(emptySet())
//
//                    println(octopi)
//                }
//
//                println(total)
//            }
//
//            fun part2() {
//                val octopi = parseInput()
//                var index = 0
//
//                while (octopi.causeFlashes(emptySet()) != 100) {
//                    index += 1
//                    octopi.increaseEnergyLevels()
//                }
//
//                println(index)
//            }
//
//            fun MutableMap<Point, Int>.increaseEnergyLevels() {
//                this.forEach {
//                    this[it.key] = this[it.key]!! + 1
//                }
//            }
//
//            fun MutableMap<Point, Int>.causeFlashes(alreadyFlashed: Set<Point>): Int {
//                val octopiToFlash = this.filter { it.value >= 10 }
//
//                if (octopiToFlash.isEmpty()) return 0
//
//                var flashCount = 0
//                val workingAlreadyFlashed = alreadyFlashed.toMutableSet()
//
//                octopiToFlash.forEach { (position, _) ->
//                    flashCount += 1
//                    this[position] = 0
//                    workingAlreadyFlashed.add(position)
//
//                    position.neighbours().filter { !workingAlreadyFlashed.contains(it) }.forEach { neighbour ->
//                        val neighbourEnergy = this[neighbour]
//
//                        if (neighbourEnergy != null) {
//                            this[neighbour] = neighbourEnergy + 1
//                        }
//                    }
//                }
//
//                return flashCount + this.causeFlashes(workingAlreadyFlashed)
//            }
//
//            fun Point.neighbours(): List<Point> = listOf(
//                Point(x, y + 1),
//                Point(x, y - 1),
//                Point(x + 1, y),
//                Point(x - 1, y),
//                Point(x + 1, y + 1),
//                Point(x + 1, y - 1),
//                Point(x - 1, y + 1),
//                Point(x - 1, y - 1)
//            )
//        }A
//    }
//}