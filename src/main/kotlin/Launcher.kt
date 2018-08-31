import java.util.concurrent.ThreadLocalRandom


// Inputs
val numberOfBoxes = 100
val numberOfTurns = 10
val numberToOpen = 6

// Game State
val prizeIndex = ThreadLocalRandom.current().nextInt(0, numberOfBoxes)
val boxes = (0..(100-1)).map { LockBox(it, false, it == prizeIndex) }

fun main(args: Array<String>) {

    // choose an initial box
    boxes.randomFirst().select()

    (1..numberOfTurns).forEach { turn ->

        // open some boxes
        boxes.asSequence().filter { !it.hasPrize && !it.opened }.random(numberToOpen).forEach { it.open() }

        // ask player if they want to switch

    }
}

class LockBox(val index: Int, var opened: Boolean, val hasPrize: Boolean, var selected: Boolean = false) {

    fun open() {
        opened = true
    }
    fun select(): LockBox {
        boxes.forEach { it.selected = false }
        selected = true
        return this
    }
}