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
                println(passports[x])
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

    fun validateYear(b: Int, e: Int, input: Int): Boolean {
        return input in b..e
    }

    fun validateHcl(input: String): Boolean {
        val ptrn = Regex("^[#][a-f0-9]{6}$")
        return input.count() == 7 && ptrn.containsMatchIn(input)
    }

    fun validatePid(input: String): Boolean {
        val ptrn = Regex("^[0-9]{9}\$")
        return ptrn.containsMatchIn(input)
    }

    fun validateEyeColor(input: String): Boolean {
        return when (input) {
            "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true
            else -> false
        }
    }

    fun validateHeight(input: String): Boolean {
        val initPtrn = Regex("^[0-9]{2,3}[a-z]{2}\$") //initial filter
        val numPtrn = Regex("[0-9]{2,3}")
        val unitPtrn = Regex("[a-z]{2,3}")
        return if(initPtrn.containsMatchIn(input)) {
            val height = numPtrn.find(input)!!.value.toInt()
            val unit = unitPtrn.find(input)!!.value
            if(unit == "cm" && height in 150..193) {
                true
            } else {
                unit == "in" && height in 59..76
            }
        } else false
    }

    fun validateValue(k: String): Boolean {
        val key = k.substringBefore(":")
        val value = k.substringAfter(":")
        return when (key) {
            "byr" -> validateYear(1920, 2002, value.toInt())
            "iyr" -> validateYear(2010, 2020, value.toInt())
            "eyr" -> validateYear(2020, 2030, value.toInt())
            "hgt" -> validateHeight(value)
            "hcl" -> validateHcl(value)
            "ecl" -> validateEyeColor(value)
            "pid" -> validatePid(value)
            "cid" -> true
            else -> false
        }
    }

    val validPassports = mutableListOf<String>()
    passports.map { k ->
        val keys = k.split(" ")
        if(keys.count() >= 7) {
            val validKeys = keys.map {
                if(resolveKey(it.substringBefore(":")) == "valid" &&
                    validateValue(it)) {
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