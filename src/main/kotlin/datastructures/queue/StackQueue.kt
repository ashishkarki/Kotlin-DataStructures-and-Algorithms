package datastructures.queue

import datastructures.stack.Stack

class StackQueue<T> : Queue<T> {
    /**
     * When you need to dequeue an element, you reverse the right stack and
     * place it in the left stack so that you can retrieve the elements using FIFO order
     */
    private val leftStack = Stack<T>()

    // enqueued element goes to right element
    private val rightStack = Stack<T>()

    // time: O(1) since stack's push is O(1); space: O(n): since there are two stacks
    override fun enqueue(element: T): Boolean {
        rightStack.push(element)
        return true
    }

    // time O(1) since stack's pop is O(1); space: O(n): since there are two stacks
    override fun dequeue(): T? {
        if (leftStack.isEmpty) moveElementsFromRighToLeft()

        return leftStack.pop()
    }

    // time: O(1)
    override val isEmpty: Boolean
        get() = leftStack.isEmpty && rightStack.isEmpty

    override val count: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    // time: O(1) - returns the first/front-element in this without removing it
    override fun peek(): T? {
        if (leftStack.isEmpty) moveElementsFromRighToLeft()

        return leftStack.peek()
    }


    private fun moveElementsFromRighToLeft() {
        var nextElement = rightStack.pop()
        while (nextElement != null) {
            leftStack.push(nextElement)
            nextElement = rightStack.pop()
        }
    }

    override fun toString(): String {
        return "StackQueue(leftStack=$leftStack, rightStack=$rightStack)"
    }
}

fun main() {
    val queue = StackQueue<String>().apply {
        enqueue("Ray")
        enqueue("Brian")
        enqueue("Eric")
    }
    println("whole queue Before dequeue: $queue")
    queue.dequeue()
    println("whole queue After dequeue: $queue")
    println("Next up: ${queue.peek()}")
}