package com.vinz.projectcontoh1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.vinz.projectcontoh1.R
import com.vinz.projectcontoh1.data.AppViewModel
import com.vinz.projectcontoh1.data.ViewModelFactory
import com.vinz.projectcontoh1.data.room.AppEntity

class UpdateActivity : AppCompatActivity() {

    private lateinit var appViewModel: AppViewModel
    private var categoriesPlace = emptyList<String>()
    private lateinit var inputNameEdit: TextInputEditText
    private lateinit var inputKategoriEdit: MaterialAutoCompleteTextView
    private lateinit var inputHargaEdit: TextInputEditText
    private lateinit var inputAlamatEdit: TextInputEditText
    private lateinit var inputkamarmandiEdit: TextInputEditText
    private lateinit var inputkamartidurEdit: TextInputEditText
    private lateinit var inputfasilitasEdit: TextInputEditText
    private lateinit var getData: AppEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        getData = intent.getParcelableExtra("data")!!

        val factory = ViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        inputNameEdit = findViewById(R.id.input_name_edit)
        inputKategoriEdit = findViewById(R.id.input_kategori_edit)
        inputHargaEdit = findViewById(R.id.input_harga_edit)
        inputAlamatEdit = findViewById(R.id.input_alamat_edit)
        inputkamarmandiEdit = findViewById(R.id.input_kamarmandi_edit)
        inputkamartidurEdit = findViewById(R.id.input_kamartidur_edit)
        inputfasilitasEdit = findViewById(R.id.input_fasilitas_edit)

        inputNameEdit.setText(getData.name)
        inputKategoriEdit.setText(getData.kategori)
        inputHargaEdit.setText(getData.harga)
        inputAlamatEdit.setText(getData.alamat)
        inputkamarmandiEdit.setText(getData.kamarmandi)
        inputkamartidurEdit.setText(getData.kamartidur)
        inputfasilitasEdit.setText(getData.fasilitas)

        appViewModel.getAllKategori()
        appViewModel.listFood.observe(this) { categories ->
            categoriesPlace = categories.map { it.name }
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categoriesPlace)
            inputKategoriEdit.setAdapter(adapter)
        }

        val btnSimpan: Button = findViewById(R.id.btn_simpan)
        btnSimpan.setOnClickListener {
            if (validateInput()) {
                appViewModel.updateFood(
                    AppEntity(
                        id = getData.id,
                        name = inputNameEdit.text.toString(),
                        kategori = inputKategoriEdit.text.toString(),
                        alamat = inputAlamatEdit.text.toString(),
                        harga = inputHargaEdit.text.toString(),
                        kamarmandi = inputkamarmandiEdit.text.toString(),
                        kamartidur = inputkamartidurEdit.text.toString(),
                        fasilitas = inputfasilitasEdit.text.toString()
                    )
                )
                finish()
            }
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
