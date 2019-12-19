package challenges.queue

import datastructures.queue.ArrayListQueue
import datastructures.queue.Queue
import datastructures.stack.Stack

/**
 * Implement a method to reverse the contents of a queue
 * using an extension function.
 *
 * he time complexity is overall O(n). You loop through the elements twice.
 * Once for removing the elements off the queue, and once for removing the elements off the stack
 */
fun <T> Queue<T>.reverse() {
    val stack = Stack<T>()

    while (!this.isEmpty) {
        stack.push(this.dequeue()!!) // stack push is O(1) but we do this for n elements so over all O(n)
    }

    while (!stack.isEmpty) { // again overall O(n)
        this.enqueue(stack.pop()!!)
    }
}

fun main() {
    val queue = ArrayListQueue<String>().apply {
        enqueue("1")
        enqueue("21")
        enqueue("18")
        enqueue("42")
    }

    println("before reversing: $queue")
    queue.reverse()
    println("after reversing: $queue")
}