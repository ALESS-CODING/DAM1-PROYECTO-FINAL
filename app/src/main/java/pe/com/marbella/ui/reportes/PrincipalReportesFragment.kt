package pe.com.marbella.ui.reportes

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import pe.com.marbella.R
import pe.com.marbella.databinding.FragmentPrincipalReportesBinding
import pe.com.marbella.databinding.FragmentProductoBinding
import pe.com.marbella.ui.principal.PrincipalFragment
import pe.com.marbella.ui.producto.RegistroProducto

@AndroidEntryPoint
class PrincipalReportesFragment : Fragment() {

    private val principalReportesViewModel by viewModels<PrincipalReportesViewModel>()
    private var _binding: FragmentPrincipalReportesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalReportesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnReporteEntrada.setOnClickListener {
            val intent = Intent(activity, ReporteEntradas::class.java)
            startActivity(intent)
        }

        binding.btnReporteSalida.setOnClickListener {
            val intent = Intent(activity, ReporteSalidas::class.java)
            startActivity(intent)
        }

        binding.btnReporteStock.setOnClickListener {
            val intent = Intent(activity, ReporteStock::class.java)
            startActivity(intent)
        }

        binding.fabAtras.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return root
    }
}