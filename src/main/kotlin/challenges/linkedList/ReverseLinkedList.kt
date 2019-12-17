package challenges.linkedList

import datastructures.linkedList.LinkedList
import datastructures.linkedList.Node

// recursive extension function; calls itself on the next node
fun <Any> Node<Any>?.printInReverse() {
    this?.next?.printInReverse() // terminates after next = null

    // any print coming after the above recursive call, is only triggered after the final/base case
    // i.e when next = null => so this prints the list in reverse
    if (this?.next != null) {
        print(" -> ")
    }
    print(this?.data.toString())
}

// extension function way of doing things
fun <Any> LinkedList<Any>.printInReverse() {
    this.findNodeAt(0)?.printInReverse()
}

class ReverseLinkedList(private val linkedList: LinkedList<Any>) {
    fun listReverser(): LinkedList<Any> {
        val reversedList = LinkedList<Any>()

        // loop through original list and append elements to reversedList
        val iterator = linkedList.iterator()

        while (iterator.hasNext()) {
            val item = iterator.next()
            reversedList.push(item)
        }

        return reversedList
    }
}

fun main() {
    val linkedList = LinkedList<Any>()
    linkedList.append(3)
    linkedList.append(2)
    linkedList.append(1)

    println("The original list is: $linkedList")

    val reverseLinkedList = ReverseLinkedList(linkedList)
    println("the reversed list is using simple method: ${reverseLinkedList.listReverser()}")

    print("Reversed list using extension function: ")
    linkedList.printInReverse()
}
