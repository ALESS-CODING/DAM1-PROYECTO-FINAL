package pe.com.marbella.ui.producto

import android.content.Context
import android.os.Bundle
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
import pe.com.marbella.data.model.Categoria
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.UnidadMedida
import pe.com.marbella.databinding.ActivityRegistroProductoBinding
import pe.com.marbella.util.DialogUtil

@AndroidEntryPoint
class RegistroProducto : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroProductoBinding
    private val ARGUMENTS : RegistroProductoArgs by navArgs()

    private val productoRegistroViewModel: ProductoRegistroViewModel by viewModels ()
    //Objetos
    private val dialogUtil =  DialogUtil ()

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
        initUICategoria()
        initUIUnidadMedida()
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
    //inicializar lista unidad medida
    private fun initUIUnidadMedida() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                productoRegistroViewModel.unidadMedidaList.collect{
                    cargarSpinerUnidadMedida(it)
                }
            }
        }
    }
    //inicializar lista cateoria
    private fun initUICategoria() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                productoRegistroViewModel.categoriaList.collect{
                    cargarSpinerCategoria(it)
                }
            }
        }
    }

    //iniciar stado marca
    private fun initUIMarca() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                productoRegistroViewModel.marcaList.collect{
                    cargarSpinerMarca(it)
                }
            }
        }
    }


    private fun grabarNuevoProducto() {

        if(!validarInterfaz()){
            return
        }
        //obtner marca seleccionada
        val positionMarca = binding.cboMarcaProd.selectedItemPosition
        val marca = productoRegistroViewModel.marcaList.value[positionMarca]

        //obtner categoria seleccionada
        val positionCatoria = binding.cboCategoriaProd.selectedItemPosition
        val cateoria = productoRegistroViewModel.categoriaList.value[positionCatoria]

        //obtner categoria unidad medida
        val positionMedida = binding.cboUnidadProd.selectedItemPosition
        val unidadMedida = productoRegistroViewModel.unidadMedidaList.value[positionMedida]

        val txtNom = binding.txtNombreProd.text.toString()
        val txtDesc = binding.txtDescProd.text.toString()
        val txtSockMin = binding.txtStockMin.text.toString().toInt()
        val txtStockAct = binding.txtStockActual.text.toString().toInt()
        val txtPrec = binding.txtPrecioProd.text.toString().toBigDecimal()

        val producto = Producto(
            -1L, txtNom, txtDesc, txtSockMin, txtStockAct,
            txtPrec, true , unidadMedida, cateoria, marca
        )
        //guardar producto en bd
        productoRegistroViewModel.saveProducto(producto)
    }

    private fun actualizarProducto(idProducto: Long) {

        if(!validarInterfaz()){
            return
        }
        val produtoSearch = productoRegistroViewModel.productoList.value.first { p -> p.codigo == idProducto }
        //buscar producto por id

        //obtner marca seleccionada
        val positionMarca = binding.cboMarcaProd.selectedItemPosition
        val marca = productoRegistroViewModel.marcaList.value[positionMarca]

        //obtner categoria seleccionada
        val positionCatoria = binding.cboCategoriaProd.selectedItemPosition
        val cateoria = productoRegistroViewModel.categoriaList.value[positionCatoria]

        //obtner categoria unidad medida
        val positionMedida = binding.cboUnidadProd.selectedItemPosition
        val unidadMedida = productoRegistroViewModel.unidadMedidaList.value[positionMedida]

        val txtNom = binding.txtNombreProd.text.toString()
        val txtDesc = binding.txtDescProd.text.toString()
        val txtSockMin = binding.txtStockMin.text.toString().toInt()
        val txtStockAct = binding.txtStockActual.text.toString().toInt()
        val txtPrec = binding.txtPrecioProd.text.toString().toBigDecimal()

        //setaer los datos
        produtoSearch.codigo = idProducto
        produtoSearch.nombre = txtNom
        produtoSearch.descripcion = txtDesc
        produtoSearch.stockMinimo = txtSockMin
        produtoSearch.stockActual = txtStockAct
        produtoSearch.precio = txtPrec

        produtoSearch.unidadMedida = unidadMedida
        produtoSearch.marca = marca
        produtoSearch.categoria = cateoria

        //enviar lo datos
        productoRegistroViewModel.updateProducto(idProducto, produtoSearch)

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
        val spinerMarcaArray= binding.cboMarcaProd.adapter as ArrayAdapter<String>
        val positionMarca = spinerMarcaArray.getPosition(producto.marca.descripcion)
        binding.cboMarcaProd.setSelection(positionMarca, true)

        //obtner cateoria
        val spinerCategoriaArray = binding.cboCategoriaProd.adapter as ArrayAdapter<String>
        val positiocCategoria = spinerCategoriaArray.getPosition(producto.categoria.descripcion)
        binding.cboCategoriaProd.setSelection(positiocCategoria, true)

        //obtner cateoria
        val spinerUnidadMedidaArray = binding.cboCategoriaProd.adapter as ArrayAdapter<String>
        val positiocUnidadMedida = spinerUnidadMedidaArray.getPosition(producto.unidadMedida.descripcion)
        binding.cboUnidadProd.setSelection(positiocUnidadMedida, true)
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
    //validar campos de texto
    private fun validarInterfaz (): Boolean{
        val txtNom = binding.txtNombreProd.text
        val txtDesc = binding.txtDescProd.text
        val txtSockMin = binding.txtStockMin.text
        val txtStockAct = binding.txtStockActual.text
        val txtPrec = binding.txtPrecioProd.text

        if(txtNom.isBlank() || txtNom.isBlank()){
            binding.txtNombreProd.error = "Este campo no puede estar vacio"
            binding.txtNombreProd.requestFocus()
            return false
        }else if (txtDesc.isBlank() || txtDesc.isEmpty()){
            binding.txtDescProd.error = "Este campo no puede estar vacio"
            binding.txtDescProd.requestFocus()
            return false
        }else if (txtSockMin.isBlank() || txtSockMin.isEmpty()){
            binding.txtStockMin.error = "Este campo no puede estar vacio"
            binding.txtStockMin.requestFocus()
            return false
        }else if (txtStockAct.isBlank() || txtStockAct.isEmpty()) {
            binding.txtStockActual.error = "Este campo no puede estar vacio"
            binding.txtStockActual.requestFocus()
            return false
        }else if (txtPrec.isBlank() || txtPrec.isEmpty()) {
            binding.txtPrecioProd.error = "Este campo no puede estar vacio"
            binding.txtPrecioProd.requestFocus()
            return false
        }else return true
    }

    //carar spiner Marca
    private fun cargarSpinerMarca (marcaList : List<Marca>){
        val spinerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, marcaList.map { i -> i.descripcion })
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cboMarcaProd.adapter = spinerAdapter

    }

    //carar spiner Cateoria
    private fun cargarSpinerCategoria (cateoriaList: List<Categoria>){
        val spinerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, cateoriaList.map { i -> i.descripcion })
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cboCategoriaProd.adapter = spinerAdapter

    }
    //carar combo unidad medida
    private fun cargarSpinerUnidadMedida(unidadMedidaList: List<UnidadMedida>) {
        val spinerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, unidadMedidaList.map { i -> i.descripcion })
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cboUnidadProd.adapter = spinerAdapter
    }
}

private fun toast (contex: Context, message: String){
    Toast.makeText(contex, message, Toast.LENGTH_LONG).show()
}