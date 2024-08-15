import utils.printArray

class HashMaps {
    // Leetcode 383
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        if(ransomNote.length > magazine.length) return false
        val hash = IntArray(26){0}

        for (c in magazine){
            hash[c - 'a']++
        }
        for (c in ransomNote){
            if (hash[c-'a'] == 0) return false
            hash[c-'a']--
        }
        return true
    }
}

fun main(){
    val h = HashMaps()
    println( h.canConstruct("a", "b"))
}

