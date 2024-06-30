package pe.com.marbella.adaptadores.reporte.stock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.Producto

class ReporteStockAdapter (private var productoStock: List<Producto> = emptyList()) : RecyclerView.Adapter<ReporteStockViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteStockViewHolder {
        val reporteStockViewHolder = ReporteStockViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.lista_producto_stock, parent, false)
        )
        return reporteStockViewHolder
    }

    override fun getItemCount(): Int {
        return productoStock.size
    }

    override fun onBindViewHolder(holder: ReporteStockViewHolder, position: Int) {
        holder.render(productoStock[position])
    }

    fun actualizarReporteStock(lista: List<Producto>) {
        productoStock = lista
        notifyDataSetChanged()
    }

}