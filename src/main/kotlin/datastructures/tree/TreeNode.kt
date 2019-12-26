package datastructures.tree

import java.util.*
import kotlin.collections.ArrayList

class TreeNode<T>(val value: T) {
    val children: MutableList<TreeNode<T>> = mutableListOf()

    fun addChild(child: TreeNode<T>) = children.add(child)

    /**
     * Depth-first Traversal.
     * Time complexity is O(n) as they visit every node exactly once.
     * Extra Space required for Depth First Traversals is O(h) where h is maximum height of Binary Tree.
     * In Depth First Traversals, stack (or function call stack) stores all ancestors of a node
     */
    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this) // visit the root which is the this reference
        children.forEach {
            it.forEachDepthFirst(visit)
        }

    }

    /**
     * Level-order (or breadth first) traversal.
     * Time complexity is O(n) as they visit every node exactly once
     * Extra Space required for Level Order Traversal is O(w) where w is maximum width of Binary Tree.
     * In level order traversal, queue one by one stores nodes of different level
     */
    @Suppress("UNCHECKED_CAST")
    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this) // visit the root first
        val queue: Queue<TreeNode<T>> = ArrayList<TreeNode<T>>() as Queue<TreeNode<T>>

        children.forEach { queue.add(it) }

        var node: TreeNode<T>? = queue.remove()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.add(it) }
            node = if (queue.size > 0) {
                queue.remove()
            } else {
                null
            }
        }
    }

    fun search(valueToSearch: T): TreeNode<T>? {
        var result: TreeNode<T>? = null

        forEachLevelOrder {
            if (it.value == valueToSearch) result = it
        }

        return result
    }

    override fun toString(): String {
        return "TreeNode(value=$value, children=$children)"
    }
}

typealias Visitor<T> = (TreeNode<T>) -> Unit

// main method for quick, dirty testing only; prefer to use Unit tests
fun main() {
    val hot = TreeNode("Hot")
    val cold = TreeNode("Cold")
    val beveragesParent = TreeNode("Beverages")

    val beverages = beveragesParent.run {
        addChild(hot)
        addChild(cold)
    }

    println(beveragesParent)
}