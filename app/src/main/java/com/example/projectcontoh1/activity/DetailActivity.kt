package com.vinz.projectcontoh1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.vinz.projectcontoh1.R
import com.vinz.projectcontoh1.data.room.AppEntity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getData = intent.getParcelableExtra<AppEntity>("data")!!


        val HomeName = findViewById<TextView>(R.id.detail_title)
        val listrik = findViewById<TextView>(R.id.category_value)
        val alamat = findViewById<TextView>(R.id.alamat_value)
        val harga = findViewById<TextView>(R.id.harga_value)
        val jmlkamarMandi = findViewById<TextView>(R.id.kamarmandi_value)
        val jmlkamarTidur = findViewById<TextView>(R.id.kamartidur_value)
        val fasilitas = findViewById<TextView>(R.id.fasilitas_value)

        HomeName.text = getData.name
        listrik.text = getData.kategori
        alamat.text = getData.alamat
        harga.text = "Rp " + getData.harga + " Juta"
        jmlkamarMandi.text = getData.kamarmandi
        jmlkamarTidur.text = getData.kamartidur
        fasilitas.text = getData.fasilitas

        val backButton = findViewById<ImageView>(R.id.imageView)
        backButton.setOnClickListener {
            toMain()
        }
    }

    private fun toMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}