package com.example.lab12_firebase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerDao {
    @Insert
    fun insert(customer: CustomerEntity)
    @Update
    fun update(customer: CustomerEntity)
    @Delete
    fun delete(customer: CustomerEntity)
    @Query("SELECT * FROM customer ORDER BY last_name")
    fun getCustomerOrderByLastName(): LiveData<List<CustomerEntity>>
}