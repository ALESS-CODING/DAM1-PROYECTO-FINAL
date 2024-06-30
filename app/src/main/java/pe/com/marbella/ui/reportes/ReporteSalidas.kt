package pe.com.marbella.ui.reportes

import android.os.Bundle
import android.widget.Button
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.com.marbella.R
import pe.com.marbella.adaptadores.reporte.salida.ReporteSalidaAdapter
import pe.com.marbella.databinding.ActivityReporteSalidasBinding

@AndroidEntryPoint
class ReporteSalidas : AppCompatActivity() {

    private lateinit var binding: ActivityReporteSalidasBinding
    private val reporteSalidaViewModel : ReporteSalidaViewModel  by viewModels()
    private lateinit var reporteSalidadAdapter: ReporteSalidaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReporteSalidasBinding.inflate(layoutInflater)
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
        initUIStateSalidaReporte()
        initReporteSalidaAdapter()
    }

    //inicializamos el adaptador
    private fun initReporteSalidaAdapter() {
        reporteSalidadAdapter = ReporteSalidaAdapter()
        binding.rcReporteSalida.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reporteSalidadAdapter
        }
    }

    //inicializamos el estado de lista salida reporte
    private fun initUIStateSalidaReporte() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                reporteSalidaViewModel.reporteSalida.collect{
                    reporteSalidadAdapter.actualizarListaSalida(lista=it)
                }
            }
        }
    }


}