package com.itops.helpdeskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // --- Código para el botón Iniciar Sesión ---
        val loginButton: Button = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // --- Listener para el botón Registrarse ---
        val registerButton: Button = findViewById(R.id.buttonRegister)
        registerButton.setOnClickListener {
            Toast.makeText(this, "Botón Registrarse presionado", Toast.LENGTH_SHORT).show()
        }
    }
}