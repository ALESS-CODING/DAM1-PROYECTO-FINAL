package pe.com.marbella.ui.reportes

import android.os.Bundle
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
import pe.com.marbella.adaptadores.reporte.stock.ReporteStockAdapter
import pe.com.marbella.databinding.ActivityReporteStockBinding

@AndroidEntryPoint
class ReporteStock : AppCompatActivity() {

    private lateinit var binding: ActivityReporteStockBinding
    private val reporteStockViewModel: ReporteStockViewModel by viewModels()
    private lateinit var reporteStockAdapter: ReporteStockAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReporteStockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()
        // Configura el botón de regreso
        binding.fabAtras.setOnClickListener {
            finish()
        }

    }

    private fun initUI() {
        initReporteStockAdapter()
        initUIState()
    }

    //iniciar lista stockProducto y actualizar adaptadorReposrtesStockAdapter
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                reporteStockViewModel.stockProducto.collect{
                    reporteStockAdapter.actualizarReporteStock(it)
                }
            }
        }
    }

    //inicializar reporte stock adapter
    private fun initReporteStockAdapter() {
        reporteStockAdapter = ReporteStockAdapter()
        //asianar valorers a reciclyview con el adapterReportesStock
        binding.rvReporteStock.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reporteStockAdapter
        }
    }


}