package com.example.lab12_firebase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab12_firebase.model.UserModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etFullName: EditText = findViewById(R.id.etFullName)
        val etCountry: EditText = findViewById(R.id.etCountry)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val btnSaveRegister: Button = findViewById(R.id.btnSaveRegister)
        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val collectionRef = db.collection("users")

        btnSaveRegister.setOnClickListener {
            val fullName = etFullName.text.toString()
            val country = etCountry.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid

                        val userModel = UserModel(email,fullName,country,uid)
                        collectionRef.add(userModel)
                            .addOnCompleteListener{
                                Snackbar.make(
                                    findViewById(android.R.id.content)
                                    , "Registro exitoso"
                                    , Snackbar.LENGTH_LONG
                                ).show()
                            }.addOnFailureListener{error ->
                                Snackbar.make(
                                    findViewById(android.R.id.content)
                                    , "Ocurrió un error al registrar el usuario: ${error.message}"
                                    , Snackbar.LENGTH_LONG
                                ).show()
                            }
                    }else{
                        Snackbar.make(
                            findViewById(android.R.id.content)
                            , "Ocurrió un error al registrar el usuario: ${task.exception.toString()}"
                            , Snackbar.LENGTH_LONG
                        ).show()
                    }
                }.addOnFailureListener (this){ error->
                    Log.e("ErrorFirebase",error.message.toString())

                }
        }

    }
}