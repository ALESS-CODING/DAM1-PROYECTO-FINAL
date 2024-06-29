package pe.com.marbella.ui.producto

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
import androidx.navigation.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pe.com.marbella.R
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.impl.MarcaImpl
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.services.MarcaApiService
import pe.com.marbella.databinding.ActivityRegistroProductoBinding
import pe.com.marbella.util.DialogUtil

@AndroidEntryPoint
class RegistroProducto : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroProductoBinding
    private val ARGUMENTS : RegistroProductoArgs by navArgs()

    private val productoRegistroViewModel: ProductoRegistroViewModel by viewModels ()
    //Objetos
    private val dialogUtil =  DialogUtil ()
    private var producto: Producto ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()

        //validar buttos para cargar producto
        if (ARGUMENTS.ISEDITMODE && ARGUMENTS.IDPRODUCTO != -1L) {
            productoRegistroViewModel.findByIdProducto(ARGUMENTS.IDPRODUCTO)
        }

        //validar buttos para agreagar o actualizar producto
        binding.btnGrabarProd.setOnClickListener {
            if (ARGUMENTS.ISEDITMODE) {
                actualizarProducto(ARGUMENTS.IDPRODUCTO)
            } else {
                grabarNuevoProducto()
            }
        }

        // Configura el botón de regreso
        binding.btnRegresarProd.setOnClickListener {
            finish()
        }
    }

    //iniciar estado
    private fun initUI() {
        initUIState()
        initUIMarca()
    }

    //iniciar stado
    private fun initUIMarca() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                productoRegistroViewModel.marcaList.collect{
                    cargarSpinerMarca(it)
                }
            }
        }
    }

    //iniciar estado de cada peticion
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                productoRegistroViewModel.resultState.collect{
                    when(it){
                        is ResultState.Delete -> {}
                        is ResultState.Error -> errorMensaje(it.error)
                        is ResultState.FindById -> {cargarDataProducto(it.data)}
                        is ResultState.Loading -> {}
                        is ResultState.Save -> resultadoMensaje("Guardado")
                        is ResultState.Update -> resultadoMensaje("Actualizado")
                        null -> {}
                    }
                }
            }
        }
    }

    private fun grabarNuevoProducto() {
        val txtNom = binding.txtNombreProd.text
        val txtDesc = binding.txtDescProd.text
        val txtSockMin = binding.txtStockMin.text
        val txtStockAct = binding.txtStockActual.text
        val txtPrec = binding.txtPrecioProd.text

        //val marca = Marca()
    }

    private fun actualizarProducto(idProducto: Long) {

        val item =  binding.cboMarcaProd.selectedItemId
        Toast.makeText(this, "$item", Toast.LENGTH_LONG).show()
    }

    //cargar producto en la iterfaz para actualizar
    private fun cargarDataProducto(producto: Producto) {
        limpiarInterfaz()
        binding.txtNombreProd.setText(producto.nombre)
        binding.txtDescProd.setText(producto.descripcion)
        binding.txtPrecioProd.setText(producto.precio.toDouble().toString())
        binding.txtStockActual.setText(producto.stockActual.toString())
        binding.txtStockMin.setText(producto.stockMinimo.toString())

        //cmbMarca
        val spinerMarca: ArrayAdapter<String> = binding.cboMarcaProd.adapter as ArrayAdapter<String>
        val position = spinerMarca.getPosition(producto.marca.descripcion)
        binding.cboMarcaProd.setSelection(position, true)

        //productoRegistroViewModel.getAllMarca()
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
            "Exito",
            "$error ",
            false,
            "Aceptar"
        )
    }

    //limpiar interfaz
    private fun limpiarInterfaz (){
        binding.txtDescProd.setText("")
        binding.txtStockMin.setText("")
        binding.txtNombreProd.setText("")
        binding.txtStockActual.setText("")
        binding.txtPrecioProd.setText("")
    }

    private fun cargarSpinerMarca (marcaSpiner : List<Marca>){

        val spinerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, marcaSpiner.map { i -> i.descripcion })
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cboMarcaProd.adapter = spinerAdapter

    }

}

private fun toast (contex: Context, message: String){
    Toast.makeText(contex, message, Toast.LENGTH_LONG).show()
}