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

    // REMOVING ELEMENTS has few different scenarios:
    // 1. Leaf Node: Removing a leaf node is straightforward; simply detach the leaf node
    // 2. One child only: When removing nodes with one child, you need to reconnect that one child with the rest of the tree
    // 3. With Two/both children: When removing a node with two children, replace the node you removed with
    // the smallest node in its right subtree. Based on the rules of the BST, this is the leftmost node of the right subtree

    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(node: BinaryNode<T>?, value: T): BinaryNode<T>? {
        node ?: return null

        when {
            value == node.value -> { // handle various removal scenarios
                // 1: removing leaf node
                if (node.leftChild == null && node.rightChild == null) return null
                // 2: removing a node with only one child
                else if (node.leftChild == null) return node.rightChild
                else if (node.rightChild == null) return node.leftChild
                // 3. removing a node that has both children: replace that node with leftmost node of right child
                else
                    node.rightChild?.min?.value?.let {
                        node.value = it
                    }

                node.rightChild = remove(node.rightChild, node.value)
            }
            value < node.value -> node.leftChild = remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }

        return node
    }

    // end of removing elements logic

    override fun toString() = root?.toString() ?: "empty tree"
}