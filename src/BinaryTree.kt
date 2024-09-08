import kotlin.math.max

class Node(var value: Int) {
    var left: GraphNode? = null
    var right: GraphNode? = null
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun inOrderTraversal(root: GraphNode?) {
    if(root == null) return
    inOrderTraversal(root.left)
    print("${root.value} ")
    inOrderTraversal(root.right)
}

fun preOrderTraversal(root: GraphNode?){
    if (root == null) return
    print("${root.value} ")
    preOrderTraversal(root.left)
    preOrderTraversal(root.right)
}

fun postOrderTraversal(root: GraphNode?){
    if (root == null) return
    postOrderTraversal(root.left)
    postOrderTraversal(root.right)
    print("${root.value} ")
}

fun levelOrderTraversal(root: GraphNode?){
    if (root == null) return
    val q = ArrayDeque<GraphNode>()
    q.addLast(root)
    while (q.isNotEmpty()){
        val curr = q.removeFirst()
        print("${curr.value} ")
        if (curr.left != null) q.addLast(curr.left!!)
        if (curr.right != null) q.addLast(curr.right!!)
    }
}

fun insertInTree(root: GraphNode, key: Int){
    val q = ArrayDeque<GraphNode>()
    q.addLast(root)
    while (q.isNotEmpty()){
        val curr = q.removeFirst()
        if (curr.left == null){
            curr.left = GraphNode(key)
            return
        } else if (curr.right == null){
            curr.right = GraphNode(key)
            return
        } else {
            q.addLast(curr.left!!)
            q.addLast(curr.right!!)
        }
    }
}

// LC 104
fun maxDepth(root: TreeNode?): Int {
    if(root == null) return 0
    return max(maxDepth(root.left), maxDepth(root.right)) + 1
}

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null &&  q == null) return true
    return if (p != null && q != null){
        if(p.`val` == q.`val`){
            isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        } else {
            false
        }
    } else false
}

fun isSameTreeLevelOrder(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p == null || q == null) return false

    val q1 = ArrayDeque<TreeNode>()
    val q2 = ArrayDeque<TreeNode>()

    q1.addLast(p)
    q2.addLast(q)

    while (q1.isNotEmpty() && q2.isNotEmpty()){
        val n1 = q1.removeFirst()
        val n2 = q2.removeFirst()

        if (n1.`val` == n2.`val`){
            if(n1.left != null && n2.left != null) {
                q1.addLast(n1.left!!)
                q2.addLast(n2.left!!)
            } else if (n1.left != null || n2.left != null){
                return false
            }

            if(n1.right != null && n2.right != null) {
                q1.addLast(n1.right!!)
                q2.addLast(n2.right!!)
            } else if (n1.right != null || n2.right != null){
                return false
            }
        } else {
            return false
        }
    }
    return true
}


fun main() {

    val root = GraphNode(1)
    root.left = GraphNode(2)
    root.right = GraphNode(3)
    root.left?.left = GraphNode(4)
    root.left?.right = GraphNode(5)
    root.right?.left = GraphNode(6)
    root.right?.right = GraphNode(7)
}