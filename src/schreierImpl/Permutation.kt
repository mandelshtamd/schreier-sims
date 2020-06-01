class Permutation(
    val permutation: MutableList<Int>,
    var decomposition: MutableList<Int> = ArrayList()) {

    fun reverse(): Permutation {
        val resultPermutationList = MutableList<Int>(size) { 0 }
        for (i in 0 until size)
            resultPermutationList[permutation[i]] = i
        val resultPermutation = Permutation(resultPermutationList)
        resultPermutation.decomposition.clear()
        for (i in 0 until decomposition.size) {
            resultPermutation.decomposition.add(-(decomposition[decomposition.size - i - 1]))
        }
        return resultPermutation
    }

    fun multiplyFromRight(otherPermutation: Permutation): Permutation {
        val resultPermutationList = MutableList<Int>(size) { 0 }
        for (i in 0 until size)
            resultPermutationList[i] = permutation[otherPermutation.permutation[i]]
        val resultPermutation = Permutation(resultPermutationList)
        resultPermutation.decomposition = (decomposition + otherPermutation.decomposition) as MutableList<Int>
        return resultPermutation

    }

     // куда элемент переходит при перестановке
    fun appliedToElement(node: Int): Int {
        return permutation[node]
    }

    fun equals(other: Permutation): Boolean {
        for (i in 0 until size) {
            if (other.permutation[i] != permutation[i])
                return false
        }
        return true
    }
}