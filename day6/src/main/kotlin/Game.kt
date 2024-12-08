import java.io.File

class Game {
    companion object {

        init {
            println("\nInitializing Game ...\n")
        }

        val board = Board(File(FILENAME).readLines().map { it.toCharArray() })
        val player = board.getPlayerPosition()
        var gameNotComleted = true

        fun start() {
            gameLoop()
        }

        private fun gameLoop() {
            while (gameNotComleted) {
                player.move()
            }
        }

        fun end(count: Int) {
            board.print()
            println("A WINNER IS YOU")
            println("Player movements: ${player.moves}")
            println("Number of distinct positions visited: $count")
            gameNotComleted = false
        }
    }

}
