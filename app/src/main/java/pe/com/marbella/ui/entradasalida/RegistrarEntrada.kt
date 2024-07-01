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
import pe.com.marbella.adaptadores.entrada.DetalleEntradaAdapter
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.model.DetalleEntrada
import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.data.model.Usuario
import pe.com.marbella.databinding.ActivityRegistrarEntradaBinding
import pe.com.marbella.util.DialogUtil
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class RegistrarEntrada : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrarEntradaBinding
    private val entradaViewModel: EntradaViewModel by viewModels()

    //variables
    private var listaDetalleEntrata  = mutableListOf<DetalleEntrada>()
    private lateinit var detalleEntradaAdapter : DetalleEntradaAdapter
    //objetos
    private val dialogUtil = DialogUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrarEntradaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegistrarEntradaBD.isEnabled = false
        binding.txtFechaEnt.setText(fechaActual())
        binding.txtFechaEnt.isFocusable = false
        binding.txtFechaEnt.isClickable = false

        initUI()
        // Configura el botón de regresoaaaaaaa
        binding.fabAtras.setOnClickListener {
            finish()
        }

        //button argrear
        binding.btnAnadirProdEnt.setOnClickListener {
            agregarDetalle()
        }

        binding.btnRegistrarEntradaBD.setOnClickListener {
            guardarEntradaBD()
        }
    }

    private fun initUI() {
        initDetalleEntradaAdapter()
        fechaActual()
        initStateSaveEntrada()
        initSpinerProveedor()
        initAutoCompleteBuscadorProducto()

    }
    //resultado de uaradr un entrada
    private fun initStateSaveEntrada() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                entradaViewModel.resultEntrada.collect{
                    when(it){
                        is ResultState.Delete -> {}
                        is ResultState.Error -> errorMensaje("Error en el servido al guardar la entrada")
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

    private fun initDetalleEntradaAdapter() {
        detalleEntradaAdapter = DetalleEntradaAdapter()
        binding.rvListaDetalleEntrada.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detalleEntradaAdapter
        }
    }

    //
    private fun initAutoCompleteBuscadorProducto() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                entradaViewModel.productoList.collect{
                    cargarAutoCompleteBuscarProducto(it)
                }
            }
        }
    }

    //inicializar spiner proveedor
    private fun initSpinerProveedor() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                entradaViewModel.proveedorList.collect{
                    cargarSpinerProveedor(it)
                }
            }
        }
    }
    private fun guardarEntradaBD() {
        if(listaDetalleEntrata.isEmpty()){
            return
        }

        val fechaHoy = fechaActual()

        val usuario = Usuario(1L, "Administrador", "correo", "empleado", "", true , emptyList())
        //obtener posicion de proveedor selccionado
        val position = binding.cboProvEntrada.selectedItemPosition
        val proveedor = entradaViewModel.proveedorList.value[position]

        val entrada = Entrada(0L, fechaHoy, usuario, proveedor, true, listaDetalleEntrata )
        //mandar a bd
        entradaViewModel.saveEntrada(entrada)
        binding.rvListaDetalleEntrada.adapter = DetalleEntradaAdapter()
        //enabilitar el boton
        binding.btnRegistrarEntradaBD.isEnabled = false
    }
    //Arear un detalle a lista DetallelIst
    private fun agregarDetalle() {

        if(!validarInterfaz()){
            return
        }
        val detalle = objDetalleEntrada()
        if(detalle != null){
            listaDetalleEntrata.add(detalle)
            detalleEntradaAdapter.actualizarListaDetalleEntrada(listaDetalleEntrata)
            limpiarInterfaz()
        }
        if(listaDetalleEntrata.isNotEmpty()){
            binding.btnRegistrarEntradaBD.isEnabled = true
        }

    }

    private fun objDetalleEntrada (): DetalleEntrada? {
        try{
            //capturar los valores
            val cantidad = binding.txtCantEnt.text.toString()
            val precio = binding.txtPrecioEnt.text.toString()
            //nombre de producto selecionasdo selectedItemPosition
            val nombrePro = binding.acProducto.text.toString()
            val producto = entradaViewModel.productoList.value.first{pro -> pro.nombre.lowercase().equals(nombrePro.lowercase())}

            val detalle = DetalleEntrada( producto, cantidad.toInt(), precio.toBigDecimal())
            return  detalle

        }catch (e: NoSuchElementException){
            errorMensaje("El producto no existe")
            Log.i("Error", "${e.message}")
            return null
        }

    }
    //cargar autocomplete buscador producto
    private fun cargarAutoCompleteBuscarProducto(productoList: List<Producto>){
        val autocompleteProductoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, productoList.map { pro -> pro.nombre })
        autocompleteProductoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.acProducto.threshold = 1
        binding.acProducto.setAdapter(autocompleteProductoAdapter)
    }
    //carar spiner provvedor
    private fun cargarSpinerProveedor(proveedors: List<Proveedor>) {
        val spinerProveedorAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, proveedors.map { pro -> pro.nombre })
        spinerProveedorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cboProvEntrada.adapter = spinerProveedorAdapter
    }
    //carar fecha de hoy
    private fun fechaActual() : String {
        val fechaHoy = Date()
        val formato = SimpleDateFormat("dd/MM/yyyy")
        val formateado = formato.format(fechaHoy)

        return formateado
    }

    //validar interfaz
    private fun validarInterfaz(): Boolean{
        val fecha = binding.txtFechaEnt.text.toString()
        val producto = binding.acProducto.text.toString()
        val cantidad = binding.txtCantEnt.text.toString()
        val precio = binding.txtPrecioEnt.text.toString()
        val error = "Este campo no puede estar vacio"

        if(fecha.isEmpty() || fecha.isBlank()){
            binding.txtFechaEnt.error = error
            return false
        }else if(producto.isEmpty() || producto.isBlank()){
            binding.acProducto.error = error
            binding.acProducto.requestFocus()
            return false
        }else if(cantidad.isEmpty() || cantidad.isBlank()){
            binding.txtCantEnt.error = error
            binding.txtCantEnt.requestFocus()
            return false
        }else if(precio.isBlank() || precio.isEmpty()){
            binding.txtPrecioEnt.error = error
            binding.txtPrecioEnt.requestFocus()
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

    private fun limpiarInterfaz (){
        binding.txtCantEnt.setText("")
        binding.txtPrecioEnt.setText("")
        binding.txtPrecioEnt.setText("")
        binding.acProducto.setText("")
        binding.cboProvEntrada.setSelection(0)
        binding.acProducto.requestFocus()
    }

}