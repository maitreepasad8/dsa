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

    // Leetcode 205
    fun isIsomorphic(s: String, t: String): Boolean {
        if(s.length != t.length) return false
        val n = s.length
        val sMap = IntArray(200){0}
        val tMap = IntArray(200){0}
        for (i in 0..<n){
            if(sMap[s[i].code] != tMap[t[i].code]) return false
            sMap[s[i].code] = i+1
            tMap[t[i].code] = i+1
        }
        return true
    }

    // Leetcode 290
    fun wordPattern(pattern: String, str: String): Boolean {
        val sArray = str.split(" ")
        if(pattern.length != sArray.size) return false
        val n = pattern.length
        val p = hashMapOf<Char, Int>()
        val s = hashMapOf<String, Int>()
        for (i in 0..<n){
            if(p[pattern[i]] != s[sArray[i]]) return false
            p[pattern[i]] = i+1
            s[sArray[i]] = i+1
        }
        return true
    }

    // Leetcode 1
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val hm = hashMapOf<Int, Int>()
        for (i in nums.indices){
            val n = nums[i]
            if(hm.containsKey(target - n)){
                return intArrayOf(i, hm[target-n]!!)
            }
            hm[n] = i
        }
        return intArrayOf(0,1)
    }

    // Leetcode 49
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val hm = hashMapOf<Int, List<String>>()
        for (s in strs){
            val key = getKey(s)
            if(hm.containsKey(key)){
                hm[key] = hm[key]!! + s
            } else {
                hm[key] = listOf(s)
            }
        }

        return hm.values.toList()
    }

    private fun getKey(s: String): Int {
        val count = Array<Int>(26){0}
        for (c in s){
            count[c - 'a']++
        }
        return count.contentDeepHashCode()
    }
}

fun main(){
    val h = HashMaps()
    println( h.canConstruct("a", "b"))
}

