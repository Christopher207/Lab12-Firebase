package com.example.lab12_firebase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?
){
    @PrimaryKey(true)
    @ColumnInfo("customer_id") var customerId:Int=0
}
