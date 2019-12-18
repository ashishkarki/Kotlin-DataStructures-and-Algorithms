package datastructures.stack

import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.Assert.assertEquals
import kotlin.test.assertTrue

internal class StackTest {
    private var stack = Stack<Int>()

    @AfterEach
    fun teardown() {
        stack.apply { null }
    }

    @Test
    fun testPush() {
        stack = Stack<Int>().apply {
            push(1)
            push(2)
            push(3)
            push(4)
        }

        println("Insider PUSH. Stack looks like: $stack")
        assertEquals("top > 4 > 3 > 2 > 1 > bottom", stack.toString())
    }

    @Test
    fun testPop() {
        stack = Stack<Int>().apply {
            push(1)
            push(2)
            push(3)
            push(4)
        }

        assertEquals("top > 4 > 3 > 2 > 1 > bottom", stack.toString())

        val poppedElement = stack.pop()
        assertTrue(poppedElement == 4)

        assertEquals("top > 3 > 2 > 1 > bottom", stack.toString())
    }
}