package datastructures.binaryTree

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BinaryNodeTest {

    private lateinit var binaryTree: BinaryNode<Int>

    @BeforeEach
    fun setupOnce() {
        binaryTree = BinaryTreeUtil.populateBinaryTree()
    }

    @Test
    fun simpleBinaryTreeTest() {
        // val binaryTree = BinaryTreeUtil.populateBinaryTree()

        println("simple binary tree: $binaryTree")
    }

    @Test
    fun `traverseInOrder should print left-root-right order`() {
        println("InOrder traversal of binary tree: ")
        binaryTree.traverseInOrder { println(it) }
    }

    @Test
    fun `traversePreOrder should print root-left-right order`() {
        println("PreOrder traversal of binary tree: ")
        binaryTree.traversePreOrder { println(it) }
    }

    @Test
    fun `traversePostOrder should print left-right-root order`() {
        println("PostOrder traversal of binary tree: ")
        binaryTree.traversePostOrder { println(it) }
    }
}