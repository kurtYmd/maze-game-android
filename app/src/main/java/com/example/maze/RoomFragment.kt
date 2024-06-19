package com.example.maze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment

class RoomFragment : Fragment() {
    private var currentRow: Int = 0
    private var currentCol: Int = 0
    private lateinit var maze: Array<IntArray>

    companion object {
        private const val CURRENT_ROW = "current_row"
        private const val CURRENT_COL = "current_col"
        private const val MAZE = "maze"

        fun newInstance(currentRow: Int, currentCol: Int, maze: Array<IntArray>): RoomFragment {
            val fragment = RoomFragment()
            val args = Bundle()
            args.putInt(CURRENT_ROW, currentRow)
            args.putInt(CURRENT_COL, currentCol)
            args.putSerializable(MAZE, maze)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_room, container, false)
        currentRow = arguments?.getInt(CURRENT_ROW) ?: 0
        currentCol = arguments?.getInt(CURRENT_COL) ?: 0
        maze = arguments?.getSerializable(MAZE) as Array<IntArray>

        displayRoom(view)
        setupNavigation(view)
        return view
    }

    private fun setupNavigation(view: View) {
        val mazeActivity = activity as MazeActivity
        val roomCode = maze[currentRow][currentCol]

        view.findViewById<Button>(R.id.button_up).apply {
            isEnabled = roomCode and 4 != 0
            setOnClickListener {
                mazeActivity.moveUp()
            }
        }
        view.findViewById<Button>(R.id.button_down).apply {
            isEnabled = roomCode and 8 != 0
            setOnClickListener {
                mazeActivity.moveDown()
            }
        }
        view.findViewById<Button>(R.id.button_left).apply {
            isEnabled = roomCode and 1 != 0
            setOnClickListener {
                mazeActivity.moveLeft()
            }
        }
        view.findViewById<Button>(R.id.button_right).apply {
            isEnabled = roomCode and 2 != 0
            setOnClickListener {
                mazeActivity.moveRight()
            }
        }
    }

    private fun displayRoom(view: View) {
        val gridView = view.findViewById<GridView>(R.id.maze_grid)
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1)
        val mazeArray = arrayListOf<String>()

        for (i in maze.indices) {
            for (j in maze[i].indices) {
                val cellText = if (i == currentRow && j == currentCol) "🟢" else maze[i][j].toString()
                mazeArray.add(cellText)
            }
        }

        adapter.clear()
        adapter.addAll(mazeArray)
        gridView.adapter = adapter
    }
}
