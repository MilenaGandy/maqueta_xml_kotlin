package com.itops.helpdeskapp // O tu paquete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter // ¡Importar!
import android.widget.Filterable // ¡Importar!
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale // Para búsqueda case-insensitive

// Añadimos ", Filterable" para implementar la interfaz
class TicketAdapter(private var ticketList: MutableList<Ticket>) : // Cambiamos a MutableList para poder modificarla
    RecyclerView.Adapter<TicketAdapter.TicketViewHolder>(), Filterable {

    // Guardamos una copia de la lista original y completa para filtrar siempre sobre ella
    private var ticketListFull: List<Ticket> = ArrayList(ticketList) // Copia inicial

    // --- ViewHolder (sin cambios) ---
    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconTicket: ImageView = itemView.findViewById(R.id.iconTicket)
        val textTicketNumber: TextView = itemView.findViewById(R.id.textTicketNumber)
        val textTicketSubject: TextView = itemView.findViewById(R.id.textTicketSubject)
        val iconViewTicket: ImageView = itemView.findViewById(R.id.iconViewTicket)
    }

    // --- onCreateViewHolder (sin cambios) ---
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_ticket, parent, false)
        return TicketViewHolder(itemView)
    }

    // --- onBindViewHolder (sin cambios) ---
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val currentItem = ticketList[position] // Usa la lista (potencialmente filtrada)
        holder.textTicketNumber.text = currentItem.number
        holder.textTicketSubject.text = currentItem.subject
    }

    // --- getItemCount (sin cambios, usa la lista actual) ---
    override fun getItemCount(): Int {
        return ticketList.size
    }

    // --- ¡NUEVO! Implementación de Filterable ---
    override fun getFilter(): Filter {
        return ticketFilter
    }

    private val ticketFilter = object : Filter() {
        // Se ejecuta en un hilo de fondo
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<Ticket> = ArrayList()

            if (constraint == null || constraint.isEmpty()) {
                // Si no hay filtro, mostramos la lista completa original
                filteredList.addAll(ticketListFull)
            } else {
                // Convertimos el texto del filtro a minúsculas y quitamos espacios
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim()

                // Recorremos la lista completa original
                for (item in ticketListFull) {
                    // Comprobamos si el número O el asunto contienen el texto del filtro (ignorando mayúsculas/minúsculas)
                    if (item.number.lowercase(Locale.getDefault()).contains(filterPattern) ||
                        item.subject.lowercase(Locale.getDefault()).contains(filterPattern)) {
                        // Si coincide, lo añadimos a la lista filtrada
                        filteredList.add(item)
                    }
                }
            }

            // Devolvemos los resultados del filtrado
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        // Se ejecuta en el hilo principal (UI) para publicar los resultados
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            // Limpiamos la lista actual que muestra el RecyclerView
            ticketList.clear()
            // Añadimos los resultados filtrados
            if (results?.values is List<*>) {
                // Hacemos un cast seguro
                @Suppress("UNCHECKED_CAST")
                ticketList.addAll(results.values as List<Ticket>)
            }
            // Notificamos al adaptador que los datos han cambiado para que refresque la lista
            notifyDataSetChanged() // Nota: Para listas muy grandes, hay métodos más eficientes, pero este es el más simple.
        }
    }
}