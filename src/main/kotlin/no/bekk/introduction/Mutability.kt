package no.bekk.introduction

class Person(val name: String)

fun main() {

    val person = Person("Sondre")
    // person.name = "Sondre"

    /**
    val sondre = gaute.copy(name = "Sondre")
    println(sondre)
    // */

// I standardbiblioteket til kotlin skiller man på datastrukturer som kan endres direkte (muterbare), og de hvor man ikke kan det
// feks finnes det både `List<T>` og `MutableList<T>` som begge er generiske lister, men `List` implementerer ikke funksjoner som `add` og `remove`.
    listOf(1, 2, 3)
    mutableListOf(1)
    val xs = listOf(1, 2, 3)
    xs.add(4) // Error: add is not a member of List

}
