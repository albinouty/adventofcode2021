import java.io.File

//read file (DONE)
//make regex to identify the colors (delimiter "contains" and ",") (DONE)
//make a list of each line, index 0 would be the color of the bag, everything above index 0 is what it can contain
//make a list of these lists (DONE)
//loop through the list, go through every list index > 0 looking for "shiny gold bag". (DONE)
//get a list of all of the matches (DONE)
//Count how many, then remove that line from the outer list
//loop through the outer list again, go through every list in there looking for matches to the result from the first check.
// repeat until nothing left
fun main(args: Array<String>) {
    val bags = File("src/main/resources/inputday7.txt").readLines()
        .associate { line ->
            val (bag, contains) = line.split(" bags contain ")
            val contentList = contains.split(", ")
                .mapNotNull { s ->
                    val (n, adj, color) = s.split(' ')
                    n.toIntOrNull()?.let { it to "$adj $color" }
                }
            bag to contentList
        }
    var x = 0
    val what = mutableListOf<String>()
        for(i in bags) {
            for(z in i.value) {
                if(z.second == "shiny gold") {
                    what.add(i.key)
                    x += 1
                }
            }
        }
    println(bags)
    println(what)
}