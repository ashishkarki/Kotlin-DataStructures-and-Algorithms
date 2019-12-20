package datastructures.binaryTree

import kotlin.math.max

typealias Visitor<T> = (T?) -> Unit

class BinaryNode<T>(val value: T) {

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
     *
     * The height of a node is the number of edges from the node to the deepest leaf.
     * The height of a tree is a height of the root.
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

    /**
     * An example of serialization is JSON. Your task is to devise a way to serialize a binary tree into a list,
     * and a way to deserialize the list back into the same binary tree
     * One strategy is to use PreOrder traversal. As with all traversal functions,
     * this algorithm goes through every element of the tree once, so it has a time complexity of O(n)
     */
    fun traversePreOrderWithNull(visit: Visitor<T>) {
        visit(value)
        leftChild?.traversePreOrderWithNull(visit) ?: visit(null)
        rightChild?.traversePreOrderWithNull(visit) ?: visit(null)
    }

    /**
     * serialize returns a new array containing the values of the tree in pre-order.
     * The time complexity of the serialization step is O(n). Because you’re creating a new list,
     * this also incurs an O(n) space cost
     */
    fun serialize(node: BinaryNode<T> = this): MutableList<T?> {
        val list = mutableListOf<T?>()
        node.traversePreOrderWithNull { list.add(it) }

        return list
    }

    /**
     * In the serialization process, you performed a pre-order traversal and assembled the values into an array.
     * The deserialization process is to take each value of the array and reassemble it back to the tree
     *
     * Because you’re calling removeAt as many times as there are elements in the array,
     * this algorithm has an O(n²) time complexity
     */
    fun deserialize(list: MutableList<T?>): BinaryNode<T?>? {
        // 1
        // val rootValue = list.removeAt(0) ?: return null
        // based on deserializeOptimized, the above line becomes this:
        val rootValue = list.removeAt(list.size - 1) ?: return null
        /**
         * EXPLANATION FOR ABOVE LINE
         * removeAt(0) is an O(n) operation because, after every removal, every element after
         * the removed element must shift left to take up the missing space. In contrast,
         * list.removeAt(list.size - 1) is an O(1) operation.
         */

        // 2
        val root = BinaryNode<T?>(rootValue)

        root.leftChild = deserialize(list)
        root.rightChild = deserialize(list)

        return root
    }

    // this now becomes an O(n) time complexity operation since the removeAt(list.size -1) doesn't involve
    // shifting elements to the left and hence is only a O(1) process
    fun deserializeOptimized(list: MutableList<T?>): BinaryNode<T?>? {
        return deserialize(list.asReversed())
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