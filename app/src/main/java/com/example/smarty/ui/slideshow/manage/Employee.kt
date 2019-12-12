package com.example.smarty.ui.slideshow.manage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smarty.R
import kotlinx.android.synthetic.main.activity_employee.*

class Employee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        val text = textViewEmploy.text.toString()
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
