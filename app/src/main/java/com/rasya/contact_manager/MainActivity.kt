package com.rasya.contact_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rasya.contact_manager.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeActivity())

        val email = intent.getStringExtra("email")
        binding.tvEmail.text = email

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.HomeActivity -> replaceFragment(HomeActivity())
                R.id.ContactActivity -> replaceFragment(ContactActivity())
                R.id.AccountActivity -> replaceFragment(AccountActivity())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}