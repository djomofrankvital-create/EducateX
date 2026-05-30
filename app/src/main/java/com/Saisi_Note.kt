package com

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gestiondesnotes.R

class Saisi_Note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saisi_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val studentName= intent.getStringExtra("STUDENT_NAME") ?: "Eleve inconnu"
        val tvStudentName= findViewById<TextView>(R.id.tvNotesStudentName)
        val tvMoyenne= findViewById<TextView>(R.id.tvMoyenneGenerale)

        val etAlgo= findViewById<EditText>(R.id.etAlgo)
        val etBdd= findViewById<EditText>(R.id.etBaseDonne)
        val Maths= findViewById<EditText>(R.id.etProbabilite)
        val anglais= findViewById<EditText>(R.id.etAnglais)

        val btnCalculer= findViewById<Button>(R.id.btnCalculer)
        val btnAnnuler= findViewById<Button>(R.id.btnAnnuler)

        tvStudentName.text="Eleve: $studentName"

        btnCalculer.setOnClickListener {
            val algo= etAlgo.text.toString().toDoubleOrNull()?:0.0
            val bdd= etBdd.text.toString().toDoubleOrNull()?:0.0
            val maths= Maths.text.toString().toDoubleOrNull()?:0.0
            val Anglais= anglais.text.toString().toDoubleOrNull()?:0.0
            var EstVrai= true

            if(algo>20){
                etAlgo.error="Veuillez mettre une note inferieur à 20 !!"
                etAlgo.requestFocus()
                EstVrai=false
            }
            if(bdd>20){
                etBdd.error="Veuillez mettre une note inferieur à 20"
                etBdd.requestFocus()
                EstVrai= false
            }
            if(maths>20){
                Maths.error="Veuillez mettre une note inferieur à 20"
                Maths.requestFocus()
                EstVrai=false
            }
            if(Anglais>20){
                anglais.error="Veuillez mettre une note inferieur à 20"
                anglais.requestFocus()
                EstVrai= false
            }
            if(EstVrai){
                val moyenne= (algo + bdd + maths + Anglais)/4.0
                tvMoyenne.text= String.format("MOYENNE GENERALE: %.2f /20", moyenne)
                val statut= if (moyenne>=10.0) "VALIDE" else "ECHOUE"
                Toast.makeText(this, "Notes enregistrées pour $studentName", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, Resultat::class.java).apply {
                    putExtra("STUDENT_NAME", studentName)
                    putExtra("MOYENNE", moyenne)
                    putExtra("STATUT", statut)
                    putExtra("NOTE_ALGO", algo)
                    putExtra("NOTE_BDD", bdd)
                    putExtra("NOTE_MATHS", maths)
                    putExtra("NOTE_ANGLAIS", Anglais)
                }
                startActivity(intent)
            }
        }
        btnAnnuler.setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }
}