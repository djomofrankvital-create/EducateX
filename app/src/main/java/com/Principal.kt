package com

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuAdapter
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondesnotes.R

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Tous les eleves
          val rvEleve= findViewById<RecyclerView>(R.id.rvEleve)
        val ExploreEleve= listOf(
            Eleve("TOUKAM HOTOU ANGE JUPILLE", "Genie logiciel", "Niveau 2", R.drawable.acc),
            Eleve("MEKAM NGUIFO EDEN PAULE", "Sécurité réseau", "Niveau 2", R.drawable.eden2),
            Eleve("FOFOU ELISA", "Genie Logiciel", "Niveau 2", R.drawable.fofou1),
            Eleve("DJOMO KOUAKAM FRANK VITAL", "Sécurité réseau", "Niveau 2", R.drawable.franck),
            Eleve("MAWAMBO IDE SHIKANAEL", "Genie Logiciel", "Niveau 2", R.drawable.ide),
            Eleve("KUATE JOSEPH", "Sécuté réseau", "Niveau 2", R.drawable.joseph),
            Eleve("DJOFANG NKANGOU PATRICK", "Genie logiciel", "Niveau 2", R.drawable.leblanc),
            Eleve("MANANG TEKENG MARIOLE", "Sécurité réseau", "Niveau 2", R.drawable.mariole),
            Eleve("NGOUAGNA TALACJIO RONALD", "Sécurité réseau", "Niveau 2", R.drawable.ronald),
            Eleve("MEGNE ALVERA", "Genie logiciel", "Niveau 2", R.drawable.xena),
            Eleve("YEFOUE ANTHORIAN TONY", "Sécurité réseau", "Niveau 2", R.drawable.yefoue),
            Eleve("TAGAKO GRACE", "Genie logiciel", "Niveau 2", R.drawable.tagako),
            Eleve("MIAFO ARTHUR LUCIANO", "Genie Logiciel", "Niveau 1", R.drawable.luciano),
            Eleve("TSIDIE TOKAM MALCOM", "Genie logiciel", "Niveau 2", R.drawable.tsidie),
            Eleve("ABENG FONGAING ASTRID", "Genie logiciel", "Niveau 2", R.drawable.astrid),
            Eleve("DJIODJO KOUDJOU EVRAD", "Genie logiciel", "Niveau 2", R.drawable.dante),
            Eleve("NDJASSAP KAMTA FRANK", "Genie logiciel", "Niveau 2", R.drawable.kamta),
            Eleve("SOKENG SANDRA", "Sécurité réseau", "Niveau 2", R.drawable.sandra),
        )
        rvEleve.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvEleve.adapter= MenuAdapter(ExploreEleve)
    }
    fun showSemestreDialog(studentName: String, studentImg: Int){
        val builder= AlertDialog.Builder(this)
        val view= LayoutInflater.from(this).inflate(R.layout.select_semestre, null)
        builder.setView(view)

        val tvTitle= view.findViewById<TextView>(R.id.DialogTitle)
        val img=view.findViewById<ImageView>(R.id.monImage)
        val btnSemestre1= view.findViewById<AppCompatButton>(R.id.btnSemestre1)
        val btnSemestre2= view.findViewById<AppCompatButton>(R.id.btnSemestre2)

        tvTitle.text="$studentName"
        img.setImageResource(studentImg)
        val dialog= builder.create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        btnSemestre1.setOnClickListener {
            val intent= Intent(this, Saisi_Note::class.java).apply {
                putExtra("STUDENT_NAME", studentName)
            }
            startActivity(intent)
            Toast.makeText(this, "$studentName - semestre 1", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        btnSemestre2.setOnClickListener {
            val intent= Intent(this, Saisi_Note1::class.java).apply {
                putExtra("STUDENT_NAME", studentName)
            }
            startActivity(intent)
            Toast.makeText(this, "$studentName - semestre 2", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.show()
    }

    data class Eleve(
        val nom: String,
        val filiere: String,
        val niveau: String,
        val img: Int
    )
    inner class MenuAdapter(private val menuList: List<Eleve>): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){
        inner class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val nom: TextView= itemView.findViewById(R.id.txtExploreTitle)
            val filiere: TextView= itemView.findViewById(R.id.txtExploreFiliere)
            val niveau: TextView= itemView.findViewById(R.id.txtExploreNiveau)
            val img: ImageView= itemView.findViewById(R.id.imgExplore)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
            val view= LayoutInflater.from(parent.context).inflate(R.layout.item_liste, parent, false)
            return MenuViewHolder(view)
        }

        override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
            val currentEleve= menuList[position]
            holder.nom.text= currentEleve.nom
            holder.filiere.text= currentEleve.filiere
            holder.niveau.text= currentEleve.niveau
            holder.img.setImageResource(currentEleve.img)
            holder.itemView.setOnClickListener {
                showSemestreDialog(currentEleve.nom, currentEleve.img)
            }

        }

        override fun getItemCount(): Int {
            return menuList.size
        }
    }
}