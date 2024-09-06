import kotlin.math.min

// Leetcode 62: Unique Paths
fun uniquePaths(m: Int, n: Int): Int {
    val memo = Array(m) { Array(n) { -1 } }
    return dfsUniquePaths(0,0,m,n, memo)
}

fun dfsUniquePaths(i: Int, j: Int, m: Int, n: Int, memo: Array<Array<Int>>): Int {
    if(memo[i][j] != -1) {
        return memo[i][j]
    }
    val paths: Int
    if (i == m-1 && j == n-1) {
        paths =  1
    }
    else if ( i > m-1 || j > n-1) {
        paths = 0
    }
    else if (i == m-1){
        paths = dfsUniquePaths(i, j+1, m, n, memo)
    }
    else if( j == n-1 ){
        paths =  dfsUniquePaths(i+1, j, m, n, memo)
    }
    else paths =  dfsUniquePaths(i+1, j, m, n, memo) + dfsUniquePaths(i, j+1, m, n, memo)
    memo[i][j] = paths
    return paths
}

// Leetcode 909: Snakes and Ladders
fun snakesAndLadders(board: Array<IntArray>): Int {
    val n = board.size
    val visited = IntArray((n*n)+1){0}
    val q = ArrayDeque<Pair<Int,Int>>()

    q.addLast(Pair(1,0))

    while(q.isNotEmpty()){
        val(square, moves) = q.removeFirst()
        for (i in 1..6){
            var next = square + i
            val (r,c) = squareToPosition(next, n)
            if(board[r][c] != -1){
                next = board[r][c]
            }
            if (next == n*n) {
                return moves + 1
            }
            if (visited[next] == 0){
                visited[next] = 1
                q.addLast(Pair(next, moves+1))
            }
        }

    }
    return -1
}

fun squareToPosition(square: Int, n: Int) : Pair<Int,Int> {
    val r = n - 1 - (square - 1)/n
    var c = (square - 1)%n
    if (r%2 == n%2){
        c = n - 1 - c
    }
    return Pair(r,c)
}

// Leetcode: 64
fun minPathSum(grid: Array<IntArray>): Int {
    val m = grid.size
    val n = grid[0].size
    val dp = Array(m){IntArray(n){-1}}
    return dfs(grid, 0, 0, m, n, dp)
}

fun dfs(grid: Array<IntArray>, i: Int, j: Int, m: Int, n: Int,dp: Array<IntArray>): Int {

    if(i==m-1 && j==n-1) return grid[i][j]
    if(i > m-1 || j > n-1) return Int.MAX_VALUE
    if(dp[i][j] == -1){
        dp[i][j] = grid[i][j] + min(
            dfs(grid, i+1, j, m, n, dp),
            dfs(grid, i, j+1, m, n, dp)
        )
    }
    return dp[i][j]
}

