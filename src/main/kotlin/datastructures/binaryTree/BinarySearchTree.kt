package datastructures.binaryTree

/**
 * By definition, binary search trees can only hold values that are Comparable
 */
class BinarySearchTree<T : Comparable<T>> {

    var root: BinaryNode<T>? = null

    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun insert(
            node: BinaryNode<T>?,
            value: T
    ): BinaryNode<T> {
        // 1
        node ?: return BinaryNode(value) // base case
        // 2
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }

        // 3
        return node
        // the tree created by using the logic above is bit unbalanced and
        // An unbalanced tree affects performance. If you insert 5 into the unbalanced tree you created,
        // it becomes an O(n) operation.
    }

    /**
     * In-order traversal has a time complexity of O(n), thus this implementation of contains
     * has the same time complexity as an exhaustive search through an unsorted array.
     * However, you can do better!
     */
    fun contains(value: T): Boolean {
        root ?: return false // if tree is empty, return false

        var found = false
        root?.traverseInOrder {
            if (value == it) found = true
        }

        return found
    }

    /**
     * This implementation of contains is an O(log n) operation in a balanced binary search tree
     */
    fun containsOptimized(value: T): Boolean {
        // 1
        var current = root

        //2
        while (current != null) {
            if (current.value == value) return true
            else current = if (value < current.value) current.leftChild else current.rightChild
        }

        return false
    }

    fun contains2(value: T): Boolean {
        // 1
        var current = root

        // 2
        while (current != null) {
            // 3
            if (current.value == value) {
                return true
            }

            // 4
            current = if (value < current.value) {
                current.leftChild
            } else {
                current.rightChild
            }
        }

        return false
    }

    override fun toString() = root?.toString() ?: "empty tree"
}