package pe.com.marbella.ui.marca

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.com.marbella.R

class RegistroMarca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_marca)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val isEditMode = intent.getBooleanExtra("IS_EDIT_MODE", false)
        val idMarca = intent.getLongExtra("ID_MARCA", -1L)

        if (isEditMode && idMarca != -1L) {
            cargarDataMarca(idMarca)
        } else {

        }

        findViewById<Button>(R.id.btnGrabarMar).setOnClickListener {
            if (isEditMode) {
                actualizarMarca(idMarca)
            } else {
                grabarNuevaMarca()
            }
        }

        // Configura el botón de regreso
        val backButton: Button = findViewById(R.id.btnRegresarMar)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun grabarNuevaMarca() {

    }

    private fun actualizarMarca(idMarca: Long) {

    }

    private fun cargarDataMarca(idMarca: Long) {

    }
}