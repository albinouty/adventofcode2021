fun main(args: Array<String>) {
    val input = mutableListOf(11, 0, 1, 10, 5, 19)

    fun findOccurances(i: Int, s: Int): Int {
        val fList = input.subList(0, s).mapIndexed { index, x ->
            if (x == i) {
                index
            } else 0
        }
        return fList.max()!!
    }
    var iter = input.size
    while (true) {
        val sub = input.size - 1
        iter += if (!input.subList(0, sub)
                .contains(input.last())) {
            input.add(0)
            1
        } else {
            val last = findOccurances(input.last(), sub) + 1
            input.add( iter - last)
            1
        }
        if (iter == 2020) {
            break
        }
    }
    println("The 2020th number is ${input[2019]}")
}