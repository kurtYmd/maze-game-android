package com.example.maze

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class GameOverDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Congratulations! You have reached the end of the maze!")
                .setPositiveButton("Restart") { _, _ ->
                    (activity as MazeActivity).restartGame()
                }
                .setNegativeButton("Main Menu") { _, _ ->
                    activity?.finish()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
