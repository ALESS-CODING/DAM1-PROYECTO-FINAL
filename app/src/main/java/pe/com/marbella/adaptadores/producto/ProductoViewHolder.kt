package pe.com.marbella.adaptadores.producto

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Producto
import pe.com.marbella.databinding.ListProductoBinding

class ProductoViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ListProductoBinding.bind(view)
    fun render (producto: Producto){
        binding.lblNombreProd.text = producto.nombre
        binding.lblStockAct.text = producto.stockActual.toString()
        binding.lblStockMin.text = producto.stockMinimo.toString()
        binding.lblPrecio.text =  producto.precio.toDouble().toString()
    }
}