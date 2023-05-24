package no.bekk.introduction

data class BootcampCoach(
    val name: String,
    val avdeling: Avdeling,
)

enum class Avdeling {
    TEKNOLOGI, DESIGN, BMC
}

val coacher2023 = listOf<String>("Ingrid", "Sondre", "Gaute", "Vegard", "Kristian", "Vetle", "Ragnhild", "Preben", "Morten")
