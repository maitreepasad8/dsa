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
}

fun main() {
    println("Hello World!")
}