package datastructures.tree

import java.util.*

class TreeNode<T>(val value: T) {
    val children: MutableList<TreeNode<T>> = mutableListOf()

    fun addChild(child: TreeNode<T>) = children.add(child)

    /**
     * Depth-first Traversal
     */
    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this) // visit the root which is the this reference
        children.forEach {
            it.forEachDepthFirst(visit)
        }

    }

    /**
     * Level-order (or breadth first) traversal
     */
    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this) // visit the root first
        val queue: Queue<TreeNode<T>> = LinkedList<TreeNode<T>>()

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