package org.example.student.dotsboxgame

import uk.ac.bournemouth.ap.dotsandboxeslib.*
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.Matrix
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.MutableMatrix
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.MutableSparseMatrix
import uk.ac.bournemouth.ap.dotsandboxeslib.matrix.SparseMatrix
import kotlin.random.Random


class StudentDotsBoxGame : AbstractDotsAndBoxesGame() {
    //override val players: List<Player> = TODO("You will need to get players from your constructor")
    val players: IntArray = intArrayOf(1, 2);
    val scores: IntArray = intArrayOf(0, 0);
    var currentPlayer: Int = 0;

    // NOTE: you may want to me more specific in the box type if you use that type in your class
    var boxes = Array(3) {Array(3) {0} }

    var lines = Array(4) {Array(4) {0} }

    override val isFinished: Boolean
        get() = TODO("Provide this getter. Note you can make it a var to do so")

    override fun playComputerTurns() {
        TODO("Not yet implemented")
    }

    /*override fun playComputerTurns() {
        var current = currentPlayer
        while (current == 2 && ! isFinished) {
            //current.makeMove(this)
            current = currentPlayer
        }
    }*/

    fun makeMove(x: Int, y: Int) {
        this@StudentDotsBoxGame.StudentLine(x, y);

    }

    fun gameChange(boxX: Int, boxY: Int) {
        if(currentPlayer > 0) {
            currentPlayer = 0;
        }
        else {
            currentPlayer = 1;
        }
    }

    /**
     * This is an inner class as it needs to refer to the game to be able to look up the correct
     * lines and boxes. Alternatively you can have a game property that does the same thing without
     * it being an inner class.
     */
    inner class StudentLine(val lineX: Int, val lineY: Int) {
        // : AbstractLine(lineX, lineY) {
        private val isDrawn: Boolean
            get() {
                if (lines.get(lineX).get(lineY) > 0) {
                    return true
                }
                return false


            }


        /*override val adjacentBoxes: Pair<StudentBox?, StudentBox?>
            get() {
                TODO("You need to look up the correct boxes for this to work")
            }
        */
        fun drawLine() {
            // TODO("Implement the logic for a player drawing a line. Don't forget to inform the listeners (fireGameChange, fireGameOver)")
            // NOTE read the documentation in the interface, you must also update the current player.
            if (!this@StudentLine.isDrawn) {
                this@StudentDotsBoxGame.lines[lineX][lineY] = players[currentPlayer];
                val linesArr = this@StudentDotsBoxGame.lines
                if (linesArr[lineX][lineY - 1] and linesArr[lineX][lineY - 2] and linesArr[lineX + 1][lineY - 1] == 1) {
                    this@StudentDotsBoxGame.boxes[lineX][(lineY / 2) - 1] = players[currentPlayer];
                    scores[currentPlayer] += 1;
                    gameChange(lineX, (lineY/2-1))
                }
                if (linesArr[lineX][lineY + 2] and linesArr[lineX + 1][lineY + 1] and linesArr[lineX][lineY + 1] == 1) {
                    this@StudentDotsBoxGame.boxes[lineX][(lineY / 2 + 1)] = players[currentPlayer];
                    scores[currentPlayer] += 1;
                    gameChange(lineX, (lineY/2+1))
                }
            }
            gameChange(-1, -1);
        }
    }

    inner class StudentBox(val boxX: Int, val boxY: Int) {

        val owningPlayer: Int
            get() = this@StudentDotsBoxGame.boxes[boxX][boxY];

                /**
                 * This must be lazy or a getter, otherwise there is a chicken/egg problem with the boxes
                 */
                /* override val boundingLines: Iterable<DotsAndBoxesGame.Line>
                     get() = TODO("Look up the correct lines from the game outer class")
                 */

    }
}