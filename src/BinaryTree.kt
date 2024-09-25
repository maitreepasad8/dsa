import kotlin.math.max

class Node(var value: Int) {
    var left: Node? = null
    var right: Node? = null
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun inOrderTraversal(root: Node?) {
    if(root == null) return
    inOrderTraversal(root.left)
    print("${root.value} ")
    inOrderTraversal(root.right)
}

fun preOrderTraversal(root: Node?){
    if (root == null) return
    print("${root.value} ")
    preOrderTraversal(root.left)
    preOrderTraversal(root.right)
}

fun postOrderTraversal(root: Node?){
    if (root == null) return
    postOrderTraversal(root.left)
    postOrderTraversal(root.right)
    print("${root.value} ")
}

fun levelOrderTraversal(root: Node?){
    if (root == null) return
    val q = ArrayDeque<Node>()
    q.addLast(root)
    while (q.isNotEmpty()){
        val curr = q.removeFirst()
        print("${curr.value} ")
        if (curr.left != null) q.addLast(curr.left!!)
        if (curr.right != null) q.addLast(curr.right!!)
    }
}

fun insertInTree(root: Node, key: Int){
    val q = ArrayDeque<Node>()
    q.addLast(root)
    while (q.isNotEmpty()){
        val curr = q.removeFirst()
        if (curr.left == null){
            curr.left = Node(key)
            return
        } else if (curr.right == null){
            curr.right = Node(key)
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

    val root = Node(1)
    root.left = Node(2)
    root.right = Node(3)
    root.left?.left = Node(4)
    root.left?.right = Node(5)
    root.right?.left = Node(6)
    root.right?.right = Node(7)
}