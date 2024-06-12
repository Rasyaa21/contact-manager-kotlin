package com.rasya.contact_manager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.rasya.contact_manager.R
import com.rasya.contact_manager.databinding.FragmentHomeBinding


class homeFragment : Fragment() {

    //A nullable variable that holds the reference to the generated binding class
    private var _binding: FragmentHomeBinding? = null
    private var stringPlaceHolder : String = ""

    //A non-nullable property that ensures _binding is not null when accessed.
    //The double exclamation mark (!!) asserts that the value is non-null.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonGroup()
        showEtDialog()

    }

    private fun buttonGroup(){
        binding.btOne.setOnClickListener { addToStringPlaceholder("1") }
        binding.btTwo.setOnClickListener { addToStringPlaceholder("2") }
        binding.btThree.setOnClickListener { addToStringPlaceholder("3") }
        binding.btFour.setOnClickListener { addToStringPlaceholder("4") }
        binding.btFive.setOnClickListener { addToStringPlaceholder("5") }
        binding.btSix.setOnClickListener { addToStringPlaceholder("6") }
        binding.btSeven.setOnClickListener { addToStringPlaceholder("7") }
        binding.btEight.setOnClickListener { addToStringPlaceholder("8") }
        binding.btNine.setOnClickListener { addToStringPlaceholder("9") }
        binding.btZero.setOnClickListener { addToStringPlaceholder("0") }
        binding.btMinus.setOnClickListener { addToStringPlaceholder("-") }
        binding.btBack.setOnClickListener { deleteNumOnebyOne() }
        binding.btBack.setOnLongClickListener {
            deleteNumOnHold()
            true
        }
    }

    private fun addToStringPlaceholder(value : String){
        stringPlaceHolder += value
        binding.tvNumber.text = stringPlaceHolder
    }

    private fun deleteNumOnebyOne(){
        var getCurrenctText = binding.tvNumber.text.toString()
        if (getCurrenctText.isNotEmpty()){
            //susbtring digunakan untuk mendapatkan text dari index awal yang di inginkan hingga index akhirnya
            //disni kita ambil dari index pertama dan kita ambil panjang dari textnya dikurangi -1 maka
            //satu karakter yang di akhir akan dibuang
            stringPlaceHolder = getCurrenctText.substring(0, getCurrenctText.length - 1)
            binding.tvNumber.text = stringPlaceHolder
        }
    }

    private fun deleteNumOnHold(){
        var getCurrenctText = binding.tvNumber.text.toString()
        if (getCurrenctText.isNotEmpty()){
            stringPlaceHolder = ""
            binding.tvNumber.text = stringPlaceHolder
        }

    }

    private fun showEtDialog(){
        binding.btAdd.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text_layout, null)
            val name = dialogLayout.findViewById<EditText>(R.id.etName)
            var phoneNum = dialogLayout.findViewById<EditText>(R.id.etPhoneNum)
            val email = dialogLayout.findViewById<EditText>(R.id.etEmail)

            //untuk mengisi nilai dari phone num jika mengggunakan edit text
            phoneNum.setText(stringPlaceHolder)

            with(builder){
                setTitle("Add To Contact")
                setPositiveButton("Add"){dialog, which ->
                    //add to the database
                }
                setNegativeButton("Cancel"){dialog, which ->

                }

                setView(dialogLayout)
                show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}