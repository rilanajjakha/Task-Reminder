package com.example.taskreminder

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.taskreminder.databinding.ActivitySecondBinding
import com.example.taskreminder.databinding.DialogConfirmBinding
import java.util.Calendar

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var title: String? = null
    private var repeat: String? = null
    private var date: String? = null
    private var time: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Spinner Repeat
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.repeat_option,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRepeat.adapter = adapter
        binding.spinnerRepeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: android.view.View?, position: Int, id: Long) {
                repeat = parentView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                repeat = "Once" // Default value
            }
        }

        // Date Picker
        binding.editTextDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, year1, monthOfYear, dayOfMonth ->
                date = "$dayOfMonth/${monthOfYear + 1}/$year1"
                binding.editTextDatePicker.setText(date)
            }, year, month, day)
            datePickerDialog.show()
        }

        // Time Picker
        binding.timePicker.setOnTimeChangedListener { _: TimePicker, hourOfDay: Int, minute: Int ->
            time = String.format("%02d:%02d", hourOfDay, minute)
        }

        // button add task
        binding.buttonAddTask.setOnClickListener {
            title = binding.editTextTitle.text.toString()

            // validasi
            if (title.isNullOrEmpty() || date == null || time == null) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show()
            } else {
                showCustomConfirmationDialog()
            }
        }
    }

    // confirm dialog
    private fun showCustomConfirmationDialog() {
        val dialogBinding = DialogConfirmBinding.inflate(layoutInflater)

        // Buat dialog custom
        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.btnYes.setOnClickListener {
            alertDialog.dismiss()
            moveToThirdActivity()
        }

        dialogBinding.btnNo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun moveToThirdActivity() {
        val intent = Intent(this, ThirdActivity::class.java)
        intent.putExtra("EXTRA_TITLE", title)
        intent.putExtra("EXTRA_REPEAT", repeat)
        intent.putExtra("EXTRA_DATE", date)
        intent.putExtra("EXTRA_TIME", time)
        startActivity(intent)
    }
}