package datastructures.binaryTree

import kotlin.math.max

typealias Visitor<T> = (T?) -> Unit

class BinaryNode<T : Comparable<T>>(var value: T) {

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
    fun deserialize(list: MutableList<T?>): BinaryNode<T>? {
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
        val root = BinaryNode<T>(rootValue)

        root.leftChild = deserialize(list)
        root.rightChild = deserialize(list)

        return root
    }

    // this now becomes an O(n) time complexity operation since the removeAt(list.size -1) doesn't involve
    // shifting elements to the left and hence is only a O(1) process
    fun deserializeOptimized(list: MutableList<T?>): BinaryNode<T>? {
        return deserialize(list.asReversed())
    }

    // This recursive min property in BinaryNode will help you find the minimum node in a subtree
    // The time complexity of this function is O(n). The space complexity of this function is O(n)
    val min: BinaryNode<T>?
        get() = leftChild?.min ?: this

    val isBinarySearchTree: Boolean
        get() = isBST(this, min = null, max = null)

    /**
     * The time complexity of this solution is O(n) since you need to traverse through the entire tree once.
     * There is also a O(n) space cost since you’re making n recursive calls.
     */
    private fun isBST(tree: BinaryNode<T>?, min: T?, max: T?): Boolean {
        // 1: A null node is a binary search tree, so you’ll return true in that case
        tree ?: return true

        //2: If the current value exceeds the bounds of the min and max values,
        // the current node does not respect the binary search tree rules.
        if (min != null && tree.value <= min) return false
        else if (max != null && tree.value > max) return false

        // 3: If any of the recursive calls evaluate false, the false value will propagate to the top.
        // When traversing through the left children, the current value is passed in as the max value.
        // This is because nodes in the left side cannot be greater than the parent. Vice versa, when traversing to the right, the min value is updated to the current value. Nodes in the right side must be greater than the parent.
        // If any of the recursive calls evaluate false, the false value will propagate to the top.
        return isBST(tree.leftChild, min, tree.value) && isBST(tree.rightChild, tree.value, max)
    }

    /**
     * Override equals() to check whether two binary search trees are equal.
     * For two binary trees to be equal, both trees must have the same elements in the same order.
     * time complexity of this function is O(n). The space complexity of this function is O(n)
     */
    override fun equals(other: Any?): Boolean {
        return if (other != null && other is BinaryNode<*>) {
            this.value == other.value
                    && this.leftChild == other.leftChild
                    && this.rightChild == other.rightChild
        } else {
            false
        }
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

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + (leftChild?.hashCode() ?: 0)
        result = 31 * result + (rightChild?.hashCode() ?: 0)
        return result
    }
}