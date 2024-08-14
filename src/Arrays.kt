import utils.printArray
import utils.swap
import kotlin.math.max
import kotlin.math.min

class Arrays {
    // Leetcode 55
    fun canJump(nums: IntArray): Boolean {
        val n = nums.size
        if(n == 1){
            return true
        }
        if(nums[0] == 0){
            return false
        }
        if(nums[0] >= n-1){
            return true
        }

        var k = n-1
        for (i in n-2 downTo 0){
            if(nums[i] >=  k - i){
                k = i
                if(i == 0){
                    return true
                }
            }
        }
        return false
    }

    // Leetcode 122
    fun maxProfit2(prices: IntArray): Int {
        var profit = 0
        var l = 0
        var r = 1
        var n = prices.size
        while (r < n){
            if(prices[r]>= prices[l]){
                profit += prices[r]-prices[l]
            }
            l++
            r++

        }

        return profit
    }

    // Leetcode 121
    fun maxProfit(prices: IntArray): Int {
        val n = prices.size
        var l = 0
        var r = 1
        var profit = 0
        while(r < n){
            if(prices[r] < prices[l]){
                l = r
            }else {
                profit = max(profit, prices[r] - prices[l])
                r++
            }
        }
        return profit
    }

    // Leetcode 189
    fun rotate(nums: IntArray, x: Int): Unit {
        var k = x
        while(k > nums.size) {
            k -= nums.size
        }
        if(k == nums.size) {
            return
        }
        reverse(nums, 0, nums.size-1)
        reverse(nums, 0, k-1)
        reverse(nums, k, nums.size-1)
    }

    fun reverse(nums: IntArray, start: Int, end: Int) {
        var l = start
        var r = end
        while (l < r) {
            swap(nums, l, r)
            l++
            r--
        }
    }

    // Leetcode 169
    fun majorityElement(nums: IntArray): Int {
        // Moore Voting algo
        var count = 0
        var candidate = 0
        for (n in nums){
            if(count == 0){
                candidate = n
            }
            if(n == candidate){
                count++
            } else {
                count--
            }

        }
        return candidate
    }

    //Leetcode 80
    fun removeDuplicatesV2(nums: IntArray): Int {
        val n = nums.size
        if(n<=2){
            return n
        }

        var j = 2
        for (i in 2..<n) {
            if(nums[i] !== nums[j-2]){
                nums[j++]=nums[i]
            }
        }
        return j
    }

    // Leetcode 26
    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    fun removeDuplicates(nums: IntArray): Int {
        var k = 1
        for (i in 1..<(nums.size)) {
            if(nums[i-1]!=nums[i]){
                nums[k++] = nums[i]
            }
        }
        return k
    }

    // Leetcode 27
    // https://leetcode.com/problems/remove-element/
    fun removeElementSimplified(nums: IntArray, n: Int): Int {
        var k = 0
        for (i in nums.indices){
            if(nums[i] != n) {
                nums[k++] = nums[i]
            }
        }
        return k
    }

    fun removeElement(nums: IntArray, n: Int): Int {
        var k = 0
        var l = 0
        var r = nums.size - 1


        while (l in 0..r) {
            while (r >= l && nums[r] == n) {
                k++
                r--
            }
            while (l <= r && nums[l] == n ){
                swap(nums, l, r)
                k++
                r--
            }
            l++

        }
        return nums.size - k
    }


    // Leetcode 88
    // https://leetcode.com/problems/merge-two-sorted-lists/description/
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var i = m+n -1
        var l = m-1
        var r = n-1
        while (i >= 0 && l >= 0 && r>= 0) {
            if(nums1[l] > nums2[r]){
                nums1[i--] = nums1[l--]
            } else {
                nums1[i--] = nums2[r--]
            }
        }
        while (l >= 0) {
            nums1[i--] = nums1[l--]
        }
        while (r >= 0) {
            nums1[i--] = nums2[r--]
        }
    }

    fun mergeBrute(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        // brute force using extra space
        val arr = IntArray(m+n)
        var i = 0
        var left = 0
        var right = 0
        while (left < m && right < n) {
            if(nums1[left] <= nums2[right]) {
                arr[i++] = nums1[left++]
            } else {
                arr[i++] = nums2[right++]
            }
        }
        while (left < m) {
            arr[i++] = nums1[left++]
        }
        while (right < n) {
            arr[i++] = nums2[right++]
        }
        for (i in 0..<m+n) {
            nums1[i] = arr[i]
        }
    }


    // Leetcode 45
    fun jump(nums: IntArray): Int {
        val n = nums.size
        var jumps = 0
        var l = 0
        var r = 0
        while (r < n-1) {
            var farthest = 0
            for (i in l..r){
                farthest = max(farthest, i + nums[i])
            }
            l = r+1
            r = farthest
            jumps++
        }

        return  jumps
    }

    // Leetcode 274
    fun hIndex(citations: IntArray): Int {
        citations.sort()
        val n = citations.size
        var h = n
        for (i in citations.indices){
            if(citations[i] >= n-i) break
            h--
        }

        return h
    }

    // Leetcode 238
    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val prefix = IntArray(n){1}
        val suffix = IntArray(n){1}
        val out = IntArray(n)
        for(i in n-2 downTo 0){
           suffix[i] = suffix[i+1]*nums[i+1]
        }
        for(i in 0..<n){
            if(i==0){
                prefix[i] = 1
            } else {
                prefix[i] = prefix[i-1]*nums[i-1]
            }
            out[i] = prefix[i]*suffix[i]
        }

        return out
    }

    //Leetcode 134
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        val n = gas.size
        val diff = IntArray(n)
        var sum = 0
        var sumSoFar = 0
        var start = 0
        for (i in 0..<n){
            diff[i] = gas[i]-cost[i]
            sum+= diff[i]
            sumSoFar += diff[i]
            if(sumSoFar<0){
                sumSoFar = 0
                start = i+1
            }
        }
        if(sum<0){
            return -1
        }
        else {
            return start
        }
    }

}

fun main () {
    val arrays = Arrays()

    val nums = intArrayOf(1,2,3,4)
    val b = arrays.productExceptSelf(nums)
    printArray(b)

}

