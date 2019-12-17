package challenges.linkedList

import datastructures.linkedList.LinkedList
import datastructures.linkedList.Node

// Solution 2: using recursive function to reverse the lit
// recursive extension function; calls itself on the next node
fun <T> Node<T>?.printInReverse() {
    this?.next?.printInReverse() // terminates after next = null

    // T print coming after the above recursive call, is only triggered after the final/base case
    // i.e when next = null => so this prints the list in reverse
    if (this?.next != null) {
        print(" -> ")
    }
    print(this?.data.toString())
}

// extension function way of doing things
fun <T> LinkedList<T>.printInReverse() {
    this.findNodeAt(0)?.printInReverse()
}
// end of Solution 2

/**
 * Solution 3: One solution is to have two references traverse down the nodes of the list where
 * one is twice as fast as the other. Once the faster reference reaches the end,
 * the slower reference will be in the middle.
 * */
// Time complexity: O(n) since it traverses the whole list once
fun <T> LinkedList<T>.getMiddle(): Node<T>? {
    var slow = this.findNodeAt(0)
    var fast = this.findNodeAt(0)

    while (fast != null) {
        fast = fast.next
        if (fast != null) {
            fast = fast.next
            slow = slow?.next
        }
    }

    return slow
}

// Solution 4: time complexity is O(n) but also space complexity: O(n) since it creates a new list
/**
 * reverse a list by using a recursive function that goes to the end of the list and
 * then starts copying the nodes when it returns, in a new linked list
 */
private fun <T> addInReverse(list: LinkedList<T>, node: Node<T>) {
    // 1: go to next
    val next = node.next

    if (next != null) {
        // 2: if we haven't reached the end, recursively call this function
        addInReverse(list, next)
    }

    // 3: once we reached the end, start adding to the list from the end
    list.append(node.data)
}

// extension function to test the addInReverse() above
fun <T> LinkedList<T>.reversed(): LinkedList<T> {
    val result = LinkedList<T>()

    val head = this.findNodeAt(0)
    if (head != null) {
        addInReverse(result, head)
    }

    return result
}

// Solution 1: merging two sorted lists

// helper function: adds the current node to the result list and returns the next node.
private fun <T : Comparable<T>> append(
        result: LinkedList<T>,
        node: Node<T>
): Node<T>? {
    result.append(node.data)
    return node.next
}

// time complexity: O(m + n) where m and n are number of nodes in each list
fun <T : Comparable<T>> LinkedList<T>.mergeSorted(otherList: LinkedList<T>)
        : LinkedList<T> {
    // 1: deal with case when one or other list is empty
    if (otherList.isEmpty()) return this
    if (this.isEmpty()) return otherList

    val result = LinkedList<T>()
    // 1
    var thisNode = this.findNodeAt(0)
    var otherNode = otherList.findNodeAt(0)
    // 2
    while (thisNode != null && otherNode != null) {
        // 3
        if (thisNode.data < otherNode.data) thisNode = append(result, thisNode)
        else otherNode = append(result, otherNode)
    }

    // 4: handle the remaining nodes in any of the two lists
    while (thisNode != null) thisNode = append(result, thisNode)
    while (otherNode != null) otherNode = append(result, otherNode)

    return result
}

class ReverseLinkedList<T>(private val linkedList: LinkedList<T>) {
    // Solution 1: using push to reverse the list
    fun listReverser(): LinkedList<T> {
        val reversedList = LinkedList<T>()

        // loop through original list and append elements to reversedList
        val iterator = linkedList.iterator()

        while (iterator.hasNext()) {
            val item = iterator.next()
            reversedList.push(item)
        }

        return reversedList
    }
}

private fun testMergeSorted() {
    val list = LinkedList<Int>()
    list.add(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.add(5)

    val other = LinkedList<Int>()
    other.add(-1)
    other.add(0)
    other.add(2)
    other.add(2)
    other.add(7)

    println("Left: $list")
    println("Right: $other")
    println("Merged: ${list.mergeSorted(other)}")
}

fun main() {
    val linkedList = LinkedList<Int>()
    linkedList.append(3)
    linkedList.append(2)
    linkedList.append(1)

    println("The original list is: $linkedList")

    val reverseLinkedList = ReverseLinkedList(linkedList)
    println("the reversed list is using simple method: ${reverseLinkedList.listReverser()}")

    print("Reversed list using extension function: ")
    linkedList.printInReverse()

    println("\n experimenting with print middle: ${linkedList.getMiddle()}")

    println("experimenting with reversed(): ${linkedList.reversed()}")

    testMergeSorted()
}
