package pe.com.marbella.adaptadores.marca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.Marca

class MarcaAdapter (var marcaList: List<Marca> = emptyList()) : RecyclerView.Adapter<MarcaViewHolder> () {

    fun actualizarListaMarca(lista : List<Marca>) {
        marcaList = lista
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcaViewHolder {
        val marcaViewHolder = MarcaViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.list_marca, parent, false)
        )
        return  marcaViewHolder
    }

    override fun getItemCount(): Int {
        return marcaList.size
    }

    override fun onBindViewHolder(holder: MarcaViewHolder, position: Int) {
        holder.render(marcaList[position])
    }


}