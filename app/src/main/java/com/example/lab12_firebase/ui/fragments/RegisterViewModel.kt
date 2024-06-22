package com.example.lab12_firebase.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lab12_firebase.database.CustomerEntity
import com.example.lab12_firebase.database.CustomerRepository

class RegisterViewModel(application: Application)
    : AndroidViewModel(application) {
    private var repository = CustomerRepository(application)

    fun saveCustomer(customerEntity: CustomerEntity){
        repository.insert(customerEntity)
    }
}