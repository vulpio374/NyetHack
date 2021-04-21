import java.text.SimpleDateFormat

fun main() {
    runSimulation()
}
fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println("Введите имя персонажа")
    val playerName = readLine()
    println(playerName?.let { greetingFunction(it) })
}
fun configureGreetingFunction(): (String) -> String {
    val structureType = "hospitals"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2018
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage $playerName (copyright $currentYear)"

    }
}