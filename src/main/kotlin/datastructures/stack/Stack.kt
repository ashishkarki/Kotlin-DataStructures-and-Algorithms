package datastructures.stack

interface IStack<Element> {
    fun push(element: Element)
    fun pop(): Element?
    fun peek(): Element?

    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0
}

class Stack<T : Any> : IStack<T> {
    private val storage = arrayListOf<T>()


    override fun toString() = buildString {
        append("top > ")
        storage.asReversed().forEach {
            append("$it > ")
        }
        append("bottom")
    }

    // since we are using arrayList, time complexity is O(1)
    override fun push(element: T) {
        // add() puts element at the end of the arraylist and hence top of the stack
        storage.add(element)
    }

    // since we are using arrayList, time complexity is O(1)
    override fun pop(): T? {
        if (isEmpty) return null

        return storage.removeAt(storage.size - 1)
    }

    override fun peek() = storage.lastOrNull()

    override val count: Int
        get() = storage.size
}