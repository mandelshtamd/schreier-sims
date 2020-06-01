object GeneratorsHolder {
    //будем в этом классе хранить образующие для нашей группы
    val generatorsHolder: HashMap<Permutation, Int> = HashMap()

    fun add(permutation: Permutation, pos: Int) {
        generatorsHolder[permutation] = pos
    }
}