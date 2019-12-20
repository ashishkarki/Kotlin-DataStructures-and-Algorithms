package datastructures.binaryTree

import kotlin.math.max

typealias Visitor<T> = (T) -> Unit

class BinaryNode<T>(private val value: T) {

    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    /**
     * A Tree is typically traversed in two ways:
     * 1. Breadth First Traversal (Or Level Order Traversal)
     * 2. Depth First Traversals:
     * 2.1 Inorder Traversal (Left-Root-Right) => prints a tree in Ascending Order
     * 2.2 Preorder Traversal (Root-Left-Right)
     * 2.3 Postorder Traversal (Left-Right-Root)
     */

    /**
     * Overall note for pre/in/post order traversals:
     * Each one of these traversal algorithms has both a time and space complexity of O(n)
     */
    fun traverseInOrder(visit: Visitor<T>) {
        leftChild?.traverseInOrder(visit)
        visit(value)
        rightChild?.traverseInOrder(visit)
    }

    fun traversePreOrder(visit: Visitor<T>) {
        visit(value)
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }

    fun traversePostOrder(visit: Visitor<T>) {
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
        visit(value)
    }

    /**
     * Given a binary tree, find the height of the tree. The height of the binary tree is determined by
     * the distance between the root and the furthest leaf. The height of a binary tree with a single node
     * is zero since the single node is both the root and the furthest leaf.
     */
    /**
     * time complexity of O(n) since you need to traverse through all of the nodes.
     * This algorithm incurs a space cost of O(n) since you need to make the same n recursive calls to the call stack
     */
    fun heightOfBinaryTree(node: BinaryNode<T>? = this): Int {
        return node?.let {
            1 + max(heightOfBinaryTree(node.leftChild), heightOfBinaryTree(node.rightChild))
        } ?: -1
    }

    override fun toString() = diagram(this)

    private fun diagram(node: BinaryNode<T>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                diagram(node.rightChild, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.value}\n" + diagram(node.leftChild, "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }
}