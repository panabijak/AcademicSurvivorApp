package com.example.calendar

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimetableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable) // Make sure this matches your XML file name

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Set click listeners for each TextView
        setClickListener(R.id.monday_8)
        setClickListener(R.id.tuesday_8)
        setClickListener(R.id.wednesday_8)
        setClickListener(R.id.thursday_8)
        setClickListener(R.id.friday_8)

        // Repeat for other time slots (9 AM to 6 PM)
        for (hour in 9..18) {
            setClickListener(resources.getIdentifier("monday_$hour", "id", packageName))
            setClickListener(resources.getIdentifier("tuesday_$hour", "id", packageName))
            setClickListener(resources.getIdentifier("wednesday_$hour", "id", packageName))
            setClickListener(resources.getIdentifier("thursday_$hour", "id", packageName))
            setClickListener(resources.getIdentifier("friday_$hour", "id", packageName))
        }
    }

    private fun setClickListener(textViewId: Int) {
        val textView = findViewById<TextView>(textViewId)
        textView.setOnClickListener {
            showEditSubjectDialog(textView)
        }
    }

    private fun showEditSubjectDialog(textView: TextView) {
        val currentSubject = textView.text.toString() // Get the current subject text
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Subject")

        // Set up the input
        val input = EditText(this)
        input.setText(currentSubject) // Set the current subject as the input text
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, _ ->
            textView.text = input.text.toString() // Update the TextView with the new subject
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel() // Dismiss the dialog without making changes
        }

        builder.show() // Show the dialog
    }
}