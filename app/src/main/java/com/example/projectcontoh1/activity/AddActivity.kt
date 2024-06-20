package com.vinz.projectcontoh1.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.vinz.projectcontoh1.R
import com.vinz.projectcontoh1.data.AppViewModel
import com.vinz.projectcontoh1.data.room.AppEntity
import com.vinz.projectcontoh1.data.ViewModelFactory

class AddActivity : AppCompatActivity() {

    private lateinit var appViewModel: AppViewModel
    private var categoriesPlace = emptyList<String>()
    private lateinit var inputNameEdit: TextInputEditText
    private lateinit var inputKategoriEdit: MaterialAutoCompleteTextView
    private lateinit var inputHarga: TextInputEditText
    private lateinit var inputalamat: TextInputEditText
    private lateinit var inputkamarMandi: TextInputEditText
    private lateinit var inputkamarTidur: TextInputEditText
    private lateinit var inputfasilitas: TextInputEditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val factory = ViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        inputNameEdit = findViewById(R.id.input_name_edit)
        inputKategoriEdit = findViewById(R.id.input_kategori_edit)
        inputHarga = findViewById(R.id.input_harga_edit)
        inputalamat = findViewById(R.id.input_alamat_edit)
        inputkamarMandi = findViewById(R.id.input_kamarmandi_edit)
        inputkamarTidur = findViewById(R.id.input_kamartidur_edit)
        inputfasilitas = findViewById(R.id.input_fasilitas_edit)

        appViewModel.getAllKategori()

        appViewModel.listFood.observe(this) { categories ->
            categoriesPlace = categories.map { it.name }
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categoriesPlace)
            inputKategoriEdit.setAdapter(adapter)
        }

        val btnSimpan: Button = findViewById(R.id.btn_simpan)
        btnSimpan.setOnClickListener {
            if (validateInput()) {
                appViewModel.insertFood(
                    AppEntity(
                        id = 0,
                        name = inputNameEdit.text.toString(),
                        kategori = inputKategoriEdit.text.toString(),
                        alamat = inputalamat.text.toString(),
                        harga = inputHarga.text.toString(),
                        kamarmandi = inputkamarMandi.text.toString(),
                        kamartidur = inputkamarTidur.text.toString(),
                        fasilitas = inputfasilitas.text.toString()
                    )
                )
                finish()
            }
        }

        val backIcon = findViewById<ImageView>(R.id.imageView)
        backIcon.setOnClickListener {
            onBackPressed()
        }
    }

    private fun validateInput(): Boolean {
        var error = 0

        if (inputNameEdit.text.toString().isEmpty()) {
            error++
            inputNameEdit.error = "Masukkan nama terlebih dahulu!"
        }

        if (inputKategoriEdit.text.toString().isEmpty()) {
            error++
            inputKategoriEdit.error = "Masukkan kategori terlebih dahulu!"
        }

        return error == 0
    }
}
