package com.itops.helpdeskapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MiProyecto.db"
        private const val TABLE_USERS = "users"
        private const val KEY_ID = "id"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }

    // Este método se llama la primera vez que se accede a la base de datos
    override fun onCreate(db: SQLiteDatabase?) {
        // Creamos la tabla de usuarios
        val createTableQuery = ("CREATE TABLE $TABLE_USERS ("
                + "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$KEY_EMAIL TEXT UNIQUE,"
                + "$KEY_PASSWORD TEXT)")
        db?.execSQL(createTableQuery)
    }

    // Se llama si actualizamos la DATABASE_VERSION
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // --- Función para añadir un usuario (para registrar) ---
    fun addUser(email: String, pass: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_EMAIL, email)
        values.put(KEY_PASSWORD, pass) // En un proyecto real, ¡esto debe estar encriptado!

        // Insertamos la fila
        val success = db.insert(TABLE_USERS, null, values)
        db.close()
        return success
    }

    // --- ¡LA FUNCIÓN CLAVE DE VALIDACIÓN! ---
    fun checkUser(email: String, pass: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(KEY_ID)
        val selection = "$KEY_EMAIL = ? AND $KEY_PASSWORD = ?"
        val selectionArgs = arrayOf(email, pass) // Igual, la contraseña debería estar encriptada

        // Consultamos la base de datos
        val cursor = db.query(
            TABLE_USERS,       // La tabla
            columns,           // Columnas a retornar
            selection,         // El "WHERE"
            selectionArgs,     // Valores del "WHERE"
            null, null, null
        )

        val count = cursor.count
        cursor.close()
        db.close()

        // Si count > 0, significa que encontró un usuario con ese email Y esa contraseña
        return count > 0
    }
}
