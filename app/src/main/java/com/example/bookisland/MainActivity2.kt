package com.example.bookisland

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btnToAc3 = findViewById<Button>(R.id.btnToSearch)
        btnToAc3.setOnClickListener{
            val Intent = Intent(this, FragmentMenu::class.java)
            startActivity(Intent)
        }

    }
}