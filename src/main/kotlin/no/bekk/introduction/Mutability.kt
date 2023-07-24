package no.bekk.introduction

// Feltene i `Person` kan ikke endres fordi de er deklarert med `val`
data class Person(val name: String, val age: Int)

fun main() {

    // Oppgave 1: Kommenter inn linjen under, og undersøk feilen du får.
    // Bruk så `copy` for å lage et nytt person objekt med endret navn.
    val sondre = Person("Sondre", 26)
    // sondre.name = "Gaute"

    // I standardbiblioteket til kotlin skiller man på datastrukturer som kan endres direkte (muterbare), og de hvor man ikke kan det
    // feks finnes det både `List<T>` og `MutableList<T>` som begge er generiske lister, men `List` implementerer ikke funksjoner som `add` og `remove`.
    // Oppgave 2: Kommenter inn linjen under, og fiks feilen ved å bruke en muterbar liste
    val numbers = listOf(1, 2, 3)
    // numbers.add(4)

}
