fun main(args: Array<String>) {
    val sum = 2020
    for(i in input.indices) {
        for(x in i + 1 until input.size)
            if(input[i] + input[x] == sum){
                val a = input[i]
                val b = input[x]
                println("The two digits that add to $sum are $a and $b. Multiplied together they are " + a*b)
            }s
    }
}