package datastructures.tree

class TreeUtil {
    companion object {
        fun makeBeverageTree(): TreeNode<String> {
            val tree = TreeNode("Beverages")

            val hot = TreeNode("hot")
            val cold = TreeNode("cold")

            val tea = TreeNode("tea")
            val coffee = TreeNode("coffee")
            val chocolate = TreeNode("cocoa")

            val blackTea = TreeNode("black")
            val greenTea = TreeNode("green")
            val chaiTea = TreeNode("chai")

            val soda = TreeNode("soda")
            val milk = TreeNode("milk")

            val gingerAle = TreeNode("ginger ale")
            val bitterLemon = TreeNode("bitter lemon")

            tree.addChild(hot)
            tree.addChild(cold)
            hot.addChild(tea)
            hot.addChild(coffee)
            hot.addChild(chocolate)

            cold.addChild(soda)
            cold.addChild(milk)

            tea.addChild(blackTea)
            tea.addChild(greenTea)
            tea.addChild(chaiTea)

            soda.addChild(gingerAle)
            soda.addChild(bitterLemon)

            return tree
        }
    }
}