package com.vinz.projectcontoh1.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.vinz.projectcontoh1.R

class Login : AppCompatActivity() {
    // Deklarasi variabel untuk input username dan password
    private lateinit var usernameInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText

    // Username dan password yang telah diatur
    private val validUsername = "adapasti@gmail.com"
    private val validPassword = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Menghubungkan variabel dengan komponen di layout
        usernameInput = findViewById(R.id.username_edit)
        passwordInput = findViewById(R.id.password_edit)

        // Mendapatkan referensi ke tombol login
        val loginBtn = findViewById<MaterialButton>(R.id.login_button)

        // Menetapkan aksi ketika tombol login diklik
        loginBtn.setOnClickListener {

            // Memvalidasi inputan
            if (validateInput()) {

                // Membuat intent untuk berpindah ke MainActivity
                val intent = Intent(this, MainActivity::class.java)

                // Menambahkan dan membawa data username dan password ke intent dengan tujuan ke MainActivity
                intent.putExtra("name", usernameInput.text.toString())

                // Memulai activity baru
                startActivity(intent)
            } else {

                // Menampilkan pesan toast jika validasi gagal
                Toast.makeText(this, "Username atau password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk memvalidasi inputan
    private fun validateInput(): Boolean {
        // Mengambil nilai inputan
        val inputUsername = usernameInput.text.toString()
        val inputPassword = passwordInput.text.toString()

        // Memeriksa apakah inputan username dan password sesuai dengan yang telah diatur
        return inputUsername == validUsername && inputPassword == validPassword
    }
}
