class Strings {
    // Leetcode 13
    fun romanToInt(s: String): Int {
        val map = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )
        var sum = 0
        var i = 0
        while (i < s.length) {
            val current = map[s[i]]!!
            val next = if (i + 1 < s.length) map[s[i + 1]]!! else 0
            if (current < next) {
                sum += next - current
                i += 2
            } else {
                sum += current
                i++
            }
        }
        return sum
    }

    // Leetcode 58
    fun lengthOfLastWord(s: String): Int {
        var n = s.length
        var i = n-1
        var count = 0
        while(i>=0){
            if (s[i] == ' ' && count > 0){
                return count
            }
            if (s[i] != ' '){
                count++
            }
            i--
        }
        return count
    }

    //Leetcode 14
    fun longestCommonPrefix(strs: Array<String>): String {
        val list = mutableListOf<Char>()
        var i = 0
        while (true){
            if(strs.any { it.length < i+1 }) break
            if(strs.any { it[i] != strs[0][i] }) break
            list.add(strs[0][i++])

        }
        return list.joinToString("")
    }

    // Leetcode 28
    fun strStr(haystack: String, needle: String): Int {
        for (i in haystack.indices) {
            for (j in needle.indices) {
                if (i + j >= haystack.length) return -1
                if (haystack[i + j] != needle[j]) break
                if (j == needle.length - 1) return i
            }
        }
        return -1
    }

}

fun main() {
    val strings = Strings()
    println(strings.longestCommonPrefix(arrayOf("flower","flow","flight")))
}

