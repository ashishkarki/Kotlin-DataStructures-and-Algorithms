package datastructures.linkedList

class LinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    private fun isEmpty() = size == 0

    override fun toString(): String {
        return if (isEmpty()) "Empty List!!"
        else head.toString()
    }

    // ADDING VALUES TO THE LINKED LIST. There are three ways:
    // push (at the start), append (at the end) and insert (after some node)
    // 1. push operation
    fun push(data: T) {
        head = Node(data = data, next = head)

        if (tail == null) tail = head // if this is the only node, head and tail point to that one node

        size++
    }

    //

}