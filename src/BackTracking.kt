

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

// LC 77: combinations
fun combine(n: Int, k: Int): List<List<Int>> {
    val result = arrayListOf<List<Int>>()

    fun backtrack(start: Int, combination: MutableList<Int>){
        if(combination.size == k){
            result.add(listOf<Int>()+combination)
            return
        }

        for (num in start..n){
            combination.add(num)
            backtrack(num+1, combination)
            combination.remove(num)
        }
    }

    backtrack(1, mutableListOf<Int>())
    return result
}

// LC 78: subsets
fun subsets(nums: IntArray): List<List<Int>> {
    val result = arrayListOf<List<Int>>()
    val n = nums.size


    fun combine(start: Int, k: Int, combinations: MutableList<Int>) {
        if(combinations.size == k){
            result.add(listOf<Int>()+combinations)
            return
        }
        for (i in start..<n){
            val num = nums[i]
            combinations.add(nums[i])
            combine(i+1, k, combinations)
            combinations.remove(nums[i])
        }
    }


    for (i in 0..n){
        combine(0, i, mutableListOf<Int>())
    }
    return result
}

fun main(){
    intArrayOf(1,2,3).max()
    println(combine(4,2))
}