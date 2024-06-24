package pe.com.marbella.adaptadores.marca

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Marca
import pe.com.marbella.databinding.ListMarcaBinding

class MarcaViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private var binding : ListMarcaBinding = ListMarcaBinding.bind(view)
    fun render(marca: Marca, onItemSelected: (codigo: Long) -> Unit) {
        binding.lblIdMarca.text = marca.codigo.toString()
        binding.lblNombreMarca.text = marca.descripcion

        binding.lyListaMarca.setOnClickListener{
            onItemSelected(marca.codigo)
        }
    }
}