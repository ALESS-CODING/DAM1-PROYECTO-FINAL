package pe.com.marbella.adaptadores.entrada

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.DetalleEntrada
import pe.com.marbella.databinding.ListaDetalleEntradaBinding

class DetalleEntradaViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private var binding: ListaDetalleEntradaBinding = ListaDetalleEntradaBinding.bind(view)
    fun render(detalleEntrada: DetalleEntrada) {
        binding.lblNombreProdEntra.text = detalleEntrada.producto.nombre
        binding.lblPrecioEntra.text = detalleEntrada.precio.toDouble().toString()
        binding.lblCantidadEntra.text = detalleEntrada.cantidad.toString()
        binding.lbldescProEntra.text = detalleEntrada.producto.descripcion
    }
}