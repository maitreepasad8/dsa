object Greedy {
    // Leetcode 435
    fun eraseOverlapIntervals(): Int {
        val intervals = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(4,5), intArrayOf(1,5))

        if (intervals.size <= 1) return 0

        intervals.sortBy { it[1] }

        var count = 0
        var end = intervals[0][1]

        for (i in 1 until intervals.size) {
            if(intervals[i][0] >= end) {
                end = intervals[i][1]
            } else {
                count++
            }
        }
        return count
    }
}