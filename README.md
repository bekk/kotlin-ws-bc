# Introduksjon til Kotlin

Først og fremst, velkommen til Bootcamp og velkommen til Bekk! 🥳

Denne workshopen er delt inn i to deler: den første delen gir deg en generell introduksjon til noen viktige konsepter i Kotlin, før vi skal lage et spill i del to! Dersom du har vært borti Kotlin før, må du gjerne hoppe over del en. 

Og ikke glem, bruk coachene og kollegaene dine aktivt! Vi er her for å hjelpe 🚀

## Data classes

En `data class` er en klasse som kun er ment for å holde på data.
Når du definerer en dataklasse får du en del funksjonalitet gratis, som f.eks. `toString`, `equals`, `hashCode` og `copy`.

Oppgave:

Åpne filen i introduction som heter "DataClass" hvor det ligger det en klasse som heter `Konsulent`, og en main funksjon.
1. Kjør main funksjonen, og se hva som skjer.
2. Gjør `Konsulent` om til en `data class` og kjør main funksjonen igjen. Hva skjer nå, og hvorfor?

<details><summary> 🤠 Løsningsforslag</summary>

1. Nå fungerer `println` mye bedre, og vi får en fin utskrift av objektet vårt.
Det er fordi default implementasjonen for `toString` i `Any` (som alle klasser arver fra) er å skrive ut klassenavnet og en hashkode.
Når vi gjør `Konsulent` til en `data class` får vi en implementasjon av `toString` som skriver ut alle feltene i klassen.

2. `==` gjør nå en strukturell sammenlikning, og vi får `true` når vi sammenligner to konsulenter med samme navn.
By default er `==` bare en referanse-sammenlikning, og vi får `false` når vi sammenlikner to separate objekter selv om de har samme innhold.

Se mer: https://kotlinlang.org/docs/data-classes.html

</details>

## Mutable vs Immutable

I kotlin er man ofte opptatt `mutability` og `immutability`, eller "muterbarhet" og "ikke-muterbarhet", som referer til hvor vidt data kan endres etter den er opprettet.
Fordelen med å gjøre så mye som mulig `immutable` er at koden ofte blir mer lesbar, og lettere å debugge, fordi
man alltid kan resonere om en verdien til en variabel utifra hvordan den ble opprettet uten å tenke på om den har blitt endret av koden senere i programmet.
Når man jobber med ikke-muterbar data er måten man gjør oppdateringer på å bruke operasjoner som lager en kopi av dataen med de ønskede endringe.

Åpne filen `Mutability.kt` og løs oppgavene der.

<details><summary>Løsning på oppgave 1</summary>

```kotlin
val sondre = Person("Sondre")
val gaute = sondre.copy(name = "Gaute")
println(gaute) // -> Person(name=Gaute, age=26)
```

</details>

<details><summary>Løsning på oppgave 2</summary>

```kotlin
val numbers = mutableListOf(1, 2, 3)
numbers.add(4)
```

Det Å bruke ikke-muterbare lister blir enklere når
man er komfortabel med "Higher Order Functions" som vi skal se på senere i workshopen.

</details>

## Functions

Funksjoner i kotlin defineres med `fun`-nøkkelordet, og kan ha parametere og returverdier.
```kotlin
fun add(a: Int, b: Int): Int {
    return a + b
}
```
Man kan også gi et parameter en default verdi ved å skrive `= <verdi>` etter typen.

Oppgave:
Åpne `Funksjoner.kt` og legg til en funksjon på `BekkAnsatt`-klassen som printer "Hallo, name" + valgfri suffix, som har en default verdi. 

<details><summary>Løsning</summary>

