package challenges.stack

import datastructures.stack.Stack

/**
 * Check for balanced parentheses. Given a string, check if there are ( and ) characters,
 * and return true if the parentheses in the string are balanced.
 * example: h((e))llo(world)() // balanced parentheses
 * example: (hello world // unbalanced parentheses
 * time complexity: O(n);space: On(n); see details below
 */
fun String.checkBalancedParentheses_Ashish(): Boolean {
    val stack = Stack<Char>()

    for (c in this) {
        if (c == '(') stack.push(c)
        else if (c == ')') stack.pop()
    }

    return stack.isEmpty
}

/**
 * The time complexity of this algorithm is O(n), where n is the number of characters in the string.
 * This algorithm also incurs an O(n) space complexity cost due to the usage of the Stack data structure
 */
fun String.checkBalancedParentheses_Another(): Boolean {
    val stack = Stack<Char>()

    for (c in this) {
        when (c) {
            '(' -> stack.push(c)
            ')' -> if (stack.isEmpty) {
                return false
            } else stack.pop()
        }
    }

    return stack.isEmpty
}

fun main() {
    val s1 = "h((e))llo(world)()"
    val s2 = "(hello world"

    println("s1 is balanced: ${s1.checkBalancedParentheses_Ashish()}")
    println("s1 is balanced: ${s1.checkBalancedParentheses_Another()}")
    println("s2 is balanced: ${s2.checkBalancedParentheses_Ashish()}")
    println("s2 is balanced: ${s2.checkBalancedParentheses_Another()}")
}