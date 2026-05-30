package com

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gestiondesnotes.R
import com.google.android.material.button.MaterialButton
import kotlin.math.log

class CreateCompte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_compte)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val register= findViewById<MaterialButton>(R.id.register)
        register.setOnClickListener {
            val intent= Intent(this, Inscription::class.java)
            startActivity(intent)
        }
        val login= findViewById<MaterialButton>(R.id.login)
        login.setOnClickListener {
            val intent= Intent(this, Connexion::class.java)
            startActivity(intent)
        }
    }
}