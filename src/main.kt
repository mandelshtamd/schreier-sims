import schreierImpl.TwoElementsTree
import kotlin.system.exitProcess

const val size = 54


fun firstTask(Permutations: MutableList<Permutation>) {
    val tree = SchreierTree(size - 1, Permutations)
    if (tree.nodes.size != size)
        println("Не все карты могут оказаться на последнем месте")
    else
        println("Любая карта может оказаться последней")
}

fun secondTask(Permutations: MutableList<Permutation>) {
    val tree = TwoElementsTree(Pair(size - 2, size - 1), Permutations)
    if (tree.nodes.size != size * (size - 1)) {
        println("Не любые две карты могут оказаться сверзу")
        exitProcess(0)
    }
    println("Любые две карты могут оказатсья сверху")
}


fun main() {
    val permutations: MutableList<Permutation> = ArrayList()

    //перестановка первого типа
    var listPermutation: MutableList<Int> = ArrayList()
    for (i in size / 3 until size / 3 * 2) {
        listPermutation.add(i)
    }
    for (i in 0 until size / 3) {
        listPermutation.add(i)
    }
    for (i in size / 3 * 2 until size) {
        listPermutation.add(i)
    }

    var permutation = Permutation(listPermutation)
    GeneratorsHolder.generatorsHolder[permutation] = 54
    permutation.decomposition.add(54)
    var reversed = permutation.reverse()
    permutation.decomposition.add(-54)
    permutations.add(permutation)
    permutations.add(reversed)

    //перестановка второго типа
    listPermutation = ArrayList()
    for (y in 2 until size) {
        listPermutation.add(y)
    }
    listPermutation.add(1)
    listPermutation.add(0)

    permutation = Permutation(listPermutation)
    GeneratorsHolder.generatorsHolder[permutation] = 54
    permutation.decomposition.add(54)

    reversed = permutation.reverse()
    permutation.decomposition.add(-54)
    permutations.add(permutation)
    permutations.add(reversed)


    firstTask(permutations)
    secondTask(permutations)
}