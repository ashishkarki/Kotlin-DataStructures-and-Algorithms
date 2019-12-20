package datastructures.binaryTree

class BinaryTreeUtil {
    companion object {
        fun populateBinaryTree(): BinaryNode<Int> {
            val zero = BinaryNode(0)
            val one = BinaryNode(1)
            val five = BinaryNode(5)
            val seven = BinaryNode(7)
            val eight = BinaryNode(8)
            val nine = BinaryNode(9)

            seven.leftChild = one
            one.leftChild = zero
            one.rightChild = five
            seven.rightChild = nine
            nine.leftChild = eight

            return seven
        }
    }
}