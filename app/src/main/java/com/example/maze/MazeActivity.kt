package com.example.maze

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maze.R

class MazeActivity : AppCompatActivity() {
    private val maze = arrayOf(
        intArrayOf(10, 8, 10, 9),
        intArrayOf(28, 1, 0, 12),
        intArrayOf(12, 10, 9, 13),
        intArrayOf(6, 5, 6, 5)
    )

    var currentRow = 0
    var currentCol = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze)

        showRoom()
    }

    private fun showRoom() {
        if (maze[currentRow][currentCol] == 0) {
            GameOverDialogFragment().show(supportFragmentManager, "GameOverDialog")
        } else {
            val roomFragment = RoomFragment.newInstance(currentRow, currentCol, maze)
            supportFragmentManager.beginTransaction()
                .replace(R.id.roomContainer, roomFragment)
                .commit()
        }
    }

    fun moveUp() {
        if (currentRow > 0) {
            currentRow--
            showRoom()
        }
    }

    fun moveDown() {
        if (currentRow < maze.size - 1) {
            currentRow++
            showRoom()
        }
    }

    fun moveLeft() {
        if (currentCol > 0) {
            currentCol--
            showRoom()
        }
    }

    fun moveRight() {
        if (currentCol < maze[0].size - 1) {
            currentCol++
            showRoom()
        }
    }

    fun restartGame() {
        currentRow = 0
        currentCol = 0
        showRoom()
    }
}
