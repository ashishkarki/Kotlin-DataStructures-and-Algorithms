package datastructures.stack

import javax.lang.model.util.Elements

interface IStack<Element> {
    fun push(element: Element)
    fun pop(): Element?
    fun peek(): Element?

    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0
}

fun <Element> stackOf(vararg elements: Element): IStack<Element> {
    return Stack.create(elements.asList())
}

class Stack<T> : IStack<T> {
    private val storage = arrayListOf<T>()

    companion object {
        fun <Element> create(items: Iterable<Element>): IStack<Element> {
            val stack = Stack<Element>()

            for (item in items) stack.push(item)

            return stack
        }
    }

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

    // this should be O(1) as array list does index accessing
    // returns the top-most element in the stack without removing it
    override fun peek() = storage.lastOrNull()

    override val count: Int
        get() = storage.size
}