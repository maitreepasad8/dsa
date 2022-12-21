
object BST {
    fun findCeil(root: Node?, key: Int): Int{
        if (root == null) return -1

        if (root.data == key) return key

        // If root's key is smaller, ceil must be in right subtree
        if (root.data < key) {
            return findCeil(root.right, key)
        }

        // Else, either left subtree or root has the ceil value
        val ceil = findCeil(root.left, key)
        return if (ceil >= key) ceil else root.data
    }
}