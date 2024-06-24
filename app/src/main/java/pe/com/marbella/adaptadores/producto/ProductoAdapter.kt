package pe.com.marbella.adaptadores.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.Producto

class ProductoAdapter (var productoList: List<Producto> = emptyList()) : RecyclerView.Adapter<ProductoViewHolder>() {

    //Actualiza la listaAdapter
    fun updateProductsList (list: List<Producto>){
        productoList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
         return ProductoViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.list_producto ,parent, false)
         )
    }

    override fun getItemCount(): Int {
       return productoList.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.render(productoList[position])
    }


}