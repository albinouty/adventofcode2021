import java.io.File

fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String) = File(fileName).useLines { it ->
        it.toList().map {s ->
            s.map {c ->
                c.toString().toInt()
            }
        }
    }
    val day3input = readFileAsLinesUsingUseLines("src/main/resources/day3data.txt")
    fun convert(bit: String): Int {
        return Integer.parseInt(bit,2)
    }
    fun day3() {
        fun bitCounter(data: List<List<Int>>, index: Int) : Pair<Int, Int> {
            var on = 0
            var off = 0
            for(i in data) {
                if(i[index] == 1) {
                    on += 1
                } else off += 1
            }
            return Pair(on, off)
        }
        val gamma = mutableListOf<Int>()
        val epsilon = mutableListOf<Int>()
        val positions = day3input[0].size
        for(i in 0 until positions) {
            val bits = bitCounter(day3input, i)
            if (bits.first > bits.second) {
                gamma.add(1)
                epsilon.add(0)
            } else {
                gamma.add(0)
                epsilon.add(1)
            }
        }
        var stringGammaBit = ""
        gamma.map {
            stringGammaBit = "$stringGammaBit$it"
        }
        var stringEpsilonBit = ""
        epsilon.map {
            stringEpsilonBit = "$stringEpsilonBit$it"
        }
        val gammaFinal = convert(stringGammaBit)
        val epsilonFinal = convert(stringEpsilonBit)
        println("Part 1: gamma value is ${gammaFinal} and epsilon value is ${epsilonFinal}. Multiplied together these are ${epsilonFinal*gammaFinal}")
    }

    fun day3part2() {
        fun mostSignificantBit(data: List<List<Int>>, index: Int) : Int {
            var on = 0
            var off = 0
            for(i in data) {
                if(i[index] == 1) {
                    on += 1
                } else off += 1
            }
            return if( on >= off) {
                1
            } else 0
        }

        fun reduce(data: List<List<Int>>, index: Int, useMostCommon: Boolean): String{
            var varIndex = index
            val sigBit = mostSignificantBit(data, index)
            val newSet = data.filter {
                if(useMostCommon) {
                    it[index] == sigBit
                } else it[index] != sigBit
            }
            var ans = ""
            return if(newSet.size == 1) {
                newSet[0].map {
                    ans = "$ans$it"
                }
                ans
            } else {
                varIndex += 1
                reduce(newSet, varIndex, useMostCommon)
            }
        }
        val oxyAns = convert(reduce(day3input, 0, true))
        val co2Ans = convert(reduce(day3input, 0, false))
        println("Part 2: The Oxygen Rating is $oxyAns and the CO2 Rating is $co2Ans. The product of these numbers is ${oxyAns*co2Ans}")
    }
    day3()
    day3part2()
}