import Game.Companion.board

const val UP    = '^'
const val RIGHT = '>'
const val DOWN  = 'v'
const val LEFT  = '<'

class Player(
    var position: Pair<Int, Int>,
    var direction: Char,
    var moves: Int = 0
) {

    fun move() {
        board.movePlayer(this)
        ++moves
    }

    fun turnRight() {
        when (direction) {
            UP    -> direction = RIGHT
            RIGHT -> direction = DOWN
            DOWN  -> direction = LEFT
            LEFT  -> direction = UP
        }
    }
}