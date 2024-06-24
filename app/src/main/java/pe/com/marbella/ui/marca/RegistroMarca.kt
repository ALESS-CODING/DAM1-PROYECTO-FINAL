package pe.com.marbella.ui.marca

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.navArgs
import pe.com.marbella.R
import pe.com.marbella.databinding.ActivityRegistroMarcaBinding

class RegistroMarca : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroMarcaBinding
    private val ARGUMENTS: RegistroMarcaArgs by navArgs()

    private val registroViewModel: RegistroMarcaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroMarcaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ARGUMENTS.codigoMarca




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