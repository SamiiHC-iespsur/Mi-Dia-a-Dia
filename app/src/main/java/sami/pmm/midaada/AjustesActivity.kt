package sami.pmm.midaada

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class AjustesActivity : AppCompatActivity() {

    private lateinit var switchDarkMode: SwitchCompat
    private lateinit var cbAutoSave: CheckBox
    private lateinit var btnResetData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        // Inicializar vistas
        switchDarkMode = findViewById(R.id.switchDarkMode)
        cbAutoSave = findViewById(R.id.cbAutoSave)
        btnResetData = findViewById(R.id.btnResetData)

        // Configurar listener del Switch de modo oscuro
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Activar modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Desactivar modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Configurar listener del CheckBox de autoguardado
        cbAutoSave.setOnCheckedChangeListener { _, isChecked ->
            // Aquí podrías guardar la preferencia en SharedPreferences
            val message = if (isChecked) {
                "Autoguardado activado"
            } else {
                "Autoguardado desactivado"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        // Configurar listener del botón Restablecer
        btnResetData.setOnClickListener {
            // Restablecer los controles a sus valores por defecto
            switchDarkMode.isChecked = false
            cbAutoSave.isChecked = false

            // Mostrar mensaje de confirmación
            Toast.makeText(this, getString(R.string.settings_reset_message), Toast.LENGTH_LONG).show()
        }
    }
}