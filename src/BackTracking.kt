

// Leetcode 46: Permutations
fun permute(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val temp = mutableSetOf<Int>()
    backTrack(result, temp, nums)

    return result
}


fun backTrack(result: MutableList<List<Int>>, temp: MutableSet<Int>, nums: IntArray) {
    if(temp.size == nums.size){
        result.add(listOf<Int>()+temp)
        return
    }

    for (n in nums){
        if (temp.contains(n)) continue

        temp.add(n)
        backTrack(result, temp, nums)
        temp.remove(n)
    }
}

fun main(){
    intArrayOf(1,2,3).max()
    println(permute(intArrayOf(1,2,3)))
}