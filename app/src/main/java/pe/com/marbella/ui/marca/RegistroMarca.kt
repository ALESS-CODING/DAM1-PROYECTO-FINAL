package pe.com.marbella.ui.marca

import android.os.Bundle
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
import kotlinx.coroutines.launch
import pe.com.marbella.R
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.model.Marca
import pe.com.marbella.databinding.ActivityRegistroMarcaBinding
import pe.com.marbella.util.DialogUtil

@AndroidEntryPoint
class RegistroMarca: AppCompatActivity() {

    private lateinit var binding: ActivityRegistroMarcaBinding
    private val ARGUMENTS: RegistroMarcaArgs by navArgs()

    private val registroViewModel: RegistroMarcaViewModel by viewModels()

    //objetos
    private val dialogUtil = DialogUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegistroMarcaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()

        //validar para carar marca
        if(ARGUMENTS.ISEDITMODE && ARGUMENTS.codigoMarca != -1L){
            registroViewModel.findByIdMarca(ARGUMENTS.codigoMarca)
        }

        //Atualizar o grabar una marca
        binding.btnGrabarMar.setOnClickListener {
            if (ARGUMENTS.ISEDITMODE) {
                actualizarMarca(ARGUMENTS.codigoMarca)
            } else {
                grabarNuevaMarca()
            }
        }
        // Configura el botón de regreso
        binding.btnRegresarMar.setOnClickListener {
            finish()
        }
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                registroViewModel.resultState.collect{
                    when(it){
                        is ResultState.Delete -> {}
                        is ResultState.Error -> errorMensaje(it.error)
                        is ResultState.FindById -> cargarDataMarca(it.data)
                        is ResultState.Loading -> {}
                        is ResultState.Save -> {resultadoMensaje("guardado")}
                        is ResultState.Update -> {resultadoMensaje("Actualizado")}
                        null -> {}
                    }
                }
            }

        }
    }
    //funion limpiar
    private fun limpiarInterfaz (){
        binding.txtNombreMar.setText("")
    }

    //funcion capturar error
    private fun validarInterfaz (): Boolean {
        val txt = binding.txtNombreMar.text.toString()
        if(txt.isEmpty() || txt.isBlank()){
            binding.txtNombreMar.error = "El campo no puede estar vacio"
            binding.txtNombreMar.requestFocus()
            return false
        }
        return true
    }

    //Grabar nueva marca
    private fun grabarNuevaMarca() {
        if(!validarInterfaz()){
            return
        }
        val marca: Marca = Marca(0L, binding.txtNombreMar.text.toString(), true)
        registroViewModel.saveMarca(marca)
        limpiarInterfaz()
    }

    //llamar a la funcion actulizar
    private fun actualizarMarca(idMarca: Long) {
        if(!validarInterfaz()){
            return
        }
        val desc: String = binding.txtNombreMar.text.toString()
        val marca: Marca = Marca (idMarca, desc, estado = true)
        registroViewModel.updateMarca(idMarca, marca)
        limpiarInterfaz()
    }

    //Resultado de atualizar: muestra dialo
    private fun resultadoMensaje(tipoMessae: String) {
        dialogUtil.MensajeAlerta(
            this,
            "Exito",
            "$tipoMessae con éxito ",
            false,
            "Aceptar"
        )
        limpiarInterfaz()
    }

    private fun errorMensaje (error: String){
        dialogUtil.MensajeAlerta(
            this,
            "ERROR",
            "$error ",
            false,
            "Aceptar"
        )
    }

    //cargar datos de marca para actualizar
    private fun cargarDataMarca(marca: Marca) {
        binding.txtNombreMar.setText(marca.descripcion)
    }


}