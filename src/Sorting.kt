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
}

fun main () {
    val sorting = Sorting()
    val nums = intArrayOf(8,4,3,2,9)
    sorting.insertionSort(nums)
    printArray(nums)

}