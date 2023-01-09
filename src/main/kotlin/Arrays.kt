import java.util.*
import kotlin.Array
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

object Arrays {

    fun containsDuplicate(): Boolean {
        val nums = intArrayOf(1,2,3,4)
        nums.sort()
        for(i in 0 until nums.size - 1){
            if(nums[i]==nums[i+1]) return true
        }
        return false
    }

    fun maxSubArray(): Int {
        val nums  = intArrayOf(-3,  2, )
        /* BF -> sliding window */
//        if (nums.size==1) return nums[0]
//
//        var sum = 0
//        var max = nums[0]
//        for (i in nums.size - 1 downTo 0){ // window size
//            for (j in 0 until nums.size-i){
//                sum=0
//                for (k in j .. j+i){
//                    sum += nums[k]
//                    if (max < sum) max=sum
//                }
//            }
//        }
//        return max

        /*
        Kadane’s algorithm
        Does not work if all are negative
        */
//        if (nums.size > 1){
//            var max_so_far = Int.MIN_VALUE
//            var max_ending_here = 0
//
//            for (i in nums.indices){
//                max_ending_here +=  nums[i]
//                if (max_ending_here < 0) max_ending_here=0
//                if (max_ending_here > max_so_far) max_so_far=max_ending_here
//
//                /*
//                * Seems to work for all cases if you first update max_so_far and then max_ending_here
//                * TODO: Find case where this can fail
//                * */
//            }
//            return max_so_far
//        }
//        return nums[0]

        /*
        * DP
        * */
        if (nums.size > 1){
            var max_so_far = nums[0]
            var max_ending_here = nums[0]

            for (i in 1 until nums.size){
                max_ending_here = max(nums[i], max_ending_here+nums[i])
                max_so_far = max(max_so_far, max_ending_here)
            }
            return max_so_far
        }
        return nums[0]
    }

    /**
     * Given an array of integers nums and an integer target
     * Return indices of the two numbers such that they add up to target.
     **/
    fun twoSum(): IntArray {
        val nums = intArrayOf(3,2,4)
        val target = 6

//        Brute force
//        for (i in nums.indices){
//            for (j in i+1 until nums.size){
//                if(nums[i] + nums[j] == target) return intArrayOf(i,j)
//            }
//        }

        // Using hashmap (value:index)
        val hm:HashMap<Int, Int> = hashMapOf()
        for ((i,n) in nums.withIndex()){
            val diff = target - n
            if(hm.containsKey(diff)) return intArrayOf(hm[diff]!!, i)
            else hm[n] = i
        }
        return intArrayOf(0,0)
    }

    /**
     * Merge two arrays without using additional space
     **/
    fun merge(): Unit {
        val nums1 = intArrayOf(3, 4, 5, 0, 0, 0)
        val nums2 = intArrayOf(1, 2, 6)
        val m = 3
        val n = 3

//        var merged = emptyArray<Int>()
//
//        var i = 0
//        var j = 0
//
//        while (i < m && j < n){
//            if(nums1[i] < nums2[j]){
//                merged += nums1[i]
//                i++
//            }
//            else{
//                merged += nums2[j]
//                j++
//            }
//        }
//        while (i < m){
//            merged += nums1[i]
//            i++
//        }
//        while (j < n){
//            merged += nums2[j]
//            j++
//        }
//        merged.toIntArray().copyInto(nums1)


        /*
        * Optimal solution
        * Two pointers pointing to end of array 1 until we have non zero values and another to end of array 2
        * One pointer pointing to end of array 1
        * Iterate from the end of array 1 backward
        * compare values between array 1 and array 2 and position them acc
        * */
        var p1 = m-1
        var p2 = n-1
        var size = m+n-1

        for (i in size downTo 0){
            if (p2 < 0) break
            if (p1 >= 0 && nums1[p1] > nums2[p2]){
                nums1[i] = nums1[p1]
                p1--
            } else {
                nums1[i] = nums2[p2]
                p2--
            }
        }

        nums1.forEach { print("$it ") }
    }

