package datastructures.binaryTree

import org.junit.jupiter.api.Test

internal class BinarySearchTreeTest {

    private val exampleTree = BinarySearchTree<Int>().apply {
        insert(3)
        insert(1)
        insert(4)
        insert(0)
        insert(2)
        insert(5)
    }

    @Test
    fun `setting up and proving that insert() works correctly`() {
        val bst = BinarySearchTree<Int>()
        (0..4).forEach {
            bst.insert(it)
        }
        println(bst)
    }

    @Test
    fun `contains should return correct true if node is present`() {
        if (exampleTree.contains(5)) {
            println("Found 5!")
        } else {
            println("Couldn't find 5")
        }
    }

    @Test
    fun `Optimized contains should return correct true if node is present`() {
        if (exampleTree.containsOptimized(5)) {
            println("Found 5!")
        } else {
            println("Couldn't find 5")
        }
    }
}