package pe.com.marbella.adaptadores.proveedor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.Proveedor

class ProveedorAdapter (var proveedorList: List<Proveedor> = emptyList()) : RecyclerView.Adapter<ProveedorViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProveedorViewHolder {
        val proveedorViewHolder: ProveedorViewHolder = ProveedorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_proveedor,parent , false)
        )
        return  proveedorViewHolder
    }

    override fun getItemCount(): Int {
        return proveedorList.size
    }

    override fun onBindViewHolder(holder: ProveedorViewHolder, position: Int) {
        return holder.render(proveedorList[position])
    }

    fun actualizarProveedorList(lista: List<Proveedor>) {
        proveedorList = lista
        notifyDataSetChanged()
    }
}