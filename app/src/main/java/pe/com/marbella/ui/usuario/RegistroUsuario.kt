package pe.com.marbella.ui.usuario

import android.os.Bundle
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.com.marbella.R
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.model.Rol
import pe.com.marbella.data.model.Usuario
import pe.com.marbella.databinding.ActivityRegistroUsuarioBinding
import pe.com.marbella.util.DialogUtil

@AndroidEntryPoint
class RegistroUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroUsuarioBinding
    private val usuarioRegistroViewModel: UsuarioRegistroViewModel by viewModels()

    private val ARGUMENTS : RegistroUsuarioArgs by navArgs()

    val dialogUtil = DialogUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()

        if (ARGUMENTS.ISEDITMODE && ARGUMENTS.codigoUsuario != -1L) {
            usuarioRegistroViewModel.findByIdUsuario(ARGUMENTS.codigoUsuario)
        }

        //confirara boton
        binding.btnGrabarUsu.setOnClickListener {
            if (ARGUMENTS.ISEDITMODE) {
                actualizarUsuario(ARGUMENTS.codigoUsuario)
            } else {
                grabarNuevoUsuario()
            }
        }
        // Configura el botón de regreso
        binding.btnRegresarUsu.setOnClickListener {
            finish()
        }
    }
    private fun initUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                usuarioRegistroViewModel.stateResult.collect{
                    when(it){
                        is ResultState.Delete -> {}
                        is ResultState.Error -> {errorMensaje(it.error)}
                        is ResultState.FindById -> cargarDataUsuario(it.data)
                        is ResultState.Loading -> {}
                        is ResultState.Save -> {resultadoMensaje("Guardado")}
                        is ResultState.Update -> {resultadoMensaje("Actualizo")}
                        null -> {}
                    }
                }
            }
        }
    }

    //
    private fun grabarNuevoUsuario() {
        if(!validarInterfaz()){
            return
        }
        val usuario = objetoUsuario()
        usuarioRegistroViewModel.saveUsuario(usuario)
    }

    //atualizar usuario
    private fun actualizarUsuario(idUsuario: Long) {
        if(!validarInterfaz()){
            return
        }
        val usuario = usuarioRegistroViewModel.usuarioList.value.first { usu -> usu.codigo == idUsuario }

        val correo = binding.txtCorreo.text.toString()
        val nombre = binding.txtNombreUsu.text.toString()
        val contra = binding.txtContraseniaUsu.text.toString()
        val username = binding.txtUsername.text.toString()
        //setear los datos
        usuario.nombre = nombre
        usuario.username = username
        usuario.password = contra
        usuario.correo = correo
        usuarioRegistroViewModel.updateUsuario(idUsuario, usuario)
    }

    //apturar datos de interface
    private fun objetoUsuario (): Usuario{
        val correo = binding.txtCorreo.text.toString()
        val nombre = binding.txtNombreUsu.text.toString()
        val contra = binding.txtContraseniaUsu.text.toString()
        val username = binding.txtUsername.text.toString()
        val rol: List<Rol> = listOf(Rol(1L, "ADMIN"))
        return Usuario(0L, nombre, correo, username, contra, true, rol)
    }

    //cargar datos de usuario
    private fun cargarDataUsuario(usuario: Usuario) {
        binding.txtUsername.setText(usuario.nombre)
        binding.txtCorreo.setText(usuario.correo)
        binding.txtNombreUsu.setText(usuario.username)
        binding.txtContraseniaUsu.setText(usuario.password)
    }

    //validar campo
    private  fun validarInterfaz (): Boolean{
        val correo = binding.txtCorreo.text.toString()
        val nombre = binding.txtNombreUsu.text.toString()
        val contra = binding.txtContraseniaUsu.text.toString()
        val username = binding.txtUsername.text.toString()

        if(nombre.isEmpty() || nombre.isBlank()){
            binding.txtNombreUsu.error = "Este campo no puede estar vacio"
            binding.txtNombreUsu.requestFocus()
            return false
        }else if(correo.isEmpty() || correo.isBlank()){
            binding.txtCorreo.error = "Este campo no puede estar vacio"
            binding.txtCorreo.requestFocus()
            return false
        }else if(username.isEmpty() || username.isBlank()){
            binding.txtUsername.error = "Este campo no puede estar vacio"
            binding.txtUsername.requestFocus()
            return false
        }else if(contra.isEmpty() || contra.isBlank()){
            binding.txtContraseniaUsu.error = "Este campo no puede estar vacio"
            binding.txtContraseniaUsu.requestFocus()
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

    private fun errorMensaje(error: String){
        dialogUtil.MensajeAlerta(
            this,
            "Error",
            "$error ",
            false,
            "Aceptar"
        )
    }
    //limpiar interfaz
    private fun limpiarInterfaz (){
        binding.txtCorreo.setText("")
        binding.txtNombreUsu.setText("")
        binding.txtContraseniaUsu.setText("")
        binding.txtUsername.setText("")
    }
}