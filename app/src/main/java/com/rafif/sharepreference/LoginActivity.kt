package com.rafif.sharepreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.rafif.sharepreference.databinding.ActivityLoginBinding
import java.util.prefs.Preferences

class LoginActivity : AppCompatActivity() {

    val PREFS_NAME = "belajarsharedpreference"
    val KEY_EMAIL = "key.email"
    val KEY_PASSWORD = "key.password"
    val KEY_SESSION = "key.session"

    private lateinit var binding: ActivityLoginBinding

    //kita perlu meendapatkan class SharedPreference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    //cara biar bisa simpan email

    private fun saveEmail(email : String){
        val editor : SharedPreferences.Editor = sharedPreferences.edit() //untuk edit data yang ada
        editor.putString(KEY_EMAIL, email) //setiap data yang disimpan berdasarkan key dan value
        editor.apply()
    }

    //cara biar bisa simpan password
    private fun savePassword(password : String){
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    private fun saveSession(session : Boolean){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_SESSION, session)
        editor.apply()

    }

    private fun getSession(key : String) : Boolean{
        return sharedPreferences.getBoolean(key, false)
    }


    private fun msg(){
        val email : String = binding.inputEmail.text.toString()
        val password : String = binding.inputPassword.text.toString()

        when{
            email == "" -> {
                val msg = Toast.makeText(this, "masukkan Email", Toast.LENGTH_SHORT)
                msg.setGravity(Gravity.TOP, 0, 140)
                msg.show()
            }
            password == "" -> {
                val msg = Toast.makeText(this, "masukkan Password", Toast.LENGTH_SHORT)
                msg.setGravity(Gravity.TOP, 0, 140)
                msg.show()
            }
            else ->{
                val msg = Toast.makeText(this, "masukkan Password", Toast.LENGTH_SHORT)
                msg.setGravity(Gravity.TOP, 0, 140)
                msg.show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (getSession(KEY_SESSION)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    fun onLogin(view:View){
        val email = binding.inputEmail.text.toString()
        saveEmail(email)
        val password = binding.inputPassword.text.toString()
        savePassword(password)
        saveSession(true)
        msg()
    }
}