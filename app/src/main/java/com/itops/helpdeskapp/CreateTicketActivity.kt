package com.itops.helpdeskapp // O tu paquete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar // Importar Toolbar
import android.view.MenuItem // Importar para botón atrás
import android.widget.ImageView // ¡Importar!
import android.content.Intent  // ¡Importar!

class CreateTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ticket)

        // Configurar la Toolbar
        val toolbar: Toolbar = findViewById(R.id.createTicketToolbar)
        setSupportActionBar(toolbar)

        // Habilitar el botón de "Atrás" en la Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // --- NUEVO: Listener para el icono de Salir ---
        // 1. Encontrar el ImageView por su ID
        val exitIcon: ImageView = findViewById(R.id.iconExit)

        // 2. Establecer el OnClickListener
        exitIcon.setOnClickListener {
            // 3. Crear Intent para ir a MainActivity (pantalla bienvenida)
            val intent = Intent(this, MainActivity::class.java)

            // 4. Añadir Flags para limpiar historial
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            // 5. Iniciar MainActivity
            startActivity(intent)
        }
        // --- Fin Listener Icono Salir ---
    }

    // Manejar el clic en el botón de "Atrás" de la Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Respond to the action bar's Up/Home button
        if (item.itemId == android.R.id.home) {
            // onBackPressed() // Opción 1: Simula el botón atrás del sistema
            finish()       // Opción 2: Simplemente cierra esta activity
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}