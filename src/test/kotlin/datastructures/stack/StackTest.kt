package datastructures.stack

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
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

    @Test
    fun `create() should build stack from Iterable`() {
        val someList = listOf<String>("A", "B", "C", "D")

        val stack = Stack.create(someList)

        stack.toString() `should equal` "top > D > C > B > A > bottom"
    }

    @Test
    fun `stackOf() should build a new stack of elements`() {
        val stack = stackOf(1.0, 2.0, 3.0)

        stack.toString() `should equal` "top > 3.0 > 2.0 > 1.0 > bottom"
    }
}