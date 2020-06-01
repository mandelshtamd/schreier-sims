package schreierImpl

import Permutation
import size
import java.util.*
import kotlin.collections.HashMap

class TwoElementsTree(
    private val root: Pair<Int, Int>,
    private val permutations: MutableList<Permutation>,
    val nodes: HashMap<Pair<Int, Int>, Node> = HashMap())
    {

        init {
            createTree()
        }

        class Node() {}

        private fun createTree() {

            val been = HashMap<Pair<Int, Int>, Boolean>()
            val queue: Queue<Pair<Int, Int>> = LinkedList()

            val id = MutableList(size) { 0 }
            for (i in 0 until size) {
                id[i] = i
            }

            queue.add(root)
            been[root] = true
            nodes[root] = Node()

            while (queue.isNotEmpty()) {
                val element = queue.remove()
                for (i in 0 until permutations.size) {
                    val newElementFirst = permutations[i].appliedToElement(element.first)
                    val newElementSecond = permutations[i].appliedToElement(element.second)
                    val newElement = Pair(newElementFirst, newElementSecond)
                    if (!been.containsKey(newElement)) {
                        queue.add(newElement)
                        been[newElement] = true
                        nodes[newElement] = Node()
                    }
                }
            }
        }
    }