import kotlin.math.roundToInt
import java.io.File
const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 10
val a = 10
val patronList = mutableListOf("Elly", "Mordoc", "Sophi")
val menuList = File("data/tavern-menu-items.txt").readText().split("\n")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggings")
val uniquePatrons = mutableSetOf<String>()
fun main() {
    (0..9).forEach {
        var first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    println(uniquePatrons)
    var orderCount = 0
    while (orderCount<=9) {
        placeOrder(uniquePatrons.shuffled().first(),
        menuList.shuffled().first())
        orderCount++
    }
}
fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = (playerGold + (playerSilver/100.0) )
    println("Purchase items for $price")
    println("Total purse: $totalPurse")
    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

private fun displayBalance(){
    println("Player purse balance: Gold $playerGold Silver $playerSilver ")
}
private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aAeiou]")) {
        when (it.value) {
            "a" -> "4"
            "A" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }
private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name($type) for $price"
    println(message)
   // performPurchase(price.toDouble())
    val phrase = if(name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name")}"
    }else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}

