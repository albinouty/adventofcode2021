fun main(args: Array<String>) {
    val targetY = -176..-117
    val targetX = 79..137
    fun isInTargetArea(t: Pair<Int, Int>): Pair<Boolean, List<Int>> {
        var xVel = t.first
        var yVel = t.second
        var xPos = 0
        var yPos = 0
        val yPositions = mutableListOf<Int>()
        while(true) {
            xPos += xVel
            yPos += yVel
            yPositions.add(yPos)
            if(xVel == 0) {
                xVel = 0
            } else if(xVel > 0) {
                xVel -= 1
            } else xVel += 1
            yVel -= 1

            if (xPos in targetX && yPos in targetY) {
                return Pair(true, yPositions)
            } else if (xPos > targetX.last || yPos <  targetY.first) {
                return Pair(false, emptyList())
            }
        }
    }
    fun part1() {
        val accurateVelocities = mutableListOf<Pair<Pair<Int, Int>, List<Int>>>()
        val iter = 2000 //tinker with this if you get the wrong answer
        repeat(iter) {x ->
            repeat(iter) {y ->
                val vel = Pair(x, y)
                val res = isInTargetArea(vel)
                if(res.first) {
                    accurateVelocities.add(Pair(vel, res.second))
                }
            }
        }
        val maxY = accurateVelocities.maxBy { it.first.second }
        println("The best initial velocity is ${maxY!!.first} and the highest the probe goes is ${maxY.second.maxBy { it }}")
    }
    fun part2() {
        val accurateVelocities = mutableListOf<Pair<Pair<Int, Int>, List<Int>>>()
        val iter = 5000 //tinker with this if you get the wrong answer
        val yRange = -iter..iter
        repeat(iter) {x ->
            for(y in yRange) {
                val vel = Pair(x, y)
                val res = isInTargetArea(vel)
                if(res.first) {
                    accurateVelocities.add(Pair(vel, res.second))
                }
            }
        }
        val maxY = accurateVelocities.maxBy { it.first.second }
        println("The number of initial velocities that get into the target area is ${accurateVelocities.size}")
    }
//    part1()
    part2()
}