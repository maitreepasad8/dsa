
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
}

fun main() {
    val bt = BinaryTree(10)
    bt.root?.left = Node(11)
    bt.root?.right = Node(9)
    bt.root?.left?.left = Node(7)

    bt.root?.right?.left = Node(15)
    bt.root?.right?.right = Node(8)

//    println(bt.height())
//    println(bt.diameter())
//    bt.inOrder()
//    bt.preOrder()
//    bt.postOrder()
//    bt.levelOrder()

//    bt.inOrder()
//    bt.insertLevelOrder(12)
//    bt.inOrder()

}