object Searching {

    // Binary Search
    private fun binarySearch(nums: IntArray, l: Int, r: Int, key: Int): Int {
        if (r >= l) {
            var mid = l + (r-l)/2

            if(nums[mid]==key) return mid
            else if (nums[mid] < key) {
                return binarySearch(nums, mid + 1, r, key)
            } else {
                return binarySearch(nums, l, mid - 1, key)
            }
        }
        return -1
    }

    fun binarySearch(nums: IntArray, key: Int): Int {
        return binarySearch(nums, 0, nums.size - 1, key)
    }

    // Leetcode 33
    fun searchInRotatedArray(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1

        while (l <= r) {
            val mid = l + (r-l)/2

            if (nums[mid] == target) return mid

            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1
                } else {
                    l = mid + 1
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
        }
        return -1
    }
}