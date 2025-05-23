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

    // Leetcode 125
    fun isPalindrome(s: String): Boolean {
        val n = s.length
        var i = 0
        var j = n-1
        while (i<j){
            while(i<j && !s[i].isLetter()){
                i++
            }
            while(i<j && !s[j].isLetter()){
                j--
            }
            println("$i $j ${s[i]} ${s[j]}")
            if(s[i].lowercaseChar() != s[j].lowercaseChar()){
                return false
            }
            i++
            j--
        }
        return true
    }

    fun isSubsequence(s: String, t: String): Boolean {
        val m = s.length
        val n = t.length

        var i = 0
        var j = 0

        while(i<m && j<n){
            if(s[i] == t[j]){
                i++
            }
            j++

        }


        return i==m
    }
}

fun longestPalindrome(s: String): String {
    var lp = ""
    var max = 0
    val n = s.length

    for(i in s.indices){
        // check for odd length palindromes
        var l = i
        var r = i
        while(l>=0 && r<n && s[l] == s[r]){
            if(r-l+1 > max){
                lp = s.slice(l..r)
                max = r-l+1
            }
            l--
            r++
        }

        // check for even length palindromes
        l = i
        r = i+1
        while(l>=0 && r<n && s[l] == s[r]){
            if(r-l+1 > max){
                lp = s.slice(l..r)
                max = r-l+1
            }
            l--
            r++
        }
    }
    return lp
}
fun main() {
    val strings = Strings()
    println(strings.isPalindrome(
        "A man, a plan, a canal: Panama"
    ))
}


