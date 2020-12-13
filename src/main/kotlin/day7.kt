import java.io.File
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
    val goldHolders = mutableListOf<String>()
        for(i in bags) {
            for(z in i.value) {
                if(z.second == "shiny gold") {
                    goldHolders.add(i.key)
                    x += 1
                }
            }
        }

    fun bfs(graph: Map<String, List<String>>, start: String): Int {
        val q = mutableListOf(start)
        val visisted = mutableSetOf<String>()
        while(q.isNotEmpty()) {
            val v = q.first()
            q.removeAt(0)
            if (!visisted.contains(v)) {
                visisted.add(v)
                val neighbors = graph.get(v)
                if (neighbors != null) {
                    for(n in neighbors) {
                        q.add(n)
                    }
                }
            }
        }
        return visisted.size - 1
    }

    val graph = mutableMapOf<String, MutableList<String>>()

    for(outer in bags) {
        for(inner in outer.value) {
            val dest = outer.key
            val src = inner.second
            if (graph.contains(src)) {
                val neighbors = graph.get(src)!!.toMutableList()
                neighbors.add(dest)
                graph.put(src, neighbors)
            } else {
                graph.put(src, mutableListOf(dest))
            }
        }
    }

    println(bfs(graph, "shiny gold"))

//    println(bags)
//    println(goldHolders)
}