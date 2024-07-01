package pe.com.marbella.ui.entradasalida

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pe.com.marbella.R
import pe.com.marbella.adaptadores.salida.DetalleSalidaAdapter
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.model.DetalleSalida
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.Salida
import pe.com.marbella.data.model.Usuario
import pe.com.marbella.databinding.ActivityRegistrarSalidaBinding
import pe.com.marbella.util.DialogUtil
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class RegistrarSalida : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarSalidaBinding
    private val salidaViewModel: SalidaViewModel by viewModels()
    private var detalleSalidaList = mutableListOf<DetalleSalida>()
    private lateinit var detalleSalidaAdapter: DetalleSalidaAdapter

    //objetos
    private val dialogUtil = DialogUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrarSalidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnRegistrarSalidaBD.isEnabled = false
        binding.txtFechaSal.setText(fechaActual())
        binding.txtFechaSal.isFocusable = false
        binding.txtFechaSal.isClickable = false
        binding.actProductoSal.requestFocus()

        initUI()
        // Configura el botón de regreso
        binding.fabAtras.setOnClickListener {
            finish()
        }
        //agrear detalle localmente
        binding.btnAnadirProdSal.setOnClickListener {
            agregarDetalle()
        }

        binding.btnRegistrarSalidaBD.setOnClickListener {
            guardarSalidaBD()
        }

    }

    private fun initUI() {
        fechaActual()
        initDetalleSalidaAdapter()
        initAutoCompleteProducto()
        initStateSaveSalida()
    }

    private fun initStateSaveSalida() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                salidaViewModel.resultSalida.collect{
                    when(it){
                        is ResultState.Delete -> {}
                        is ResultState.Error -> errorMensaje("Error en el servidor al guardar la salida")
                        is ResultState.FindById -> {}
                        is ResultState.Loading -> {}
                        is ResultState.Save -> resultadoMensaje("Guardado")
                        is ResultState.Update -> {}
                        null -> {}
                    }
                }
            }
        }
    }

    //inicializar adapterSalida
    private fun initDetalleSalidaAdapter() {
        detalleSalidaAdapter = DetalleSalidaAdapter()
        binding.rvListaDetalleSalida.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detalleSalidaAdapter
        }
    }

    //carar lista producto
    private fun initAutoCompleteProducto() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                salidaViewModel.productoList.collect{
                    cargarAutoCompleteProducto(it)
                }
            }
        }
    }
    //guardar salida en bd
    private fun guardarSalidaBD() {
       if(detalleSalidaList.isEmpty()){
           return
       }
        val fecha = fechaActual()
        val usuario = Usuario(1L, "Administrador", "", "empleado", "", true , emptyList())
        val salida = Salida(0L, fecha, usuario, true, detalleSalidaList )
        salidaViewModel.saveSalida(salida)
        binding.rvListaDetalleSalida.adapter = DetalleSalidaAdapter()
        //enabilitar el boton
        binding.btnRegistrarSalidaBD.isEnabled = false
    }

    //arear detalle a detalleSalidaAdapter
    private fun agregarDetalle() {
        if(!validarInterfaz()){
            return
        }

        val detalleSalida = objDetalleSalida()
        if(detalleSalida != null){
            detalleSalidaList.add(detalleSalida)
            detalleSalidaAdapter.actualizarListaDetalleSalida(detalleSalidaList)
            limpiarInterfaz()
        }
        if(detalleSalidaList.isNotEmpty()){
            binding.btnRegistrarSalidaBD.isEnabled = true
        }
    }
    private fun objDetalleSalida (): DetalleSalida? {

        try{
            //capturar los valores
            val fecha = binding.txtFechaSal.text.toString()
            val cantidad = binding.txtCantSal.text.toString()
            val precio = binding.txtPrecioSal.text.toString()
            //nombre de producto selecionasdo selectedItemPosition
            val nombrePro = binding.actProductoSal.text.toString()

            val producto = salidaViewModel.productoList.value.first{pro -> pro.nombre.lowercase().equals(nombrePro.lowercase())}

            val usuario = Usuario(1L, "Administrador", "", "empleado", "", true , emptyList())
            //crear unjeto entrdad
            if(cantidad.toInt() > producto.stockActual){
                dialogUtil.MensajeAlerta(this, "Alerta", "Stock insuficiente. Disponibles: ${producto.stockActual}.", false,"Aceptar")
                binding.txtCantSal.requestFocus()
                return null
            }

            val detalleSalida = DetalleSalida(producto, cantidad.toInt(), precio.toBigDecimal())
            return  detalleSalida

        }catch (e: NoSuchElementException){
            errorMensaje("El producto no existe")
            Log.i("Error", "${e.message}")
            return null
        }

    }
    //carar fecha de hoy
    private fun fechaActual() : String{
        val fechaHoy = Date()
        val formato = SimpleDateFormat("dd/MM/yyyy")
        val formateado = formato.format(fechaHoy)
        return formateado
    }

    //carar autocomplete producto
    private fun cargarAutoCompleteProducto(listaProducto: List<Producto>) {
        val autocompleteProductoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaProducto.map { pro -> pro.nombre })
        autocompleteProductoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.actProductoSal.threshold = 1
        binding.actProductoSal.setAdapter(autocompleteProductoAdapter)
    }
    //validar interfaz
    private fun validarInterfaz(): Boolean{
        val fecha = binding.txtFechaSal.text.toString()
        val producto = binding.actProSalida.text.toString()
        val cantidad = binding.txtCantSal.text.toString()
        val precio = binding.txtPrecioSal.text.toString()
        val error = "Este campo no puede estar vacio"

        if(fecha.isEmpty() || fecha.isBlank()){
            binding.txtFechaSal.error = error
            return false
        }else if(producto.isEmpty() || producto.isBlank()){
            binding.actProSalida.error = error
            binding.actProSalida.requestFocus()
            return false
        }else if(cantidad.isEmpty() || cantidad.isBlank()){
            binding.txtCantSal.error = error
            binding.txtCantSal.requestFocus()
            return false
        }else if(precio.isBlank() || precio.isEmpty()){
            binding.txtPrecioSal.error = error
            binding.txtPrecioSal.requestFocus()
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
        //limpiarInterfaz()
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

    private fun limpiarInterfaz () {
        binding.txtCantSal.setText("")
        binding.txtPrecioSal.setText("")
        binding.actProductoSal.setText("")
        binding.actProductoSal.requestFocus()
    }
}