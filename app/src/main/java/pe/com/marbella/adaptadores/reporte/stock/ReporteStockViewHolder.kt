package pe.com.marbella.adaptadores.reporte.stock

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Producto
import pe.com.marbella.databinding.ListaProductoStockBinding

class ReporteStockViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private var binding : ListaProductoStockBinding = ListaProductoStockBinding.bind(view)
    fun render(producto: Producto) {
        binding.lblNombreProd.text = producto.nombre
        binding.lblStockAct.text = producto.stockActual.toString()
        binding.lblStockMin.text = producto.stockMinimo.toString()
        binding.lbldescPro.text = producto.descripcion
        binding.lblPrecio.text = producto.precio.toDouble().toString()
    }
}