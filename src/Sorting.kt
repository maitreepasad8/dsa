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
}

fun main () {
    val sorting = Sorting()
    val nums = intArrayOf(8,4,3,2,9)
    sorting.selectionSort(nums)
    printArray(nums)

}