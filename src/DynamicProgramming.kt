import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

class DynamicProgramming {
    // naive recursive approach
    fun fibonacci(n: Int): Int {
        if (n <= 2) {
            return 1
        }
        return fibonacci(n-1) + fibonacci(n-2)
    }

    fun fibonacciMemoised(n: Int, memo: HashMap<Int, Int>): Int {
        if (memo.containsKey(n)) {
            return memo[n]!!
        }
        val f: Int
        if (n <= 2) {
            f = 1
        } else {
            f = fibonacciMemoised(n-1, memo) + fibonacciMemoised(n-2, memo)
        }
        memo[n] = f
        return f
    }

    fun fiboBottomUp(n: Int): Int {
        val memo = IntArray(n+1)
        for (i in 1..n) {
            val f: Int
            if(i <= 2) {
                f = 1
            } else {
                f = memo[i-1] + memo[i-2]
            }
            memo[i] = f
        }
        return memo[n]
    }

}

// Leetcode 322: Coin Change
fun coinChange(coins: IntArray, amount: Int): Int {
    if (amount == 0) return 0
    if(coins.size == 1 && coins[0] > amount) return -1

    val memo = IntArray(amount+1){-1}

    for (i in coins.indices){
        if(coins[i] > amount) continue
        memo[coins[i]] = 1
    }

    for (i in 1..amount){
        if (memo[i] != -1) continue

        var numCoins = Int.MAX_VALUE
        for (j in coins.indices) {
            val remaining = i-coins[j]
            if(remaining < 0) continue
            if(memo[remaining] != -1){
                numCoins = min(numCoins, memo[remaining]+memo[coins[j]])
            }
        }
        if (numCoins != Int.MAX_VALUE){
            memo[i]=numCoins
        }

    }

    return memo[amount]
}

// Leetcode 118: Pascal's Triangle
fun generate(numRows: Int): List<IntArray> {
    if(numRows == 1) return listOf(intArrayOf(1))

    val prevRows = generate(numRows-1)
    val row = IntArray(numRows){1}

    for(i in 1..<numRows-1){
        row[i] = prevRows[numRows-2][i-1] + prevRows[numRows-2][i]
    }
    return prevRows + listOf(row)
}

//Leetcode 119: Pascal's Triangle II
fun getRow(rowIndex: Int): List<Int> {
    if(rowIndex == 0) return listOf(1)

    val prev = getRow(rowIndex-1)
    val result = IntArray(rowIndex+1){1}
    for (i in 1..<prev.size){
        result[i] = prev[i-1]+prev[i]
    }
    return result.toList()
}


// Leetcode 300: Longest Increasing Subsequence
fun lengthOfLIS(nums: IntArray): Int {
    val n = nums.size
    val dp = IntArray(n){1}

    for (i in n-1 downTo 0){
        for (j in i..<n){
            if(nums[j]>nums[i]){
                dp[i] = max(dp[i], 1+dp[j])
            }
        }
    }
    return dp.max()
}

// leetcode 139: Word Break
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val n = s.length
    val dp = BooleanArray(n+1)
    dp[n] = true

    for (i in n-1 downTo 0){
        for (w in wordDict){
            if(i+w.length <= n && s.slice(i..<(i+w.length))==w){
                dp[i]=dp[i+w.length] || dp[i]
                if(dp[i] == true) break
            }
        }
    }

    return dp[0]
}


// Leetcode 198: House Robber
fun rob(nums: IntArray): Int {
    val n = nums.size
    if(n === 1) {
        return nums[0]
    }
    val memo = IntArray(n){0}
    for (i in 0..<n){
        val m: Int
        if(i == 0) {
            m = nums[0]
        }
        else if (i == 1) {
            m = max(nums[0], nums[1])
        }
        else {
            m = max(nums[i]+memo[i-2], memo[i-1])
        }
        memo[i] = m
    }
    return memo[n-1]

}

