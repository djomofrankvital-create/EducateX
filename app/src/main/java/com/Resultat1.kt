package com

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gestiondesnotes.R
import org.w3c.dom.Text

class Resultat1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultat1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name= intent.getStringExtra("STUDENT_NAME") ?: ""
        val moyenne= intent.getDoubleExtra("MOYENNE", 0.0)
        val statut= intent.getStringExtra("STATUT")?: "ECHOUE"
        val algo= intent.getDoubleExtra("NOTE_ALGO", 0.0)
        val bdd= intent.getDoubleExtra("NOTE_BDD", 0.0)
        val maths= intent.getDoubleExtra("NOTE_MATHS", 0.0)
        val anglais= intent.getDoubleExtra("NOTE_ANGLAIS", 0.0)

        val tvAlgo= findViewById<TextView>(R.id.tvResAlgo)
        val tvBdd= findViewById<TextView>(R.id.tvResBdd)
        val tvMaths= findViewById<TextView>(R.id.tvResMaths)
        val tvAnglais= findViewById<TextView>(R.id.tvResAnglais)

        val tvName= findViewById<TextView>(R.id.tvResultatName)
        val tvMoyenne= findViewById<TextView>(R.id.tvResulatMoyenne)
        val tvStatut= findViewById<TextView>(R.id.tvResultatStatut)
        val banniere= findViewById<LinearLayout>(R.id.layoutStatutBanniere)

        tvName.text="Eleve: $name"
        tvMoyenne.text=String.format("MOYENNE GENERALE: %.2f /20", moyenne)
        tvStatut.text= statut

        tvAlgo.text= String.format("%.0f /20", algo)
        tvBdd.text=String.format("%.0f /20", bdd)
        tvMaths.text=String.format("%.0f /20", maths)
        tvAnglais.text=String.format("%.0f /20", anglais)

        if(statut == "VALIDE"){
            banniere.setBackgroundColor(Color.parseColor("#4ADE80"))
        }else{
            banniere.setBackgroundColor(Color.parseColor("#F87171"))
        }

        val retour= findViewById<ImageView>(R.id.tvRetour)
        retour.setOnClickListener {
            val intent= Intent(this, Saisi_Note::class.java)
            startActivity(intent)
        }

        val btnRetour= findViewById<Button>(R.id.btnRetourListe)
        btnRetour.setOnClickListener {
            val intent= Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }
}