    fun getIntersectionOfArrays(): IntArray {
        val nums1 = intArrayOf(4, 9, 5)
        val nums2 = intArrayOf(9, 4, 9, 8, 4)

        var intersect = arrayOf<Int>()

        val hm: HashMap<Int, Int> = hashMapOf()
        for (n in nums1){
            hm[n] = hm.getOrDefault(n, 0) + 1
        }
        for (n in nums2){
            hm[n]?.let{
                if (it > 0) {
                    hm[n] = it - 1
                    intersect += n
                }
            }
        }

        return intersect.toIntArray()
    }

    /*
    * max positive difference from left to right (maximise profit for buy & sell of stocks)
    * */
    fun maxDifference(): Int {
        val prices = intArrayOf(7,2,5,3,7,4,1,10)

        if(prices.size == 1) return 0

        var profit = 0

        // using two pointers
        // i-> min, j -> max
        var i = 0
        var j = 1
        while (j < prices.size){
            val p = prices[j] - prices[i]
            if(p < 0) {
                i=j
            } else if (p > profit){
                profit = p
            }
            j++

        }
        return profit
    }


    /*
    * reshape given matrix (m*n) to (r*c)
    * */
    fun reshapeMatrix(): Array<IntArray> {
        val mat: Array<IntArray> = arrayOf(intArrayOf(1,2), intArrayOf(3,4))
        val r = 2
        val c = 4

        val m = mat.size
        val n = mat[0].size

        if(m*n != r*c) return mat

//        var newMat: Array<IntArray> = arrayOf()
//        var curRow: Array<Int> = arrayOf()
//        var count = 0
//        for (row in mat){
//            for ( a in row){
//                count++
//                curRow += a
//                if(count >= c) {
//                    newMat += curRow.toIntArray()
//                    count = 0
//                    curRow = arrayOf()
//                }
//            }
//        }
//
//        return newMat

        val reshaped = Array(r) {IntArray(c)}
        var k = 0 // row
        var l = 0 // column

        for (i in mat.indices){
            for (j in mat[i].indices){
                reshaped[k][l]=mat[i][j]
                if (++l == c){
                    l = 0
                    k++
                }
            }
        }

        return reshaped
    }

    /*
    * return numRows of pascal triangle
    * */
    fun pascalTriangle(): List<List<Int>> {
        val numRows = 5
        val p = mutableListOf<List<Int>>()

        for (i in 0 until numRows){
            val row = Array(i+1){1}
            if (i != 0 && i != 1) {
                val mid: Int = i / 2
                for (j in 1..mid){
                    val n = p[i-1][j-1] +  p[i-1][j]
                    row[j] = n
                    row[row.size - j - 1] = n
                }
            }
            p.add(row.toList())


        }
        return p
    }


