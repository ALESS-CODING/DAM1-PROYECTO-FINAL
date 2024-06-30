package pe.com.marbella.ui.proveedor

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
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.databinding.ActivityRegistroProveedorBinding
import pe.com.marbella.util.DialogUtil

@AndroidEntryPoint
class RegistroProveedor : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroProveedorBinding
    private val registroProveedorViewModel: RegistroProveedorViewModel by viewModels ()
    //recebir toos los argumentos
    private val ARGUMENTS :  RegistroProveedorArgs by navArgs()

    //Objetos
    val dialogUtil: DialogUtil = DialogUtil()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroProveedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()

        //validar para carar la interfaz actualizar proveedor
        if(ARGUMENTS.ISEDITMODE && ARGUMENTS.codigoProveedor != -1L){
            registroProveedorViewModel.findByIdProveedor(ARGUMENTS.codigoProveedor)
        }
        //validar para atualizar o arabar
        binding.btnGrabarProv.setOnClickListener {
            if (ARGUMENTS.ISEDITMODE) {
                actualizarProveedor(ARGUMENTS.codigoProveedor)
            } else {
                grabarNuevoProveedor()
            }
        }

        // Configura el botón de regreso
        binding.btnRegresarProv.setOnClickListener {
            finish()
        }
    }

    private fun initUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                registroProveedorViewModel.stateResult.collect{
                    when(it){
                        is ResultState.Error -> {errorMensaje(it.error)}
                        is ResultState.FindById -> {cargarDataProveedor(it.data)}
                        is ResultState.Loading -> {}
                        is ResultState.Save -> {resultadoMensaje("Agredado")}
                        is ResultState.Delete -> {resultadoMensaje("Eliminado")}
                        is ResultState.Update -> {resultadoMensaje( "Atualizado")}
                        null -> {}
                    }
                }
            }
        }
    }

    //funion artear provvedor
    private fun grabarNuevoProveedor() {
        if(!validarInterfaz()){
            return
        }
        val proveedor = objetoProveedor()
        registroProveedorViewModel.saveProveedor(proveedor)
    }

    //funion atualizar proveedor
    private fun actualizarProveedor(idProveedor: Long) {
        if(!validarInterfaz()){
            return
        }
        val prove = objetoProveedor()
        registroProveedorViewModel.updateProveedor(idProveedor, prove)
    }

    //obtner obtner ampos y añadir a objeto Provvedor
    private fun objetoProveedor(): Proveedor {
        val nombre = binding.txtNombreProv.text.toString()
        val dir = binding.txtDirProv.text.toString()
        val tele = binding.txtTelefonoProv.text.toString()
        val correo = binding.txtCorreoProv.text.toString()
        val repre = binding.txtRepre.text.toString()
        val est = true
        val proveedor = Proveedor(0L, nombre, dir, tele, correo,repre,est)

        return proveedor
    }

    // carar datos en para atualizar
    private fun cargarDataProveedor(proveedor: Proveedor) {
        binding.txtNombreProv.setText(proveedor.nombre)
        binding.txtDirProv.setText(proveedor.direccion)
        binding.txtTelefonoProv.setText(proveedor.telefono)
        binding.txtCorreoProv.setText(proveedor.correo)
        binding.txtRepre.setText(proveedor.nomRepresentante)
    }
    //limpiar caja de textos
    private fun limpiarInterfaz(){
        binding.txtDirProv.setText("")
        binding.txtCorreoProv.setText("")
        binding.txtTelefonoProv.setText("")
        binding.txtNombreProv.setText("")
        binding.txtRepre.setText("")
    }
//    validad caja de textos
    private fun validarInterfaz (): Boolean {
        val txt =  binding.txtNombreProv.text
        val txtDir = binding.txtDirProv.text
        val txtRep = binding.txtRepre.text
        val txtCor = binding.txtCorreoProv.text
        val txtTel = binding.txtTelefonoProv.text


        if(txt.isEmpty() || txt.isBlank()){
            binding.txtNombreProv.error = "El campo no puede estar vacio"
            return false
        }else if(txtDir.isEmpty() || txtDir.isBlank()){
            binding.txtDirProv.error = "El campo no puede estar vacio"
            return false
        }else if(txtRep.isEmpty() || txtRep.isBlank()){
            binding.txtRepre.error = "El campo no puede estar vacio"
            return false
        } else if(txtTel.isEmpty() || txtTel.isBlank()){
            binding.txtTelefonoProv.error = "El campo no puede estar vacio"
            return false
        }else if(txtCor.isEmpty() || txtCor.isBlank()){
            binding.txtCorreoProv.error = "El campo no puede estar vacio"
            return false
        }else return true
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
}