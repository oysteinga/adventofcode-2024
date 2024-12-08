
private const val OPEN = '.'
private const val OBSTACLE = '#'
private const val VISITED = 'X'

class Board(
    private var board: List<CharArray>,
)
{
    //---------------------------------------
    // PUBLIC FUNTIONS
    //---------------------------------------
    fun getPlayerPosition(): Player{
        for (y in board.lastIndex downTo 0) {
            for (x in 0 until board[y].size) {
                printPosition(x, y)
                if (board[y][x] == '^' || board[y][x] == '>' || board[y][x] == 'v' || board[y][x] == '<') {
                    println()
                    return Player(Pair(x, y), board[y][x])
                }
            }
        }
        return Player(Pair(-1, -1), '^')
    }

    fun movePlayer(player: Player) {

        when (player.direction) {
            UP    ->    moveUp(player)
            RIGHT -> moveRight(player)
            DOWN  ->  moveDown(player)
            LEFT  ->  moveLeft(player)
        }
    }

    //---------------------------------------
    // PRIVATE MOVEMENT FUNCTIONS
    //---------------------------------------
    private fun moveLeft(player: Player) {
        val (x, y) = player.position
        moveInDirection(x-1, y, player)
    }

    private fun moveDown(player: Player) {
        val (x, y) = player.position
        moveInDirection(x, y+1, player)
    }

    private fun moveRight(player: Player) {
        val (x, y) = player.position
        moveInDirection(x+1, y, player)
    }

    private fun moveUp(player: Player) {
        val (x, y) = player.position
        moveInDirection(x, y-1, player)
    }

    private fun moveInDirection(xMove: Int, yMove: Int, player: Player) {
        if (playerLeavesMap(xMove, yMove)) {
            Game.end(distinctPositionsVisited())
        } else if (board[yMove][xMove] == OPEN || board[yMove][xMove] == VISITED) {
            board[yMove][xMove] = VISITED
            player.position = Pair(xMove, yMove)
        } else if (board[yMove][xMove] == OBSTACLE)
            player.turnRight()
    }

    //---------------------------------------
    // OTHER NECESSARY PRIVATE FUNCTIONS
    //---------------------------------------
    private fun playerLeavesMap(x: Int, y: Int): Boolean {
        return (y >= board.size || y < 0 || x < 0 || x >= board[y].size)
    }

    private fun distinctPositionsVisited(): Int {
        // Start count at 1 to include player's starting position
        var distinctPositionsVisited = 1
        for (y in board.lastIndex downTo 0) {
            for (x in 0 until board[y].size) {
//                printPosition(x, y)
                if (board[y][x] == 'X')
                    distinctPositionsVisited++
            }
        }
        return distinctPositionsVisited
    }

    //---------------------------------------
    // PRINT FUNCTIONS
    //---------------------------------------
    fun print() {
        for (row in board) {
            println(row)
        }
        println()
    }

    private fun printPosition(x: Int, y: Int, sleepTime: Long = 0) {
        if (x % 130 == 0) {
            println()
        }
        print(board[y][x])
        Thread.sleep(sleepTime)
    }
}