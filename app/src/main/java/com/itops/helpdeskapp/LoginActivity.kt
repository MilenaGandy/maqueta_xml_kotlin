package com.itops.helpdeskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
// ¡Asegúrate de que DatabaseHelper.kt esté en el mismo paquete!

// Esta es tu pantalla de LOGIN (la de la evidencia)
class LoginActivity : AppCompatActivity() {

    // Declaramos las vistas y el helper de la BD
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Cargamos el layout de LOGIN (el que hicimos en el paso anterior)
        setContentView(R.layout.activity_login)

        // 2. Inicializamos el DatabaseHelper
        dbHelper = DatabaseHelper(this)

        // --- (Opcional) Añadimos un usuario de prueba la primera vez ---
        // Descomenta esta línea la primera vez que corras la app para crear un usuario
        dbHelper.addUser("test@sena.edu.co", "12345")
        // ---

        // 3. Conectamos las variables con los IDs del XML
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        // 4. Asignamos un "escuchador" al botón de Ingresar
        buttonLogin.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        // Obtenemos el texto que el usuario escribió
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        // Validamos que no estén vacíos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese email y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // ¡Usamos la función de validación de nuestro Helper!
        val loginSuccess = dbHelper.checkUser(email, password)

        // Mostramos un mensaje según el resultado
        if (loginSuccess) {
            Toast.makeText(this, "¡Login Exitoso!", Toast.LENGTH_SHORT).show()

            // Aquí iría el código para pasar a la pantalla principal
            // val intent = Intent(this, MainAppActivity::class.java)
            // startActivity(intent)
            // finish() // Cerramos la pantalla de login
        } else {
            Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}