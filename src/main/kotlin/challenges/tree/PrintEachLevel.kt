package challenges.tree

import datastructures.tree.TreeNode
import datastructures.tree.TreeUtil.Companion.makeBeverageTree
import java.util.*

/**
 * Print the values in a tree in an order based on their level.
 * Nodes belonging to the same level should be printed on the same line
 */

typealias Visitor<T> = (TreeNode<T>) -> Unit

fun <T> TreeNode<T>.printEachLevel_Ashish(visit: Visitor<T>) {
    visit(this)
    println()

    val queue: LinkedList<TreeNode<T>> = LinkedList<TreeNode<T>>()
    this.children.forEach {
        queue.add(it)
        visit(it)
    }
    println()

    var node: TreeNode<T>? = queue.remove()
    while (node != null) {
        node.children.forEach {
            queue.add(it)
            visit(it)
        }
        println()

        node = if (queue.size > 0) queue.remove() else null
    }
}

fun <T> TreeNode<T>.printEachLevel(visit: Visitor<T>) {
    // 1: create a queue to go through each level
    val queue: Queue<TreeNode<T>> = LinkedList<TreeNode<T>>()
    var nodesLeftInCurrentLevel = 0

    queue.add(this)

    // 2
    while (queue.isEmpty().not()) {
        // 3
        nodesLeftInCurrentLevel = queue.size

        // 4
        while (nodesLeftInCurrentLevel > 0) {
            val node = queue.remove() // remove at head; just like dequeue
            node?.let { treeNode ->
                visit(treeNode)
                node.children.forEach { queue.add(it) }
                nodesLeftInCurrentLevel--
            } ?: break
        }

        // 5
        println()
    }
}

fun main() {
    val tree = makeBeverageTree()

    tree.printEachLevel_Ashish { print("${it.value} ") }
    println("now printing with better gaps::")
    tree.printEachLevel { print("${it.value} ") }
}