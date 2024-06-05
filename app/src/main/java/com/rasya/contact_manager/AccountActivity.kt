package com.rasya.contact_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rasya.contact_manager.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {
    val binding = ActivityAccountBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}