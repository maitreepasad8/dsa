import kotlin.math.max

object Strings {

    /* Find the length of the longest substring without repeating characters*/
    fun lengthOfLongestSubstring(s: String): Int {
        // sliding window

        var length: Int = 0
        val map = hashMapOf<Char, Int>()

        var l = 0
        for (r in s.indices){
            val c = s[r]
            if (map.containsKey(c)){
                l = max(l, map[c]?.plus(1) ?: l)
            }
            map[c] = r
            length = max(length, r-l+1)
        }
        return length
    }

    /*
    * Given two strings s and t of lengths m and n respectively
    * Return the minimum window substring of s such that every character in t (including duplicates)
    * is included in the window.
    * If there is no such substring, return the empty string ""
    * */
    fun minWindow(s: String, t: String): String {

        /*
        * 1. Use two pointers to create a window of letters in s, which would have all the characters from t.
        * 2. Expand the right pointer until all the characters of t are covered.
        * 3. Once all the characters are covered, move the left pointer and
        * ensure that all the characters are still covered to minimize the subarray size.
        * 4. Continue expanding the right and left pointers until you reach the end of s.
        * */
        val m = s.length
        val n = t.length

        check(m>n){
            return ""
        }

        val freqs = IntArray(128){ 0 }
        for(ch in t){
            ++freqs[ch.code]
        }
        var shortest = ""

        var l = 0
        var count = n
        for (r in s.indices) {
            if (freqs[s[r].code]-- > 0) --count

            while (count == 0) {
                if (shortest.isEmpty() || (r-l+1 < shortest.length)) {
                    shortest=s.substring(l, r+1)
                }
                if(++freqs[s[l].code] > 0)  count ++
                l++
            }
        }

        return shortest
    }

    /*
    * Given a string s, return the number of palindromic substrings in it.
    * */
    fun countSubstrings(s: String): Int {
        val n = s.length
        var count = 0

        var x = 0
        var y = 0
        for (i in s.indices) {
            count++

            x = i-1
            y = i+1
            while (x>=0 && y<n && s[x]==s[y]) {
                count++
                x--
                y++
            }

            x = i
            y = i+1
            while (x>=0 && y<n && s[x]==s[y]) {
                count++
                x--
                y++
            }
        }
        return count
    }
}
