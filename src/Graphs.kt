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


// Leetcode 399: Evaluate Division
class GraphNode(val value: String) {
    val neighbours: HashMap<String, Double> = hashMapOf()
}
fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
    val graph = hashMapOf<String, GraphNode>()


    // create graph
    for (i in equations.indices) {
        val equation = equations[i]
        val (a,b) = equation
        val node1 = graph.getOrPut(a){GraphNode(a)}
        node1.neighbours[b] = values[i]

        val node2 = graph.getOrPut(b){GraphNode(b)}
        node2.neighbours[a] = 1/values[i]
    }

    val answers = DoubleArray(queries.size){-1.0}
    queries.forEachIndexed{ i, q ->
        val (a,b) = q
        if(!graph.containsKey(a) || !graph.containsKey(b)) {
            return@forEachIndexed
        }
        if(a == b){
            answers[i] = 1.0
            return@forEachIndexed
        }
        answers[i] = bfs(graph[a]!!, graph[b]!!, graph)
    }
    return answers
}

fun bfs(node1: GraphNode, node2: GraphNode, graph: HashMap<String, GraphNode>): Double {
    val q = ArrayDeque<Pair<GraphNode, Double>>()
    q.addLast(node1 to 1.0)

    val visited = mutableSetOf(node1.value)

    while (q.isNotEmpty()){
        val (node, weight) = q.removeFirst()
        if (node.value == node2.value) {
            return weight
        } else {
            node.neighbours.forEach{(n,w) ->
                if (!visited.contains(n)){
                    q.addLast(graph[n]!! to w * weight)
                    visited.add(n)
                }
            }
        }
    }
    return -1.0
}


// Leetcode 210: Course schedule II
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): ArrayList<Int> {
    val order = ArrayList<Int>()
    val graph = Array<ArrayList<Int>>(numCourses){ arrayListOf() }

    // build adjacency list of prerequisites
    for ((a,b) in prerequisites) {
        graph[a].add(b)
    }

    val visited = HashSet<Int>()
    val cycle = HashSet<Int>()

    fun dfs(c: Int): Boolean {
        if (c in cycle) return false
        if (c in visited) return true

        cycle.add(c)
        for (p in graph[c]){
            if (!dfs(p)) return false
        }
        cycle.remove(c)
        visited.add(c)
        order.add(c)
        return true
    }

    for (c in 0..<numCourses){
        if (!dfs(c)) return arrayListOf()
    }

    return order
}