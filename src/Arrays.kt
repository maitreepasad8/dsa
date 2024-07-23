import kotlin.math.max

class Arrays {
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

    fun swap(arr: IntArray, i : Int, j: Int) {
        val temp = arr[i]
        arr[i]=arr[j]
        arr[j]=temp
    }

    fun printArray(array: IntArray) {
        for (i in array){
            print("$i ")
        }
        println()
    }

}

fun main () {
    val arrays = Arrays()

    val nums = intArrayOf(7,2,3,4,5,6,7)
    println(arrays.maxProfit(nums))

}
