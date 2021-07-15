package com.emdev.kotlinmessenger.registerlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emdev.kotlinmessenger.R
import com.emdev.kotlinmessenger.messages.LatestMessageActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)

            //Toast.makeText(this, "Ingreso con el correo: $email, pass: $pass", Toast.LENGTH_SHORT ).show()
            val intent = Intent(this, LatestMessageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        olvideMiClave.setOnClickListener {
            Log.d("LOGINACTIVITY", "Ir a olvide mi clave")
        }
    }
}