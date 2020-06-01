class SubgroupG(val element: Int,
                val formings: MutableList<Permutation>,
                val tree: SchreierTree = SchreierTree(element, formings)) {
}