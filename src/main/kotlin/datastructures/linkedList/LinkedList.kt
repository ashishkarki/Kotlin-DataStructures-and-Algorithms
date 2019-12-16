package datastructures.linkedList

class LinkedListIterator<T>(private val linkedList: LinkedList<T>) :
        MutableIterator<T> {
    private var index = 0
    private var lastVisitedNode: Node<T>? = null

    override fun hasNext() = index < linkedList.size

    override fun next(): T {
        // first check if index is okay
        if (index >= linkedList.size) throw IndexOutOfBoundsException()

        // go to next node and update lastVisited
        lastVisitedNode = if (index == 0) linkedList.findNodeAt(0)
        else lastVisitedNode?.next

        // update index
        index++
        return lastVisitedNode!!.data
    }

    override fun remove() {
        // 1: if we want to remove the first element
        if (index == 1) linkedList.pop() // since next() updates index by 1 when returning element;
        // so next() is done accessing the 0-th element, the index is 1 => that's why == 1 above
        else {
            // 2: find beforeNode to modify links after deletion of this node
            val beforeNode = linkedList.findNodeAt(index - 2) ?: return
            // 3: remove current node and move the lastVisitedNode to valid, before node
            linkedList.removeAfter(beforeNode)
            lastVisitedNode = beforeNode
        }

        // there is one less item, so move index by one
        index--
    }

}

class LinkedList<T> : MutableIterable<T>, MutableCollection<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    override var size = 0 // not using a private setter so as to use this property directly in unit test

    override fun isEmpty() = size == 0

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
    // complexity is O(1) since we are just changing links
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
    // complexity: O(1) - if we have the beforeNode, we just change the links in constant time
    fun removeAfter(beforeNode: Node<T>): T? {
        val result = beforeNode.next?.data

        // if the node to be removed is tail, update the tail to beforeNode
        if (beforeNode.next == tail) tail = beforeNode

        // if we the beforeNode is NOT the last/tail node, also decrease size
        if (beforeNode.next != null) size--

        // change pointers
        beforeNode.next = beforeNode.next?.next

        return result
    }

    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    // this iterates, in worst case, through all elements and so complexity: O(n)
    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }

        return false
    }

    // bit inefficient; complextiy: O(n^2) since two loops
    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched)) return false
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }

        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun remove(element: T): Boolean {
        // 1
        val iterator = iterator()
        // 2
        while (iterator.hasNext()) {
            val item = iterator.next()
            // 3 if element is found remove it
            if (item == element) {
                iterator.remove()
                return true
            }
        }

        return false
    }

    // i believe removeAll should remove all elements in the parameter
    override fun removeAll(elements: Collection<T>): Boolean {
        var result = true
        for (element in elements) {
            result = remove(element) && result
        }

        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false

        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }

        return result
    }

}