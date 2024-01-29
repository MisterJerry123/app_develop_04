package com.example.alomtest

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.alomtest.R

class Food_dialog(context: Context, private val dataTitle: String) : Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_dialog)
        val dialogTitle = findViewById<TextView>(R.id.dialogTitle)
        dialogTitle.text = dataTitle
    }
}