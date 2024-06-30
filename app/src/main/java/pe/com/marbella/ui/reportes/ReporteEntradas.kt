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
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.com.marbella.R
import pe.com.marbella.adaptadores.reporte.entrada.ReporteEntradaAdapter
import pe.com.marbella.databinding.ActivityReporteEntradasBinding
@AndroidEntryPoint
class ReporteEntradas : AppCompatActivity() {

    private lateinit var binding: ActivityReporteEntradasBinding
    private val reporteEntradaViewModel : ReporteEntradaViewModel by viewModels()
    private lateinit var reporteEntradaAdapter: ReporteEntradaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReporteEntradasBinding.inflate(layoutInflater)
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
        initEntradaReporteAdapter()
        initUIReporteEntrada()
    }

    //inicializar lista reporteEntrada
    private fun initUIReporteEntrada() {
       lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED){
               reporteEntradaViewModel.reporteEntrada.collect{
                   reporteEntradaAdapter.actualizarReporteSalidadAdapter(lista=it)
               }
           }
       }
    }

    //inicializar entrdadAdapter
    private fun initEntradaReporteAdapter() {
        reporteEntradaAdapter = ReporteEntradaAdapter()
        binding.rvReporteEntrada.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reporteEntradaAdapter
        }
    }
}