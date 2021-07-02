package com.emdev.kotlinmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegister.setOnClickListener {
            performaceRegister()

        }

        iHaveAnAccount.setOnClickListener {
            Log.d("MAINACTIVITY", "Ir al loginActivty")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun performaceRegister() {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        Log.d("MAINACTIVITY", "El email es: " + email)
        Log.d("MAINACTIVITY", "La clave es: " + password)

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
        } else if (password.length < 6){
            Toast.makeText(this, "La clave debe tener 6 caracteres como mínimo", Toast.LENGTH_SHORT).show()
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d("Main", "Succesfully created user with uid: ${it.result?.user?.uid}")
                }
                .addOnFailureListener{
                    Log.d("Main", "Failed to create user: ${it.message}")
                }
        }
    }
}