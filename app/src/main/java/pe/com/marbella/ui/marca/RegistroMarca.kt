package pe.com.marbella.ui.marca

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.com.marbella.R
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.MarcaState
import pe.com.marbella.data.services.MarcaApiService
import pe.com.marbella.databinding.ActivityRegistroMarcaBinding
import javax.inject.Inject

@AndroidEntryPoint
class RegistroMarca: AppCompatActivity() {

    private lateinit var binding: ActivityRegistroMarcaBinding
    private val ARGUMENTS: RegistroMarcaArgs by navArgs()

    private val registroViewModel: RegistroMarcaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroMarcaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        if(ARGUMENTS.ISEDITMODE && ARGUMENTS.codigoMarca != -1L){
            registroViewModel.findByIdMarca(ARGUMENTS.codigoMarca)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        /*val isEditMode = intent.getBooleanExtra("IS_EDIT_MODE", false)
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
        }*/

        // Configura el botón de regreso
        val backButton: Button = findViewById(R.id.btnRegresarMar)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                registroViewModel.stateMarca.collect{
                    when(it){
                        is MarcaState.Error -> {}
                        MarcaState.Loading -> {}
                        is MarcaState.Success -> {cargarDataMarca(it.marca)}
                    }
                }
            }

        }
    }

    private fun error() {
        Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_LONG).show()
    }

    private fun grabarNuevaMarca() {

    }

    private fun actualizarMarca(idMarca: Long) {

    }

    private fun cargarDataMarca(marca: Marca) {
        binding.txtNombreMar.setText(marca.descripcion.toString())
    }
}