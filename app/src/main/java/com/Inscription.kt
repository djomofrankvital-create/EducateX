package com

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gestiondesnotes.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Inscription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inscription)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val retour= findViewById<ImageView>(R.id.retour)
        retour.setOnClickListener {
            val intent= Intent(this, CreateCompte::class.java)
            startActivity(intent)
        }
        val nom= findViewById<TextInputEditText>(R.id.nom)
        val adresse= findViewById<TextInputEditText>(R.id.adresse)
        val MotDePasse1= findViewById<TextInputEditText>(R.id.motdepasse)
        val MotDePasse2= findViewById<TextInputEditText>(R.id.confirmMotdepasse)
        val register= findViewById<MaterialButton>(R.id.register)

        register.setOnClickListener {
            val name= nom.text.toString().trim()
            val email= adresse.text.toString().trim()
            val password1= MotDePasse1.text.toString().trim()
            val password2= MotDePasse2.text.toString().trim()

            var EstValide= true
            if(name.isEmpty()){
                nom.error="Veuillez saisir votre nom"
                nom.requestFocus()
                EstValide=false
            }
            if(email.isEmpty()){
                adresse.error="Veuillez saisir votre Email"
                adresse.requestFocus()
                EstValide= false
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                adresse.error="Adresse email invalide"
                if(EstValide)
                    adresse.requestFocus()
                EstValide= false
            }
            if(password1.isEmpty() && password2.isEmpty()){
                MotDePasse1.error="Veuillez saisir un mot de passe"
                if (EstValide) MotDePasse1.requestFocus()
                EstValide=false
            }else if(password1.length<6 && password1!= password2){
                MotDePasse1.error="Le mot de passe doit contenir au moins 6 caractères"
                if(EstValide) MotDePasse1.requestFocus()
                EstValide= false
            }
            if(password1 != password2){
                MotDePasse1.error="Veuillez Mettre un mot de passe identique"
                MotDePasse1.requestFocus()
                EstValide= false
            }
            if (EstValide){
                Toast.makeText(this, "Inscription réussir", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, Principal::class.java)
                startActivity(intent)
            }
        }
    }
}