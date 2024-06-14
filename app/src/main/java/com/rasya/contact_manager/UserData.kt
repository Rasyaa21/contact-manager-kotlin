package com.rasya.contact_manager

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserData(
    val name: String? = null,
    val phoneNumber : String? = null,
    val email: String? = null
)
