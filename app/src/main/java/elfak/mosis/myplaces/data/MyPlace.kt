package elfak.mosis.myplaces.elfak.mosis.myplaces.data

data class MyPlace(var name:String, var description:String) {
    override fun toString(): String = name
}
