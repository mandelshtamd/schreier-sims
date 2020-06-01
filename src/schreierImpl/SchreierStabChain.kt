class SchreierStabChain(
        private val base: MutableList<Int>,
        generators: MutableList<Permutation>,
        private val stabChain: MutableList<SubgroupG> = ArrayList())  {
    private val id = Permutation(MutableList(size) { it })

    init {
        var permutations = generators
        for (b in 0 until base.size) {

            val root = base[b]
            val tree = SchreierTree(root, permutations)

            val newG = SubgroupG(root, permutations, tree)
            stabChain.add(newG)
            val temp: MutableList<Permutation> = ArrayList()

            for (y in 0 until size) {
                if (tree.nodes.containsKey(y)) {
                    val restored = restorePermutation(root, y, tree)
                    for (s in newG.formings) {
                        var myS = s
                        myS = myS.multiplyFromRight(restored)

                        val element = s.appliedToElement(y)
                        var ToSY = restorePermutation(root, element, tree)
                        var reverseToSY = ToSY.reverse()

                        reverseToSY = reverseToSY.multiplyFromRight(myS)

                        temp.add(reverseToSY)
                    }
                }
            }
            permutations = normalize(temp)
        }
    }

    private fun normalize(G: MutableList<Permutation>): MutableList<Permutation> {
        val permutations: MutableList<Permutation> = ArrayList()
        val been: HashMap<Pair<Int, Int>, Permutation> = HashMap()
        for (g in G) {
            var myG = g
            for (x in 0 until size) {
                if (myG.appliedToElement(x) != x) {
                    if (been.containsKey(Pair(x, myG.appliedToElement(x)))) {
                        val newMyS = myG
                        myG = myG.reverse()
                        myG = myG.multiplyFromRight(been[Pair(x, newMyS.appliedToElement(x))]!!)
                    } else {
                        been[Pair(x, myG.appliedToElement(x))] = myG
                        permutations.add(myG)
                        break
                    }
                }
            }
        }
        return permutations
    }


    private fun restorePermutation(root: Int, to: Int, tree: SchreierTree): Permutation {
        var pos = to
        var permutation = id

        while (pos != root) {
            permutation = permutation.multiplyFromRight(tree.nodes[pos]!!.permutation)
            pos = tree.nodes[pos]!!.parent
        }
        return permutation
    }

    //считает порядок группы
    fun groupOrder(): Long {
        var result: Long = 1
        stabChain.forEach {
            result *= it.tree.nodes.size
        }
        return result
    }

    //лежит ли в группе перестановке
    fun belongsTo(permutation: Permutation): Boolean {
        var myPermutation = permutation
        for (i in 0 until base.size) {
            val v = myPermutation.appliedToElement(base[i])
            if (!stabChain[i].tree.nodes.containsKey(v)) return false
            var restoredPermutation = restorePermutation(base[i], v, stabChain[i].tree)
            restoredPermutation = restoredPermutation.reverse()
            myPermutation = restoredPermutation.multiplyFromRight(myPermutation)
        }
        if (myPermutation.equals(id)) return true
        return false
    }
}