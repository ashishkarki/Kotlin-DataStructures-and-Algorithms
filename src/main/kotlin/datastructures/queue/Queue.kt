package datastructures.queue

interface Queue<T> {
    // enqueue adds element to the end of the queue
    fun enqueue(element: T): Boolean

    // dequeue returns the first/front element from queue
    fun dequeue(): T?

    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0

    // returns front element without removing it
    fun peek(): T?
}