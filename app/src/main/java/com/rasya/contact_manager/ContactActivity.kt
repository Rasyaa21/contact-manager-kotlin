package com.rasya.contact_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rasya.contact_manager.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {

    val binding = ActivityContactBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}