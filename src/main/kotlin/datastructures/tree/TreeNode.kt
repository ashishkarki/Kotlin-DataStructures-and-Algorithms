package datastructures.tree

class TreeNode<T>(val value: T) {
    private val children: MutableList<TreeNode<T>> = mutableListOf()

    fun addChild(child: TreeNode<T>) = children.add(child)

    override fun toString(): String {
        return "TreeNode(value=$value, children=$children)"
    }
}

// main method for quick, dirty testing only; prefer to use Unit tests
fun main() {
    val hot = TreeNode("Hot")
    val cold = TreeNode("Cold")
    val beveragesParent = TreeNode("Beverages")

    val beverages = beveragesParent.run {
        addChild(hot)
        addChild(cold)
    }

    println(beveragesParent)
}