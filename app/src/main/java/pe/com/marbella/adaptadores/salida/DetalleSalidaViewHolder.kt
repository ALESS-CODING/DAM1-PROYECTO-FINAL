package pe.com.marbella.adaptadores.salida

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.DetalleSalida
import pe.com.marbella.databinding.ListaDetalleSalidaBinding
import pe.com.marbella.databinding.ListaReporteSalidaBinding

class DetalleSalidaViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private var binding: ListaDetalleSalidaBinding = ListaDetalleSalidaBinding.bind(view)
    fun render(detalleSalida: DetalleSalida) {
        binding.lblPrecioSalida.text = detalleSalida.toString()
        binding.lblCantidadSalida.text = detalleSalida.cantidad.toString()
        binding.lblNombreProdEntra.text = detalleSalida.producto.nombre
        binding.lblPrecioSalida.text = detalleSalida.precio.toDouble().toString()
    }
}