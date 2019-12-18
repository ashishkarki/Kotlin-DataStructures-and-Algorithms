package datastructures.queue

class DoublyLinkedListQueue<T> : Queue<T> {
    private val queue = DoublyLinkedList<T>()
    private var size = 0

    // time complexity is O(1) since we are using doubly linked list
    // space complexity: O(n) since there is a new linked list created
    override fun enqueue(element: T): Boolean {
        queue.addLast(element)
        size++
        return true
    }

    /**
     * Time complexity: O(1) since with doubly linked list, we only change links
     * space complexity: O(n) since there is a new linked list created
     */
    override fun dequeue(): T? {
        // check if there is a node at all, if there is no node just return null
        val firstNode = queue.peek() ?: return null

        // otherwise decrease size and remove the first node
        size--
        return queue.removeAt(0)
    }

    override val count: Int
        get() = size

    override fun peek(): T? = queue.first

    override fun toString() = queue.toString()
}

fun main() {
    val queue = DoublyLinkedListQueue<String>().apply {
        enqueue("Ray")
        enqueue("Brian")
        enqueue("Eric")
    }
    println(queue)
    queue.dequeue()
    println(queue)
    println("Next up: ${queue.peek()}")
}