// leetcode 213: House Robber II
fun rob2(nums: IntArray): Int {
    val n = nums.size
    if(n == 1) return nums[0]
    if(n == 2) return max(nums[0], nums[1])

    // visiting first house case
    val dp1 = IntArray(n)
    dp1[0] = nums[0]
    dp1[1] = max(nums[0], nums[1])

    // not visting first house
    val dp2 = IntArray(n)
    dp2[0] = nums[1]
    dp2[1] = max(nums[1], nums[2])

    var firstVisited = false
    for(i in 2..<n-1){
        dp1[i] = max(dp1[i-1], dp1[i-2]+nums[i])
        dp2[i]=max(dp2[i-1], dp2[i-2]+nums[i+1]);

    }

    return max(dp1[n-2], dp2[n-2])
}
// Leetcode 264: Ugly Number II
fun nthUglyNumber(n: Int): Int {
    if(n<= 5) return n
    var p2 = 1
    var p3 = 1
    var p5 = 1
    val dp = IntArray(n+1)
    dp[1]=1
    for(i in 2..n){
        val product2 = dp[p2]*2
        val product3 = dp[p3]*3
        val product5 = dp[p5]*5

        dp[i]=minOf(product2, product3, product5)
        if(dp[i] == product2) p2++;
        if(dp[i] == product3) p3++;
        if(dp[i] ==  product5) p5++;
    }
    return dp[n]
}

<<<<<<< Updated upstream
// Leetcode 975: Odd Even Jump
fun oddEvenJumps(arr: IntArray): Int {
    var result = 1
    val n = arr.size
    // higher[i] is true if you can jump to end in odd numbered jump
    val higher = BooleanArray(n)

    // lower[i] is true if you can jump to end in even numbered jump
    val lower = BooleanArray(n)
    higher[n-1] = true
    lower[n-1] = true

    // map to store number to index map, and find next higher and next lower easily
    val map = TreeMap<Int, Int>()
    map[arr[n-1]] = n-1
    for (i in n-2 downTo 0){
        val h = map.ceilingEntry(arr[i])
        val l = map.floorEntry(arr[i])

        if(h != null){
            higher[i] = lower[h.value]
        }
        if(l != null){
            lower[i] = higher[l.value]
        }
        if(higher[i]){
            result++
        }
        map[arr[i]] = i
    }

    return result
}
=======
// Leetcode 72: Edit Distance
fun minDistance(word1: String, word2: String): Int {
    val m = word1.length
    val n = word2.length

    val dp = Array(m+1){IntArray(n+1)}

    for(i in m downTo 0){
        for (j in n downTo 0){
            if (i == m) {
                dp[i][j] = n-j
            } else if (j == n) {
                dp[i][j] = m - i
            } else {
                if(word1[i]==word2[j]) {
                    dp[i][j] = dp[i+1][j+1]
                } else {
                    dp[i][j] = 1 + minOf(
                        dp[i][j+1],
                        dp[i+1][j],
                        dp[i+1][j+1]
                    )
                }
            }
        }
    }
    return dp[0][0]
}

// Leetcode 1143: Longest Common Subsequence
fun longestCommonSubsequence(text1: String, text2: String): Int {
    val m = text1.length
    val n = text2.length

    val dp = Array(m+1){IntArray(n+1)}

    for(i in m downTo 0){
        for (j in n downTo 0){
            if (i == m) {
                dp[i][j] = 0
            } else if (j == n) {
                dp[i][j] = 0
            } else {
                if(text1[i]==text2[j]) {
                    dp[i][j] = maxOf(
                        1+dp[i+1][j+1],
                        dp[i+1][j],
                        dp[i][j+1]
                    )
                } else {
                    dp[i][j] = maxOf(
                        dp[i+1][j],
                        dp[i][j+1]
                    )
                }
            }
        }
    }
    return dp[0][0]
}
>>>>>>> Stashed changes
