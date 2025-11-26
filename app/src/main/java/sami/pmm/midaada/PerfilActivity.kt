package sami.pmm.midaada

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class PerfilActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var cbReminders: CheckBox
    private lateinit var rgNotifications: RadioGroup
    private lateinit var btnSaveProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Inicializar vistas
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        cbReminders = findViewById(R.id.cbReminders)
        rgNotifications = findViewById(R.id.rgNotifications)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)

        // Configurar listener del botón Guardar
        btnSaveProfile.setOnClickListener {
            saveProfile()
        }
    }

    private fun saveProfile() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        cbReminders.isChecked
        val selectedNotificationId = rgNotifications.checkedRadioButtonId

        // Validar que se ingresó un nombre
        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show()
            etName.requestFocus()
            return
        }

        // Validar que el correo contenga al menos un @
        if (!email.contains("@")) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            etEmail.requestFocus()
            return
        }

        // Validar que se haya seleccionado una opción de notificaciones
        if (selectedNotificationId == -1) {
            Toast.makeText(this, "Por favor, selecciona una opción de notificaciones", Toast.LENGTH_SHORT).show()
            return
        }

        // Aquí podrías guardar los datos en SharedPreferences, base de datos, etc.
        // Por ahora solo mostramos el mensaje de confirmación

        val message = "Perfil guardado. ¡Bienvenid@, $name!"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // Opcional: cerrar la actividad después de guardar
        // finish()
    }
}

