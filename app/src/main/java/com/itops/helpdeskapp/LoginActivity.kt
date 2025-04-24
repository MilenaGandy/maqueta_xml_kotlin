package com.itops.helpdeskapp // O tu paquete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.content.Intent

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los elementos del layout XML
        val emailLayout: TextInputLayout = findViewById(R.id.textFieldEmail)
        val emailEditText: TextInputEditText = findViewById(R.id.editTextEmail)
        val passwordLayout: TextInputLayout = findViewById(R.id.textFieldPassword)
        val passwordEditText: TextInputEditText = findViewById(R.id.editTextPassword)
        val enterButton: Button = findViewById(R.id.buttonEnter)

        // Configurar el listener para el botón "Entrar"
        enterButton.setOnClickListener {
            // Obtener el texto de los campos, quitando espacios al inicio/final del email
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            // --- Validación Ficticia ---

            // 1. Resetear errores previos en los layouts
            emailLayout.error = null
            passwordLayout.error = null

            // 2. Validar campos vacíos
            if (email.isEmpty()) {
                emailLayout.error = "Ingrese su usuario (Email)" // Mostrar error en el campo
                return@setOnClickListener // Detener ejecución si hay error
            }
            if (password.isEmpty()) {
                passwordLayout.error = "Ingrese su contraseña" // Mostrar error en el campo
                return@setOnClickListener // Detener ejecución si hay error
            }

            // 3. Validar credenciales (ficticias)
            if (email == "agente@itops.com" && password == "12345678") {
                // Credenciales CORRECTAS
                Toast.makeText(this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()

                // Dentro del setOnClickListener del botón "Entrar", en el bloque if de éxito:
                if (email == "agente@itops.com" && password == "12345678") {
                    Toast.makeText(this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()

                    // --- Navegación a la pantalla principal ---
                    val intent = Intent(this, MainAppActivity::class.java)
                    startActivity(intent)
                    finish() // Cierra LoginActivity para que el usuario no vuelva a ella con el botón Atrás
                    // --- Fin Navegación ---

                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
                // val intent = Intent(this, MainAppActivity::class.java) // Crear MainAppActivity luego
                // startActivity(intent)
                // finish() // Opcional: cierra LoginActivity para que no se pueda volver atrás

            } else {
                // Credenciales INCORRECTAS
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        }
    }
}