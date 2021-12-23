
fun main(args: Array<String>) {
    val winningScore = 1000
    val rollsPerTurn = 3
    var die = 1
    var dieTotal = 0
    //Player number, position (index), score
    val players = mutableListOf(Triple(1, 8 - 1, 0), Triple(2, 4 - 1, 0))
    fun part1() {
        val scores = listOf(1,2,3,4,5,6,7,8,9,10)
        fun score(pos: Int): Int {
            val tmp = scores[pos % scores.size]
            return tmp
        }
        fun takeTurn(pos: Int): Pair<Int, Int> {
            val maxScore = players.maxBy { it.third }?.third ?: 0
            val check = maxScore >= winningScore
            if(check) {
                return (Pair(-1,-1))
            }
            var turnMoves = 0
            var tmpPos = pos
            for(i in 1..rollsPerTurn) {
                if(check) {
                    break
                }
                if(die > 100) {
                    die = 1
                }
                turnMoves += die
                die += 1
                dieTotal += 1
            }
            tmpPos += turnMoves
            val turnScore = score(tmpPos)
            tmpPos = scores.indexOf(turnScore)
            return Pair(tmpPos, turnScore)
        }
        while(true) {
            for((indx, i) in players.indices.reversed().withIndex()) {
                val it = players[indx]
                val turnData = takeTurn(it.second)
                if(turnData.first == -1) {
                    break
                }
                val tmp = it.copy(it.first, turnData.first, it.third + turnData.second)
                players.removeAt(indx)
                players.add(indx, tmp)
            }
            val maxScore = players.maxBy { it.third }?.third ?: 0
            if(maxScore >= winningScore) {
                break
            }
        }
        val maxScore = players.maxBy { it.third }!!
        val minScore = players.minBy { it.third }!!
        println("The winning player is player ${maxScore.first} with a score of ${maxScore.third}")
        println("The losing player had a score of ${minScore.third}. The number of times the die has been rolled is $dieTotal. The product of these numbers is ${dieTotal * minScore.third}.")
    }
    fun part2() {
    }
    part1()
    part2()
}