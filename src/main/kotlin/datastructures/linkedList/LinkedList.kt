package datastructures.linkedList

class LinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    var size = 0

    private fun isEmpty() = size == 0

    override fun toString(): String {
        return if (isEmpty()) "Empty List!!"
        else head.toString()
    }

    // ADDING VALUES TO THE LINKED LIST. There are three ways:
    // push (at the start), append (at the end) and insert (after some node)
    // 1. push operation; complexity: O(1) - constant time - always return the first node
    fun push(data: T): LinkedList<T> {
        head = Node(data = data, next = head)

        if (tail == null) tail = head // if this is the only node, head and tail point to that one node

        size++

        return this
    }

    // 2. append (to end) operation; complexity: O(1) - if tail is known
    fun append(data: T) {
        // Case 1: if it is empty
        if (isEmpty()) {
            push(data)
            return
        }

        // Case 2: there is at least one node already
        tail?.next = Node(data)
        // move the tail to this last node
        tail = tail?.next

        size++
    }

    // 3. insert (after certain node); complexity: O(1) - we simply re-link nodes
    fun insert(data: T, afterNode: Node<T>): Node<T> {
        // Case 1: if the new node is supposed to be the last node
        if (tail == afterNode) {
            append(data)
            return tail as Node<T> // or return tail!!
        }

        // Case 2: the new node is in the middle
        val newNode = Node(data = data, next = afterNode.next)
        afterNode.next = newNode
        size++

        return newNode
    }

    // helper function to the "insert" function. finds the node right after index
    // complexity: O(i) where i is the given index to search for
    fun findNodeAt(index: Int): Node<T>? {
        // 1
        var currentNode = head
        var currentIndex = 0

        // 2
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }

        return currentNode
    }

    // Following are Value Removal operations - 3 of them
    // pop (to remove at front of the list), removeLast (node), removeAfter (specific node)

    // 1. pop/remove the first node in the linkedlist;
    fun pop(): T? {
        if (!isEmpty()) size--

        val result = head?.data
        head = head?.next

        if (isEmpty()) tail = null

        return result
    }

    // 2. removes the last element/node in the LinkedList;
    // since we have to traverse whole list; complexity: O(n)
    fun removeLast(): T? {
        // bit complex since we have to find the node previous to tail

        // Step 1: check if there is a head; if no just return null
        val head = head ?: return null

        // Step 2: if there is only one node, simply return that
        if (head.next == null) return pop()

        // step 3: decrease size
        size--

        // step 4: create pointers to traverse the list
        var prev = head
        var current = head
        var next = current.next

        while (next != null) {
            prev = current
            current = next
            next = current.next
        }

        prev.next = null // set current node which is now last node to null to remove it
        tail = prev // since last node is removed, tail is the previous to last node

        return current.data
    }

    // 3. removeAfter: remove after certain node; first find the before node
    // complexity:
    fun removeAfter(node: Node<T>): T? {

    }

    // helper method to insert a node as first element
    fun linkHead(data: T): LinkedList<T>? {
        return null
    }

}