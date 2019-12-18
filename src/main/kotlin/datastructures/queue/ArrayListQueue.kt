package datastructures.queue

class ArrayListQueue<T> : Queue<T> {
    private val queue = arrayListOf<T>()

    /**
     * Regardless of the size of the list, enqueueing an element is an O(1) operation.
     * This is because the list has empty space at the back
     */
    override fun enqueue(element: T): Boolean {
        queue.add(element)
        return true
    }

    /**
     * Removing an element from the front of the queue is an O(n) operation.
     * To dequeue, you remove the element from the beginning of the list.
     * This is always a linear time operation because it requires all of
     * the remaining elements in the list to be shifted in memory
     */
    override fun dequeue() = if (isEmpty) null else queue.removeAt(0)

    // since using arraylist, time complexity is O(1)
    override val count: Int
        get() = queue.size

    // since using arraylist, time complexity is O(1)
    override fun peek() = queue.getOrNull(0)

    override fun toString() = queue.toString()


}