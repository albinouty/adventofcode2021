fun main(args: Array<String>) {
    val sum = 2020

    /**
    Part 1
     */
    for(i in input.indices) {
        for(x in i + 1 until input.size)
            if(input[i] + input[x] == sum){
                val a = input[i]
                val b = input[x]
                println("The two digits that add to $sum are $a and $b. Multiplied together they are " + a*b)
            }
    }

    /**
    Part 2
     */
    for(i in input.indices) {
        for(x in i + 1 until input.size)
            for(z in x + 1 until input.size)
                if(input[i] + input[x] + input[z] == sum){
                    val a = input[i]
                    val b = input[x]
                    val c = input[z]
                    println("The three digits that add to $sum are $a, $b, and $c. Multiplied together they are " + a * b * c)
                }
    }
}