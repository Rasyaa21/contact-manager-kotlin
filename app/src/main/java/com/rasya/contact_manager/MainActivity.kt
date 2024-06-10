package com.rasya.contact_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.replace
import com.rasya.contact_manager.databinding.ActivityMainBinding
import com.rasya.contact_manager.fragments.accountFragment
import com.rasya.contact_manager.fragments.contactFragment
import com.rasya.contact_manager.fragments.homeFragment


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")

        val homeFragment = homeFragment()
        val contactFragment = contactFragment()
        val accountFragment = accountFragment()

        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.HomeActivity->setCurrentFragment(homeFragment)
                R.id.ContactActivity->setCurrentFragment(contactFragment)
                R.id.AccountActivity->setCurrentFragment(accountFragment)
            }
            true
        }

    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

}



