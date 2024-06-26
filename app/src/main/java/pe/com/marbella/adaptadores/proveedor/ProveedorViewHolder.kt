package pe.com.marbella.adaptadores.proveedor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.databinding.ListProveedorBinding

class ProveedorViewHolder( view: View) : RecyclerView.ViewHolder(view) {

    private var binding: ListProveedorBinding = ListProveedorBinding.bind(view)
    fun render(proveedor: Proveedor, onItemSelected: (codigo: Long) -> Unit) {
        binding.lblIdProv.text =  proveedor.codigo.toString()
        binding.lblNombreProv.text = proveedor.nombre
        binding.lblTelefProv.text = proveedor.telefono
        binding.lblCorreoProv.text = proveedor.nomRepresentante
        binding.lblCorreoProv.text = proveedor.correo

        binding.lyListaProveedor.setOnClickListener{onItemSelected(proveedor.codigo)}
    }
}