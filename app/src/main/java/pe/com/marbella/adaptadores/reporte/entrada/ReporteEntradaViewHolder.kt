package pe.com.marbella.adaptadores.reporte.entrada

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Entrada
import pe.com.marbella.databinding.ListaReporteEntradaBinding

class ReporteEntradaViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private var binding: ListaReporteEntradaBinding = ListaReporteEntradaBinding.bind(view)
    fun render(entrada: Entrada) {
        binding.lblFechaEntrada.text = entrada.fechaEntrada.toString()
        binding.lblUsuario.text = entrada.usuario.nombre
        binding.lblProveedor.text = entrada.proveedor.nombre
        binding.lblEstadoEntrada.text = entrada.estado.toString()
    }
}