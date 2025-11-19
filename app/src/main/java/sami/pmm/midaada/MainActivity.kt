package sami.pmm.midaada

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el botón "Empezar"
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            // TODO: Navegar al menú principal (MenuActivity - aún no implementada)
            // Placeholder: mostrar mensaje mientras se implementa MenuActivity
            Toast.makeText(this, "Abriendo Menú Principal...", Toast.LENGTH_SHORT).show()
        }
    }
}