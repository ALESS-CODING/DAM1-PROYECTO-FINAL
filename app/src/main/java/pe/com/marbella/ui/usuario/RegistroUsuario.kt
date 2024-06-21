package pe.com.marbella.ui.usuario

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.com.marbella.R

class RegistroUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val isEditMode = intent.getBooleanExtra("IS_EDIT_MODE", false)
        val idUsuario = intent.getLongExtra("ID_USUARIO", -1L)

        if (isEditMode && idUsuario != -1L) {
            cargarDataUsuario(idUsuario)
        } else {

        }

        findViewById<Button>(R.id.btnGrabarUsu).setOnClickListener {
            if (isEditMode) {
                actualizarUsuario(idUsuario)
            } else {
                grabarNuevoUsuario()
            }
        }

        // Configura el botón de regreso
        val backButton: Button = findViewById(R.id.btnRegresarUsu)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun grabarNuevoUsuario() {

    }

    private fun actualizarUsuario(idUsuario: Long) {

    }

    private fun cargarDataUsuario(idUsuario: Long) {

    }
}