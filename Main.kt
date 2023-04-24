package machine

fun showMachineState (coffeeMachineStartValue: MutableList<UInt>) {
    println()
    println("The coffee machine has:")
    println("${coffeeMachineStartValue[0]} ml of water")
    println("${coffeeMachineStartValue[1]} ml of milk")
    println("${coffeeMachineStartValue[2]} g of coffee beans")
    println("${coffeeMachineStartValue[4]} disposable cups")
    println("$${coffeeMachineStartValue[3]} of money")
    println()
}

fun buy(coffeeMachineStartValue: MutableList<UInt>, espressoUseValue: MutableList<UInt>,
        lateUseValue: MutableList<UInt>, cappuccinoUseValue: MutableList<UInt>) {
    val coffeeMachineHelpValue = mutableListOf<UInt>()
    var noEnough = 5
    println()
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    val coffeeChoose: MutableList<UInt> = when(readln()) {
        "1" -> espressoUseValue
        "2" -> lateUseValue
        "3" -> cappuccinoUseValue
        "back" -> {
            println()
            return
        }
        else -> coffeeMachineStartValue
    }
    if (coffeeMachineStartValue != coffeeChoose) {
    for (i in 0..4) {
        if (i == 3) continue
        if (coffeeMachineStartValue[i] < coffeeChoose[i]) {
            noEnough = i
            break
        } else {
            val coffeeMachineNewValue = mutableListOf(
                coffeeMachineStartValue[0] - coffeeChoose[0],
                coffeeMachineStartValue[1] - coffeeChoose[1], coffeeMachineStartValue[2] - coffeeChoose[2],
                coffeeMachineStartValue[3] + coffeeChoose[3], coffeeMachineStartValue[4] - coffeeChoose[4]
            )
            coffeeMachineHelpValue.addAll(coffeeMachineNewValue)
            coffeeMachineStartValue.clear()
            coffeeMachineStartValue.addAll(coffeeMachineHelpValue)
            noEnough = 3
        }
    }
    }
    when(noEnough){
        0 -> println("Sorry, not enough water!")
        1 -> println("Sorry, not enough milk!")
        2 -> println("Sorry, not enough beans!")
        4 -> println("Sorry, not enough cups!")
        3 -> println("I have enough resources, making you a coffee!")
        5 -> println("Wrong Input")
    }
    println()

}

fun fill(coffeeMachineStartValue: MutableList<UInt>) {
    println()
    println("Write how many ml of water you want to add:")
    val waterAdd = readln()
    println("Write how many ml of milk you want to add:")
    val milkAdd = readln()
    println("Write how many grams of coffee beans you want to add:")
    val beansAdd = readln()
    println("Write how many disposable cups you want to add")
    val cupsAdd = readln()
    try { val coffeeMachineNewValue = mutableListOf(
        coffeeMachineStartValue[0] + waterAdd.toUInt(),
        coffeeMachineStartValue[1] + milkAdd.toUInt(), coffeeMachineStartValue[2] + beansAdd.toUInt(),
        coffeeMachineStartValue[3], coffeeMachineStartValue[4] + cupsAdd.toUInt())
        coffeeMachineStartValue.clear()
        coffeeMachineStartValue.addAll(coffeeMachineNewValue)
    } catch (e: NumberFormatException) { println("Wrong Input!") }
    println()
}

fun take(coffeeMachineStartValue: MutableList<UInt>) {
    println("I gave you $${coffeeMachineStartValue[3]}")
    coffeeMachineStartValue[3] = 0u
}

fun main() {
    val espressoUseValue = mutableListOf(250u, 0u, 16u, 4u, 1u) //water, milk, beans, cost
    val lateUseValue = mutableListOf(350u, 75u, 20u, 7u, 1u) //water, milk, beans, cost
    val cappuccinoUseValue =  mutableListOf(200u, 100u, 12u, 6u, 1u) //water, milk, beans, cost
    val coffeeMachineStartValue = mutableListOf(400u, 540u, 120u, 550u, 9u) //water, milk, beans, cost, cup

    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        when (readln()) {
            "buy" -> buy(coffeeMachineStartValue, espressoUseValue, lateUseValue, cappuccinoUseValue)
            "fill" -> fill(coffeeMachineStartValue)
            "take" -> take(coffeeMachineStartValue)
            "remaining" -> showMachineState(coffeeMachineStartValue)
            "exit" -> break
            else -> println("Wrong Input!")
        }
    }

}
