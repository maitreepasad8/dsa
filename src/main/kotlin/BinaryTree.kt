
class BinaryTree {
    var root: Node?

    constructor() {
        root = null
    }
    constructor(data: Int) {
        root = Node(data)
    }

    private fun height(node: Node?): Int {
        if (node == null) return 0
        return maxOf(height(node.left), height(node.right)) + 1
    }

    fun height(): Int {
        return height(this.root)
    }

    private fun diameter(node: Node?): Int {
        if (node == null) return 0

        val lh = height(node.left)
        val rh = height(node.right)

        val ld = diameter(node.left)
        val rd = diameter(node.right)

        return maxOf(ld, rd, lh + rh + 1)

    }

    fun diameter(): Int {
        return diameter(this.root)
    }

    private fun inOrder(node: Node?) {
        if (node == null) return
        inOrder(node.left)
        print("${node.data} ")
        inOrder(node.right)
    }

    fun inOrder() {
        println()
        return inOrder(this.root)
    }

    private fun preOrder(node: Node?) {
        if (node == null) return
        print("${node.data} ")
        preOrder(node.left)
        preOrder(node.right)
    }

    fun preOrder() {
        println()
        return preOrder(this.root)
    }

    private fun postOrder(node: Node?) {
        if (node == null) return
        postOrder(node.left)
        postOrder(node.right)
        print("${node.data} ")
    }

    fun postOrder() {
        println()
        return postOrder(this.root)
    }

    private fun levelOrder(node: Node?) {
        val height = height(node)
        for (i in 1..height) {
            currentLevel(node, i)
            println()
        }
    }

    private fun currentLevel(node: Node?, level: Int) {
        if (node == null) return
        if (level == 1) print("${node.data} ")
        else {
            currentLevel(node.left, level - 1)
            currentLevel(node.right, level - 1)
        }
    }

    fun levelOrder() {
        println()
        return levelOrder(this.root)
    }

    // do an iterative level order traversal of the given tree using queue
    private fun insertLevelOrder(node: Node?, key: Int) {
        if (node == null) {
            this.root = Node(key)
            return
        }

        var temp = node
        val q =  ArrayDeque<Node>()
        q.addLast(temp)

        while (q.isNotEmpty()){
            temp = q.removeFirst()

            if(temp.left == null){
                temp.left = Node(key)
                break
            } else {
                q.addLast(temp.left!!)
            }

            if(temp.right == null){
                temp.right = Node(key)
                break
            } else {
                q.addLast(temp.right!!)
            }

        }
    }

    fun insertLevelOrder(key: Int) {
        return insertLevelOrder(this.root, key)
    }

    // Leetcode 226
    private fun invertTree(root: Node?): Node? {
        if (root == null) return null
        val tree = Node(root.data)
        tree.left = invertTree(root.right)
        tree.right = invertTree(root.left)
        return tree
    }

    fun invertTree() {
        this.root =  invertTree(this.root)
    }

    // Leetcode 100
    private fun sameTree(p: Node?, q: Node?): Boolean {
        if (p == null && q == null) return true
        return if (p != null && q != null) {
            if (p.data == q.data) {
                sameTree(p.left, q.left) && sameTree(p.right, q.right)
            } else false
        } else false
    }

    fun findLCA(p: Int, q: Int): Int {
//        return findLCA(this.root, p, q)
        return findLCASingleTraversal(this.root, p, q)?.data ?: -1
    }

    private fun findLCASingleTraversal(root: Node?, p: Int, q: Int): Node? {
        if (root == null) return null
        if (root.data == p || root.data == q) return root

        val leftLCA = findLCASingleTraversal(root.left, p, q)
        val rightLCA = findLCASingleTraversal(root.right, p, q)

        if (leftLCA != null && rightLCA != null) return root
        return leftLCA ?: rightLCA

    }

    private fun findLCA(root: Node?, p: Int, q: Int): Int {
        val path1 = arrayListOf<Int>()
        val path2 = arrayListOf<Int>()
        if (!findPath(root, p, path1) || !findPath(root, q, path2)) return  -1

        var i = 0
        while (i < path1.size && i < path2.size) {
            if (path1[i] != path2[i]) break
            i++
        }

        return path1[i-1]
    }

    // Finds the path from root node to given root of the
    // tree, Stores the path in arrayList path, returns
    // true if path exists otherwise false
    private fun findPath(root: Node?, n: Int, path: ArrayList<Int>): Boolean {
        if (root == null) return  false

        path.add(root.data)

        if (root.data == n) return true
        if (root.left != null && findPath(root.left, n, path)) return true
        if (root.right != null && findPath(root.right, n, path)) return true

        path.removeLast()
        return false
    }
}

fun main() {
    val bt = BinaryTree(4)

    bt.root?.left = Node(2)
    bt.root?.right = Node(7)

    bt.root?.left?.left = Node(1)
    bt.root?.left?.right = Node(3)

    bt.root?.right?.left = Node(6)
    bt.root?.right?.right = Node(9)

//    println(bt.height())
//    println(bt.diameter())
//    bt.inOrder()
//    bt.preOrder()
//    bt.postOrder()
//    bt.levelOrder()

//    bt.inOrder()
//    bt.insertLevelOrder(12)
//    bt.inOrder()

    bt.levelOrder()

    println(bt.findLCA(6,3))
}