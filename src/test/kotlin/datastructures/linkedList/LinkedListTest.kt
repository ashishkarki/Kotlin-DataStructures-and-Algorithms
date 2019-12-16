package datastructures.linkedList

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

internal class LinkedListTest {

    private val testLinkedList = LinkedList<Int>()

    @Test
    fun `push should return correct sequence`() {
        testLinkedList.push(3)
        testLinkedList.push(2)
        testLinkedList.push(1)

        "1 -> 2 -> 3" `should be equal to` testLinkedList.toString()
        assertEquals("1 -> 2 -> 3", testLinkedList.toString())

        testLinkedList.size `should be` 3
    }

    @Test
    fun `push should be chainable`() {
        testLinkedList.push(3).push(2).push(1)

        "1 -> 2 -> 3" `should be equal to` testLinkedList.toString()
        testLinkedList.size `should be` 3
    }

    @Test
    fun `append should return correct sequence`() {
        testLinkedList.append(1)
        testLinkedList.append(2)
        testLinkedList.append(3)

        "1 -> 2 -> 3" `should be equal to` testLinkedList.toString()
        testLinkedList.size `should be` 3
    }

    @Test
    fun `insert should work correctly`() {
        testLinkedList.append(1)
        testLinkedList.append(2)
        testLinkedList.append(3)

        // before inserting, there should be these 3 elements
        "1 -> 2 -> 3" `should be equal to` testLinkedList.toString()
        testLinkedList.size `should be` 3

        var afterNode = testLinkedList.findNodeAt(1)
        for (i in 1..3) {
            afterNode = afterNode?.let { testLinkedList.insert(-1 * i, it) }
        }

        // after inserting, there should be 4 elements
        "1 -> 2 -> -1 -> -2 -> -3 -> 3" `should be equal to` testLinkedList.toString()
        testLinkedList.size `should be` 6
    }

    @Test
    fun `pop should work correctly`() {
        testLinkedList.push(3).push(2).push(1)

        //before popping
        testLinkedList.toString() `should be equal to` "1 -> 2 -> 3"

        // after popping
        val poppedValue = testLinkedList.pop()
        testLinkedList.toString() `should be equal to` "2 -> 3"
        poppedValue `should be` 1
    }

    @Test
    fun `removeLast should work correctly`() {
        testLinkedList.push(3).push(2).push(1)

        //before removing last
        testLinkedList.toString() `should be equal to` "1 -> 2 -> 3"

        // after removing last
        val removedValue = testLinkedList.removeLast()
        testLinkedList.toString() `should be equal to` "1 -> 2"
        removedValue `should be` 3
    }

    @Test
    fun `removeAfter should work correctly`() {
        testLinkedList.push(3).push(2).push(1)

        //before removing after
        testLinkedList.toString() `should be equal to` "1 -> 2 -> 3"

        // after removing after
        val indexToRemoveAt = 1
        val beforeNode = testLinkedList.findNodeAt(indexToRemoveAt - 1)
        val removedAfterValue = beforeNode?.let { testLinkedList.removeAfter(it) }

        testLinkedList.toString() `should be equal to` "1 -> 3"
        removedAfterValue `should be` 2
    }

    @Test
    fun `removing elements should work correctly`() {
        val mutableCollection: MutableCollection<Int> = LinkedList()
        mutableCollection.add(3)
        mutableCollection.add(2)
        mutableCollection.add(1)

        // list before removing
        mutableCollection.toString() `should be equal to` "3 -> 2 -> 1"
        // after removing
        mutableCollection.remove(3)
        mutableCollection.toString() `should be equal to` "2 -> 1"
    }

    @Test
    fun `retainAll should work correctly`() {
        val mutableCollection: MutableCollection<Int> = LinkedList()
        mutableCollection.add(3)
        mutableCollection.add(2)
        mutableCollection.add(1)
        mutableCollection.add(4)
        mutableCollection.add(5)

        // list before retainAll
        mutableCollection.toString() `should be equal to` "3 -> 2 -> 1 -> 4 -> 5"
        // list after retainAll
        mutableCollection.retainAll(listOf(3, 4, 5))
        mutableCollection.toString() `should be equal to` "3 -> 4 -> 5"
    }

    @Test
    fun `removeAll should work correctly`() {
        val mutableCollection: MutableCollection<Int> = LinkedList()
        mutableCollection.add(3)
        mutableCollection.add(2)
        mutableCollection.add(1)
        mutableCollection.add(4)
        mutableCollection.add(5)

        // list before removeAll
        mutableCollection.toString() `should be equal to` "3 -> 2 -> 1 -> 4 -> 5"
        // list after removeAll
        mutableCollection.removeAll(listOf(3, 4, 5))
        mutableCollection.toString() `should be equal to` "2 -> 1"
    }

    @After
    fun tearDown() {
        testLinkedList.size = 0
    }

}