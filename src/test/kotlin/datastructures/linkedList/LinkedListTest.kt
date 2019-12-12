package datastructures.linkedList

import org.amshove.kluent.`should be equal to`
import org.junit.Test
import kotlin.test.assertEquals

internal class LinkedListTest {

    private val testLinkedList = LinkedList<Int>()

    @Test
    fun testToString_NonEmpty() {
        testLinkedList.push(3)
        testLinkedList.push(2)
        testLinkedList.push(1)

        "1 -> 2 -> 3" `should be equal to` testLinkedList.toString()
        assertEquals("1 -> 2 -> 3", testLinkedList.toString())
    }

    @Test
    fun push() {
    }
}