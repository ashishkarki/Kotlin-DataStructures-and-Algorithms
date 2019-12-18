package challenges.stack

import datastructures.linkedList.LinkedList
import datastructures.stack.Stack

// challenge: Given a linked list, print the nodes in reverse order.
// You should not use recursion to solve this problem

// Solution 1: by using a Stack
/**
 * The time complexity of pushing the nodes into the stack is O(n). The time complexity of popping the stack
 * to print the values is also O(n). Overall, the time complexity of this algorithm is O(n)
 *
 * Since you’re allocating a container (the stack) inside the function,
 * you also incur an O(n) space complexity cost
 */
fun <T> LinkedList<T>.printInReverseUsingStack() {
    val stack = Stack<T>()

    for (node in this) stack.push(node)

    var node = stack.pop()
    while (node != null) {
        print("$node ")
        node = stack.pop()
    }
}

fun main() {
    val linkedList = LinkedList<Int>()
    linkedList.push(3).push(2).push(1) // push to the start of linkedlist
    println("current linkedlist is ${linkedList.toString()}")

    println("Reversing linked list using stack::")
    linkedList.printInReverseUsingStack()
}