```kotlin
fun greet(suffix: String = "!") {
    println("Hallo, $name $suffix")
}
```
*Løsningen bruker [`string templates`]( https://kotlinlang.org/docs/java-to-kotlin-idioms-strings.html#concatenate-strings ) for å sette sammen meldingen uten å bruke `+`*

</details>

## Lambda og bruken av it

Oppgave:
// Bruk .let til å gjøre ett eller annet

Løsning:
```
```


## Higher Order Functions
Store deler av det vi gjør som utviklere er å hente data, manipulere den og deretter bruke den videre i applikasjonene våre.
I Kotlin finnes det mange "higher order functions", altså ferdigskrevne hjelpefunksjoner, som gjør dette lettere enn i Java. 
Blant annet har vi funksjoner for å endre lister (map) eller filtrerer bort ting vi ikke trenger (filter). 

```
listeMedBekkKonsulenter.filter { it.name != "Ingrid" }
```

Oppgave:
1. Gå igjennom listen, finn navnene Morten og Ragnhild, og lag en ny liste hvor Morten er i BMC og Ragnhild er i design. 
2. Bruk den siste listen, og lag en egen liste for coachene som er i teknologi-avdelingen. 
3. Bruk coacher2023 listen, og summer opp hvor mange år de har jobbet til sammen.

<details><summary> 🤠 Løsningsforslag</summary>

```
val realCoacher2023 = coacher2023.map {
    when (it.name) {
        "Morten" -> it.copy(avdeling = Avdeling.BMC)
        "Ragnhild" -> it.copy(avdeling = Avdeling.DESIGN)
        else -> it
    }
}

val teknologiCoacher = realCoacher2023.filter {
    it.avdeling == Avdeling.TEKNOLOGI
}

coacher2023.sumOf { it.yearsInBekk }
```


</details>

## Extension Functions
Noen ganger har vi behov for spesialtilpasset funksjonalitet på en klasse som vi ikke har tilgang til å endre, for eksempel de innebygde klassene Int eller String.
Da kan du skrive en spesiell type funksjon som heter extension functions.

Funksjonen kan skrives på følgende måte:
``` 
fun <Klasse>.<funksjonsnavn>(<argumenter>): <return type> {
    // gjør noe
}
```

For å refere til instansen av klassen bruker vi `this`.

```kotlin
fun double(x: Int) {
    return x * 2
}
// æsj, kjedelig
val four = double(2)

fun Int.triple() {
    return this * 3
} 
// wow, kult 🤩
val six = 2.triple()
```

Extension functions kan også gjøre det mer leselig ved at man kan "chaine" funksjonskall. 

```kotlin
val unreadableMess = square(triple(increase(double(2))))

val ahhMuchBetter = 2
    .double()
    .increase()
    .triple()
    .square()
```

Dette kan bli spesielt nyttig når man håndterer null-verdier. 

```kotlin
// 🤮
val konsulentMaybe: Konsulent? = getKonsulent()
val konsulentInfo = if (konsulentMaybe) getInfo(konsulentMaybe) else null

// 😍
val konsulentInfo = getKonsulent()?.getInfo()
```

Oppgavene ligger i fila [ExtensionFunctions.kt](src/main/kotlin/no/bekk/introduction/ExtensionFunctions.kt)

<details>
<summary> 🤠 Løsningsforslag</summary>

```kotlin
// Oppgave 1
fun BootcampCoach.getInfo(): String {
    return "${this.name} er i avdeling ${this.avdeling} og har jobbet ${this.yearsInBekk} år i Bekk"
}

// Oppgave 2
fun BootcampCoach.hasWorkedLongerThan(otherCoach: BootcampCoach): Boolean {
    return this.yearsInBekk > otherCoach.yearsInBekk
}

// Oppgave 3
fun BootcampCoach.addYears(years: Int): BootcampCoach {
    return BootcampCoach(this.name, this.avdeling, this.yearsInBekk + years)
}

fun BootcampCoach.withAvdeling(avdeling: Avdeling): BootcampCoach {
    return BootcampCoach(this.name, avdeling, this.yearsInBekk)
}
// i main:
print(ingrid
    .addYears(1)
    .withAvdeling(Avdeling.BMC)
    .getInfo()
)
```

Du kan lese mer om extension functions i [den offisielle Kotlin-dokumentasjonen](https://kotlinlang.org/docs/extensions.html).
</details>

# Lage ditt første spill med LibGDX og Kotlin

Nå skal vi lage et spill! Du skal styre en firkant på skjermen. Firkanten skal
unngå andre firkanter som faller ned fra toppen av skjermen. Målet med oppgaven er å gjøre
deg litt kjent med et par viktige konsepter som du kan ta med deg inn i de litt mer kreative
oppgavene.

![Animasjon av spillet](docs/slides/bilder/game-animation.gif)

Skjelettet av koden er allerede skrevet - og består i hovedsak av tomme metoder som
det er opp til deg å implementere. Vi tar det stegvis, og når alle metodene er implementert ender du opp med et ferdig spill.

Koden vi skal jobbe finner du i filen [Main.kt](src/main/kotlin/org/veiset/libgdx/Main.kt).

## 1. Tegne en figur (spilleren) på skjermen.

Det første vi skal gjøre er å tegne noe på skjermen, og vi starter med selve spilleren.
Start med å implementere metoden `drawPlayer` slik at den tegner spillere som en firkant
på skjermen. Du kan fritt velge farge du ønsker å bruke.

Spilleren er definert som et `Rectangle` som ligger i variabelen `this.player`. Du kan
endre start posisjon og størrelse på spilleren ved å endre verdiene denne variabelen
initialiseres med.

Du kan bruke hjelpemetoden `drawRectangle` for å tegne et rektangel på skjermen.

## 2. Styre spilleren med tastaturet

Det neste vi skal gjøre er å sørge for at man kan styre spilleren med tastaturet.
Posisjonen til spilleren er definert som en `Vector2` på `player` variabelen.
Du kan velge å enten endre manipulere X og Y verdiene på denne direkte eller å bruke
hjelpemetoden `Rectangle.move` for å flytte spilleren. Veriabelen `delta` som man får
inn som argument er tiden siden forrige update, og kan brukes for å sørge for at man
får gjevn bevegelse uavhengig av update-rate. Dette kan man gjøre via å bruke den som
en faktor: `val moveDistance = movementSpeed * delta`.

For å sjekke tastatur-input kan man bruke funksjonen `Gdx.input.isKeyPressed`. F.eks.
kan man se om man holder nede `PIL OPP` med `Gdx.input.isKeyPressed(Input.Keys.UP)`.

Når man har fått spilleren til å bevege seg rundt på skjermen kan man legge til at
spilleren ikke skal få lov til å bevege seg utenfor skjermen. Bredde og høyde på skjermen
han man hente fra `EngineConfig.height` og `EngineConfig.width`.

Posisjonen til spilleren  er posisjonen til nedre venstre hjørne av rektangelet, og
det kan være fint å ta høyde for størrelsen på rektangelet når man skal holde spilleren
innenfor skjermen.

## 3. Lage bokser som spilleren må unngå

For at det skal bli et spill må det noe mer gameplay på plass. Så her er tanken at vi
skal ha noen bokser på starter på toppen av skjermen og "faller" nedover, og så er målet
å unngå å bli truffet av disse. For å få dette på plass må følgende ting implementeres:

 * `shouldSpawnNewBlocks`: Denne metoden skal returnere true om det skal lages flere blokker
    for spilleren å unngå.
 * `spawnNewBlock` Her skal den lage ny blokk(er). De nye blokkene skal ha en posisjon på
    toppen av skjermen, og et tilfeldig X-koordinat. Den nye blokken skal legges i listen
   `blocksToDodge`.
 * `drawAllBlocksToDodge`: Denne skal tegne alle blokkene som ligger i `this.blocksToDodge`.
    Dette kan gjøres ganske likt som tegning av spilleren. Men bruk gjerne en annen farge.
 * `handleMoveBlocks`: Her flytter man blokkene nedover på skjermen. I starten er det greit
    å bare flytte de med en konstant fart.


## 4. Kollisjonstesting

Om du starter main-metoden skal man når ting er implementert korrekt se at det faller ned noen
blokker fra toppen av skjermen. Men om styrer figuren din inn i en av de vil du se at det ikke
skjer noen ting. Det er som og blokkene ikke er der. Så det neste vi må få på plass er en
enkel kollisjonstest.

Om man ser ser i `update` metoden har den en sjekk på `playerIsColliding`. Hvis denne returnerer
true så kaller den `onGameLost()` som resetter spillet. Så målet nå er å implementere en sjekk i
`playerIsColliding` som sjekker om spilleren har kollidert med en av blokkene. `Rectangle.isCollidingWith`
kan brukes for å sjekke om 2 rektangler overlapper.


## 5. Ytelsesforbedring

Slik spillet er implementert nå vil det gjevnlig legges til nye blokker i listen `blocksToDodge`,
men de fjernes aldri. Over tid vil dette påvirke ytelsen både i form av at den bruker mer og mer minne
men også at den må gjøre operasjoner på fler og fler elementer som ikke lenger er relevante. Vi trenger
derfor en måte å rydde opp. 

I `update`-metoden så kaller vi `removeBlocksOutOfBounds()`. Tanken er at denne skal fjerne alle blokker
som er utenfor skjermen, og derfor ikke lenger er relevante for spillet.

## 6. Ekstra features?

Legg gjerne til litt ekstra features som du tenker kan være kult.

 * Vise hvor lenge man overlevde på en "score screen" når man taper?
 * Øke vanskelighetsgraden over tid (øke tempoet på brikkene som faller, spawne flere)
 * Blokker av ulike størrelser og med ulik fart?
 * Lag din egen figur!
 * Bytte ut firkantene med bilder
 * Se gjerne på ting i `examples`-mappen for inspirasjon til flere ting å leke seg med

Legg gjerne på kule ting du kommer på, eller gå videre til neste oppgave som er litt åpen.
