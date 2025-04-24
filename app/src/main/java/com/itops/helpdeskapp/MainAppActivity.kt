package com.itops.helpdeskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Intent
import android.widget.ImageView

class MainAppActivity : AppCompatActivity() {

    private lateinit var ticketAdapter: TicketAdapter
    private lateinit var allTickets: List<Ticket>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)

        // --- Referencias ---
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTickets)
        val searchEditText: EditText = findViewById(R.id.searchEditText) // Obtenemos el EditText
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAddTicket) // Obtener el FAB
        val exitIcon: ImageView = findViewById(R.id.iconExit)

        // --- Datos y Adapter ---
        allTickets = createDummyTickets() // Creamos los datos
        // Pasamos una copia mutable al adapter
        ticketAdapter = TicketAdapter(ArrayList(allTickets))

        // --- RecyclerView Setup ---
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ticketAdapter

        // --- Configuración del FAB ---
        fabAdd.setOnClickListener {
            // Crear Intent para ir a CreateTicketActivity
            val intent = Intent(this, CreateTicketActivity::class.java)
            startActivity(intent) // Iniciar la nueva Activity
        }

        exitIcon.setOnClickListener {
            // Crear Intent para volver a MainActivity (pantalla de bienvenida)
            val intent = Intent(this, MainActivity::class.java)

            // Añadir Flags para limpiar el historial y empezar la tarea de nuevo
            // Esto evita que el usuario pueda volver a esta pantalla con el botón "Atrás"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            // Iniciar MainActivity
            startActivity(intent)
        }

        // --- Configuración del Buscador ---
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No necesitamos hacer nada aquí
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Llamamos al filtro del adaptador cada vez que el texto cambia
                ticketAdapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
                // No necesitamos hacer nada aquí
            }
        })
    }

    // Función auxiliar para generar datos de ejemplo
    private fun createDummyTickets(): List<Ticket> {
        val tickets = mutableListOf<Ticket>()
        tickets.add(Ticket("TK-00001", "Falla de conexión"))
        tickets.add(Ticket("TK-00002", "Error del sistema"))
        tickets.add(Ticket("TK-00003", "Solicitud de acceso"))
        tickets.add(Ticket("TK-00004", "Falla de conexión"))
        tickets.add(Ticket("TK-00005", "Error del sistema"))
        tickets.add(Ticket("TK-00006", "Solicitud de acceso"))
        tickets.add(Ticket("TK-00007", "Falla de conexión"))
        tickets.add(Ticket("TK-00008", "Error del sistema"))
        tickets.add(Ticket("TK-00009", "Solicitud de acceso"))
        tickets.add(Ticket("TK-00010", "Falla de conexión"))
        tickets.add(Ticket("TK-00011", "Error del sistema"))
        return tickets
    }
}