    /*
    * Given an array of positive integers nums and a positive integer target,
    * return the minimal length of a subarray
    * whose sum is greater than or equal to target.
    * If there is no such subarray, return 0 instead.
    * */
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var minLen = Int.MAX_VALUE
        var l = 0
        var sum = 0
        for (r in nums.indices) {
            if (sum < target) {
                sum += nums[r]
            }

            while (sum >= target) {
                minLen = minOf(minLen, r-l+1)

                sum -= nums[l]
                l++
            }

        }
        return if (minLen == Int.MAX_VALUE) 0 else minLen
    }


    /*
    * Given an array nums with n objects colored red, white, or blue,
    * sort them in-place so that objects of the same color are adjacent,
    * with the colors in the order red, white, and blue.
    * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
    * You must solve this problem without using the library's sort function.
    * */
    fun sortColors(nums: IntArray): Unit {
        var low = 0
        var high = nums.size - 1
        var i = 0
        while (i <= high) {
            if (nums[i] == 0) {
                nums[i++] = nums[low]
                nums[low++] = 0
            }
            else if (nums[i] == 2) {
                nums[i] = nums[high]
                nums[high--] = 2
            } else i++
        }

        nums.forEach { it -> print("$it ") }
    }


    // Leetcode 739
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        //we need to use a stack of indexes and, parsing the vector right-to-left,
        // we will keep popping every value with a matching vector element lower than the current one, update our result vector accordingly
        // and then push down the value we are parsing

        // [89,62,70,58,47,47,46,76,100,70]
        val n = temperatures.size
        val answer = IntArray(n){0}
        val stack = ArrayDeque<Int>()

        for (i in n-1 downTo 0) {
            val t = temperatures[i]
            while (stack.isNotEmpty() && temperatures[stack.last()] <= t) stack.removeLast()
            if (stack.isNotEmpty()) answer[i]=stack.last()-i
            stack.addLast(i)
        }

        return answer
    }

    // Leetcode 1944
    fun canSeePersonsCount(heights: IntArray): IntArray {
        val n = heights.size
        val answer = IntArray(n){0}
        val stack = Stack<Int>()

        for (i in n-1 downTo 0) {
            while (!stack.empty()  && heights[i] > stack.peek()) {
                stack.pop()
                answer[i]++
            }
            if (!stack.empty()) answer[i]++
            stack.push(heights[i])
        }
        return answer
    }

    // Leetcode 41
    fun firstMissingPositive(nums: IntArray): Int {
        var n = nums.size
        for (i in nums.indices) {
            while ( (1..nums.size).contains(nums[i]) && nums[i]!=nums[nums[i]-1]) {
                Sorting.swap(nums, i, nums[i]-1)
            }
        }

        for (i in nums.indices) {
            if (nums[i] != i+1)
                return i+1
        }

        return n+1
    }

    // Leetcode 152
    fun maxProduct(nums: IntArray): Int {
        var product = Int.MIN_VALUE
        var minProduct = 1
        var maxProduct = 1


        for (n in nums) {
            if (n < 0) minProduct = maxProduct.also { maxProduct = minProduct }

            maxProduct = max(maxProduct * n, n)
            minProduct = min(minProduct * n, n)

            product=max(product, maxProduct)
        }

        return product
    }

    // Leetcode 15
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        nums.sort()
        if (nums[0] > 0)
            return result

        for (i in nums.indices){
            if (nums[i] > 0)
                break

            if (i>0 && nums[i]==nums[i-1])
                continue

            var low = i+1
            var high = nums.size - 1
            var sum = 0

            while (low < high){
                sum = nums[i] + nums[low] + nums[high]

                if (sum < 0) low++
                else if(sum > 0) high--
                else{
                    result.add(listOf( nums[i], nums[low], nums[high]))

                    var lastLow = nums[low]
                    var lastHigh = nums[high]

                    while (low < high && nums[low]==lastLow) low++
                    while (low < high && nums[high]==lastHigh) high--

                }
            }
        }
        return result
    }

    fun threeSumHashMap(nums: IntArray): List<List<Int>> {
        val result = arrayListOf<List<Int>>()

        nums.sort()
        if (nums[0] > 0)
            return result

        val hashMap = hashMapOf<Int, Int>()
        for (i in nums.indices){
            hashMap[nums[i]] = i
        }

       var i = 0
        while (i < nums.size - 2){
            if (nums[i] > 0)
                break

            var j = i+1
            while (j < nums.size - 1){
                val required = -1*(nums[i] + nums[j])
                val f = hashMap[required]
                if (f != null && f > j) {
                    result.add(listOf(nums[i], nums[j], nums[f]))
                }
                j = hashMap.getOrDefault(nums[j], j)
                j++
            }
            i = hashMap.getOrDefault(nums[i], i)
            i++
        }
        return result
    }

}