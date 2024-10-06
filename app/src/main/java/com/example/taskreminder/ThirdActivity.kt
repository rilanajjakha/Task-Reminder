package com.example.taskreminder

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskreminder.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ambil data dari intent
        val title  = intent.getStringExtra("EXTRA_TITLE")
        val repeat = intent.getStringExtra("EXTRA_REPEAT")
        val date = intent.getStringExtra("EXTRA_DATE")
        val time = intent.getStringExtra("EXTRA_TIME")

        // menampilkan data
        binding.title.text = title
        binding.textViewDatePicker.text = date
        binding.textViewTimePicker.text = time
        binding.textViewRepeat.text = repeat

        // button add task
        binding.buttonAddTask.setOnClickListener {
            val returnIntent = Intent(this@ThirdActivity, SecondActivity::class.java)
            startActivity(returnIntent)
        }
    }
}