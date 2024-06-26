package pe.com.marbella.ui.proveedor

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.navArgs
import pe.com.marbella.R
import pe.com.marbella.databinding.ActivityRegistroProveedorBinding

class RegistroProveedor : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroProveedorBinding

    //recebir toos los argumentos
    private val ARGUMENTS :  RegistroProveedorArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroProveedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //copigo e provveor
        ARGUMENTS.codigoProveedor


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val isEditMode = intent.getBooleanExtra("IS_EDIT_MODE", false)
        val idProveedor = intent.getLongExtra("ID_PROVEEDOR", -1L)

        if (isEditMode && idProveedor != -1L) {
            cargarDataProveedor(idProveedor)
        } else {

        }

        findViewById<Button>(R.id.btnGrabarProv).setOnClickListener {
            if (isEditMode) {
                actualizarProveedor(idProveedor)
            } else {
                grabarNuevoProveedor()
            }
        }

        // Configura el botón de regreso
        val backButton: Button = findViewById(R.id.btnRegresarProv)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun grabarNuevoProveedor() {

    }

    private fun actualizarProveedor(idProveedor: Long) {

    }

    private fun cargarDataProveedor(idProveedor: Long) {

    }
}