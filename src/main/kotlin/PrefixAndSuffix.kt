object PrefixAndSuffix {

    // Leetcode 238
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n){0}
        val prefixProduct = IntArray(n){0}
        val suffixProduct = IntArray(n){0}

        prefixProduct[0] = 1
        suffixProduct[n-1] = 1

        for (i in 1 until n) prefixProduct[i] = prefixProduct[i-1] * nums[i-1]
        for (i in n-2 downTo 0) suffixProduct[i] = suffixProduct[i+1] * nums[i+1]

        for (i in 0 until n) result[i] = prefixProduct[i] * suffixProduct[i]

        prefixProduct.forEach { print("$it ") }
        println()
        suffixProduct.forEach { print("$it ") }
        println()

        return result
    }
}