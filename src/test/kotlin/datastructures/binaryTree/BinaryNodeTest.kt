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

    @Test
    fun `heightOfBinaryTree should give correct height`() {
        println("height of this tree: ${binaryTree.heightOfBinaryTree()}")
    }

    @Test
    fun `serialization and deserialization should work correctly`() {
        println("printing original tree: $binaryTree")
        val array = binaryTree.serialize()
        println("serialized tree is $array")
        println(binaryTree.deserializeOptimized(array))
    }
}