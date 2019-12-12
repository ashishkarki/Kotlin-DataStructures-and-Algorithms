package datastructures.linkedList

fun main() {
    println("creating and linking node example")

    val node1 = Node(data = 1)
    val node2 = Node(data = 2)
    val node3 = Node(data = 3)

    node1.next = node2
    node2.next = node3

    println(message = "Printing nodes: $node1")
}