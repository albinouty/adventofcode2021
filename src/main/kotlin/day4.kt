import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    val reader = BufferedReader(FileReader("src/main/resources/inputday4.txt"))
    val passports = mutableListOf<String>()
    var line = ""
    var x = 0
    while(true){
        val tmp = reader.readLine()
        if (tmp==null){
            if(!line.isEmpty()) {
                passports.add(line)
            }
            break
        } else if(tmp.isEmpty()) {
            if(!line.isEmpty()) {
                passports.add(line)
                println(passports[x])
                x += 1
            }
            line = ""
        } else {
            if(line.isEmpty()){
                line = tmp
            } else {
                line += " $tmp"
            }
        }
    }

    fun resolveKey(key: String): String {
        return when (key) {
            "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" -> "valid"
            else -> "invalid"
        }
    }

    val validPassports = mutableListOf<String>()
    passports.map { k ->
        val keys = k.split(" ")
        if(keys.count() >= 7) {
            val validKeys = keys.map {
                if(resolveKey(it.substringBefore(":")) == "valid") {
                    1
                } else 0
            }
            if(validKeys.sum() >= 7) {
                validPassports.add(k)
            }
        }
    }
    println(validPassports.count())
}