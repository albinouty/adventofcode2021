import inputs.inputDayOne

fun main(args: Array<String>) {
    val sum = 2020

    /**
    Part 1
     */
    for(i in inputDayOne.indices) {
        for(x in i + 1 until inputDayOne.size)
            if(inputDayOne[i] + inputDayOne[x] == sum){
                val a = inputDayOne[i]
                val b = inputDayOne[x]
                println("The two digits that add to $sum are $a and $b. Multiplied together they are " + a*b)
            }
    }

    /**
    Part 2
     */
    for(i in inputDayOne.indices) {
        for(x in i + 1 until inputDayOne.size)
            for(z in x + 1 until inputDayOne.size)
                if(inputDayOne[i] + inputDayOne[x] + inputDayOne[z] == sum){
                    val a = inputDayOne[i]
                    val b = inputDayOne[x]
                    val c = inputDayOne[z]
                    println("The three digits that add to $sum are $a, $b, and $c. Multiplied together they are " + a * b * c)
                }
    }
}