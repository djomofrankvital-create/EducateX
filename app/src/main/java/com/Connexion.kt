package com

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gestiondesnotes.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Connexion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_connexion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val principal= findViewById<MaterialButton>(R.id.menu)
        principal.setOnClickListener {
            val intent= Intent(this, Principal::class.java)
            startActivity(intent)
        }
        val adresse= findViewById<TextInputEditText>(R.id.adresse)
        val MotDePasse= findViewById<TextInputEditText>(R.id.MotDePasse)
        val menu = findViewById<MaterialButton>(R.id.menu)
        menu.setOnClickListener {
            val email= adresse.text.toString().trim()
            val password= MotDePasse.text.toString().trim()
            var EstValide= true
            if(email.isEmpty()){
                adresse.error="Veuillez saisir l'adresse"
                adresse.requestFocus()
                EstValide= false
            }

            if(password.isEmpty()){
                MotDePasse.error="Veuillez saisir un mot de passe"
                MotDePasse.requestFocus()
                EstValide= false
            }
            if(password.length<6){
                MotDePasse.error="Les mots de passe contiennent au moins 6 caractères"
            }

            if(EstValide){
                Toast.makeText(this, "Connexion en cours", Toast.LENGTH_SHORT).show()
                val intent=Intent(this, Principal::class.java)
                startActivity(intent)
            }
        }
    }
}