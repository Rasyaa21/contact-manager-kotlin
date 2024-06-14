package com.rasya.contact_manager.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.core.Tag
import com.google.firebase.firestore.firestore
import com.rasya.contact_manager.MyAdapter
import com.rasya.contact_manager.R
import com.rasya.contact_manager.UserData
import com.rasya.contact_manager.databinding.FragmentContactBinding
import kotlin.math.log


private lateinit var firebaseAuth: FirebaseAuth
private lateinit var myAdapter: MyAdapter
private lateinit var userData : ArrayList<UserData>

class contactFragment : Fragment(), SearchView.OnQueryTextListener{

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.setHasFixedSize(true)

        userData = arrayListOf()
        myAdapter = MyAdapter(userData)
        binding.recyclerview.adapter = myAdapter

        val db = Firebase.firestore
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId).collection("contact")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                        val name = document.getString("name") ?: "No Name"
                        val email = document.getString("email") ?: "No Email"
                        val phoneNumber = document.getString("phone") ?: "No Phone Number"
                        userData.add(UserData(name, email, phoneNumber))
                    }
                    myAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting data", exception)
                }
        } else {
            Log.w(TAG, "User ID is null.")
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        searchList(newText)
        return true
    }

    fun searchList(text: String) {
        val search = ArrayList<UserData>()
        for (data in userData) {
            if (data.name?.toLowerCase()?.contains(text.toLowerCase()) == true) {
                search.add(data)
            }
        }
        myAdapter.searchData(search)
    }

}