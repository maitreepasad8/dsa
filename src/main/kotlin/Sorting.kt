import kotlin.math.max

object Sorting {

    fun mergeSort(nums: IntArray): IntArray {
        sort(nums, 0, nums.size - 1)
        return nums
    }

    private fun sort(nums: IntArray, l: Int, r: Int) {
        if (l < r) {
            val m = l + (r-l)/2
            sort(nums, l, m)
            sort(nums, m+1, r)
            merge(nums, l, m, r)
        }
    }

    private fun merge(nums: IntArray, l: Int, m: Int, r: Int) {
        val l1 = m - l + 1
        val l2 = r - m

        val L = IntArray(l1)
        val R = IntArray(l2)

        for (i in 0 until l1) L[i] = nums[l+i]
        for (i in 0 until l2) R[i] = nums[m+1+i]

        var i = 0
        var j = 0
        var k = l
        while (i < l1 && j < l2) {
            if (L[i] < R[j]) nums[k++] = L[i++]
            else nums[k++] = R[j++]
        }

        while (i < l1) nums[k++] = L[i++]
        while (j < l2) nums[k++] = R[j++]
    }

    // Leetcode 56
    fun merge(): Array<IntArray> {
        val intervals = arrayOf(intArrayOf(1,3), intArrayOf(2,6), intArrayOf(8,10), intArrayOf(15,18))
        if (intervals.size <= 1) return intervals
        
        intervals.sortBy { it[0] }

        val answer = arrayListOf<IntArray>()
        for (interval in intervals) {
            if (answer.isEmpty() || answer.last()[1] < interval[0]) {
                answer.add(interval)
            } else {
                answer.last()[1]= max(answer.last()[1], interval[1])
            }
        }


        return answer.toTypedArray()
    }


    // Quick sort
    fun quickSortWrapper(nums: IntArray): IntArray {
        quickSort(nums, 0, nums.size - 1)
        return nums
    }

    fun quickSort(nums: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pivot = partition(nums, low, high)
            quickSort(nums, low, pivot - 1)
            quickSort(nums, pivot + 1, high)
        }
    }

    fun partition(a: IntArray, low: Int, high: Int): Int {
        val pivot = a[high]

        var i = low - 1

        for (j in low until high) {
            if (a[j] < pivot) {
                i++
                swap(a, i, j)
            }
        }

        swap(a, i+1, high)
        return (i+1)
    }


    fun swap(a: IntArray, i: Int, j: Int) {
        val temp = a[i]
        a[i]=a[j]
        a[j]=temp
    }
}