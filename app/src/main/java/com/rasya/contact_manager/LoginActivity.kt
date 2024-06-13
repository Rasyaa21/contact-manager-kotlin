package com.rasya.contact_manager

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.WindowCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Tag
import com.google.firebase.firestore.firestore
import com.rasya.contact_manager.databinding.ActivityLoginBinding

private lateinit var firebaseAuth: FirebaseAuth;

class LoginActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val db = Firebase.firestore

        binding.btRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        binding.btLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login Successfull", LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        val uid = firebaseAuth.uid.toString()
                        val user = hashMapOf(
                            "uid" to uid,
                            "email" to email
                        )
                        db.collection("users").document(uid)
                            .set(user)
                            .addOnSuccessListener {documentReferences ->
                            Log.d(TAG, "id added : $uid")
                            }
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Email Already Exist", LENGTH_LONG).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "please fill the form", LENGTH_LONG).show()
            }
        }
    }
}