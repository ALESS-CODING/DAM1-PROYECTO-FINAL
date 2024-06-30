package pe.com.marbella.adaptadores.reporte.salida

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Salida
import pe.com.marbella.databinding.ListaReporteSalidaBinding

class ReporteSalidaViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private var binding : ListaReporteSalidaBinding = ListaReporteSalidaBinding.bind(view)
    fun render(salida: Salida) {
        binding.lblFechaSalida.text = salida.fechaSalida.toString()
        binding.lblUsuario.text = salida.usuario.nombre
        binding.lblEstadoSalida.text = salida.estado.toString()
    }
}