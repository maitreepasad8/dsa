import utils.printArray
import utils.swap
import kotlin.math.min

class Sorting {
    fun selectionSort(nums: IntArray) {
        val n = nums.size
        for(i in nums.indices){
            var min = i
            for (j in i+1..<n){
                if(nums[j]<nums[min]){
                    min = j
                }
            }
            swap(nums, i, min)
        }
    }

    fun selectionSortRecursive(nums: IntArray, i: Int){
        if(i > 0){
            val j = findMaxIndex(nums, i)
            swap(nums, i, j)
            selectionSortRecursive(nums, i-1)
        }
    }

    fun findMaxIndex(nums: IntArray, i: Int): Int {
        // find index of maximum element in nums[:i+1] recursively
        if (i > 0){
            val j =  findMaxIndex(nums, i-1)
            if (nums[j] > nums[i]) {
                return j
            }
        }
        return i
    }

    fun insertionSort(nums: IntArray) {
        for (i in nums.indices) {
            for (j in i downTo 1){
                if (nums[j] < nums[j-1]) {
                    swap(nums, j, j-1)
                } else {
                    break
                }
            }
        }
    }


    fun mergeSort(a: IntArray, l: Int, r: Int) {
        if (l < r) {
            val m = l + (r-l)/2
            mergeSort(a, l, m)
            mergeSort(a, m+1, r)
            merge(a, l, m, r)
        }
    }

    private fun merge(a: IntArray, l: Int, m: Int, r: Int) {
        val b = IntArray(a.size)
        for (i in a.indices) b[i] = a[i]

        var i = l
        var j = m + 1
        var k = l

        while (i <= m && j <= r) {
           if (b[i] < b[j]) a[k++] = b[i++]
            else a[k++] = b[j++]
        }

        while (i <= m) {
            a[k++] = b[i++]
        }

        while (j <= r) {
            a[k++] = b[j++]
        }
    }

    fun quickSort(a: IntArray, l: Int, r: Int) {
        if (l<r) {
            val pivot = partition(a, l, r)
            quickSort(a, l, pivot - 1)
            quickSort(a, pivot + 1, r)
        }
    }

    private fun partition(a: IntArray, l: Int, r: Int): Int {
        val pivot = a[r]
        var i = l
        for (j in i..<r){
            if (a[j] < pivot){
                swap(a, i++, j)
            }
        }
        swap(a, i, r)

        return  i
    }
}

fun main () {
    val sorting = Sorting()
    val nums = intArrayOf(8,4,3,2,9)
    sorting.quickSort(nums, 0, nums.size - 1)
    printArray(nums)

}