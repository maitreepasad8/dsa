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

fun main () {
    val dp = DynamicProgramming()
    println(dp.fibonacci(10))
    println(dp.fibonacciMemoised(10, hashMapOf()))
    println(dp.fiboBottomUp(10))
}

