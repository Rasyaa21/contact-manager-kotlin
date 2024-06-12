package com.rasya.contact_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.WindowCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import com.rasya.contact_manager.databinding.ActivityRegisterBinding


private lateinit var firebaseAuth: FirebaseAuth;

class RegisterActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        firebaseAuth = FirebaseAuth.getInstance()

        binding.btRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this, "Account Successfully Created", LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Email already exist", LENGTH_LONG).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"please fill the form", LENGTH_SHORT).show()
                }
            }

        binding.btLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
    }
}
