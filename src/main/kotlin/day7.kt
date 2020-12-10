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
    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
//    val inputDaySeven = readFileAsLinesUsingUseLines("src/main/resources/inputday7.txt")
//    var totalCount = 0
//    val values = inputDaySeven.map {
//        val a = it.split(" contain ", ", ")
//        a
//    }.toMutableList()
//    val bags = values.map { it[0] }.toMutableList()
//    println(values)
//    val matchesBag = mutableListOf<String>()
//    for (x in values) {
//        for (i in 1 until x.size) {
//            if (x[i].contains("shiny gold bag")) {
//                matchesBag.add(x[0])
//                break
//            }
//        }
//    }
//    println(matchesBag)
//    val matchesFull = mutableListOf<List<String>>()
//    for (x in values) {
//        for (i in 1 until x.size) {
//            if (x[i].contains("shiny gold bag")) {
//                matchesFull.add(x)
//                break
//            }
//        }
//    }
//    val diff = (values - matchesFull).toMutableList()
//    fun resolve(values: MutableList<List<String>>, m: List<String>): List<List<String>> {
//        val match = mutableListOf<List<String>>()
//        for (x in values) {
//            for (i in 1 until x.size) {
//                for(y in m) {
//                    if (x[i].contains(y)) {
//                        match.add(x)
//                        break
//                    }
//                }
//            }
//        }
//        println(match)
//        val remaining = (values - match).toMutableList()
//    println(resolve(diff, matchesBag))

//    val iter = bags.iterator()
//    while(iter.hasNext()){
//        var x = 0
//        val it = iter.next()
//        val matchIter = matches.iterator()
//        while(matchIter.hasNext()) {
//            val z = matchIter.next()
//            if(it == z){
//                bags.removeAt(x)
//            }
//        }
//        x += 1
//    }
//    for(i in bags) {
//        for(y in 0 until matches.size)
//            if(matches[y] == i) {
//                bags.removeAt(y)
//            }

//    if(values[0][0] == matches[z] )
//    print(values)
//        totalCount += matchesBag.size
//        matches = values.filterIndexed { ix, i ->
//            i[0] !in matches
//        }
//        println(what)

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
    val what = bags.map {
        for(i in bags) {
            for(z in i.value) {
                if(z.second == "shiny gold") {
                    x += 1
                }
            }
        }
    }
    println(bags)
    println(x)
}