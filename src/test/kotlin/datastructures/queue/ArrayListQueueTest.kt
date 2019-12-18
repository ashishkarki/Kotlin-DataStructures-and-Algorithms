package datastructures.queue

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ArrayListQueueTest {
    private var queue: ArrayListQueue<String> = ArrayListQueue()

    @BeforeEach
    fun setup() {
        queue = ArrayListQueue<String>().apply {
            enqueue("Roger")
            enqueue("Brandon")
            enqueue("Easton")
        }
    }

    @Test
    fun `enqueue should work correctly`() {
        queue.toString() `should be equal to` "[Roger, Brandon, Easton]"
    }

    @Test
    fun `dequeue should work correctly`() {
        val dequeuedElement = queue.dequeue()

        if (dequeuedElement != null) {
            dequeuedElement `should be equal to` "Roger"
            queue.toString() `should be equal to` "[Brandon, Easton]"
        }
    }

    @Test
    fun `peek should work correctly`() {
        val peekedValue = queue.peek()

        if (peekedValue != null) {
            peekedValue `should be equal to` "Roger"
            queue.toString() `should be equal to` "[Roger, Brandon, Easton]"
        }
    }
}