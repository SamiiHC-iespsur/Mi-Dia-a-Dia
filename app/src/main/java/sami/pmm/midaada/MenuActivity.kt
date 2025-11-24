package sami.pmm.midaada

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        // Listeners para cada botón del menú
        findViewById<Button?>(R.id.btnProfile)?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        findViewById<Button?>(R.id.btnTasks)?.setOnClickListener {
            Toast.makeText(this, "Abriendo Tareas...", Toast.LENGTH_SHORT).show()
            // TODO: Implementar navegación a las tareas
        }
        findViewById<Button?>(R.id.btnSettings)?.setOnClickListener {
            Toast.makeText(this, "Abriendo Ajustes...", Toast.LENGTH_SHORT).show()
            // TODO: Implementar navegación a los ajustes
        }
    }
}
