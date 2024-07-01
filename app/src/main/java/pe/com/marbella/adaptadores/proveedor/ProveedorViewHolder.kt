package pe.com.marbella.adaptadores.proveedor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.databinding.ListProveedorBinding

class ProveedorViewHolder( view: View) : RecyclerView.ViewHolder(view) {

    private var binding: ListProveedorBinding = ListProveedorBinding.bind(view)
    fun render(proveedor: Proveedor, onItemSelected: (codigo: Long) -> Unit) {
        binding.lblNombreProv.text = proveedor.nombre
        binding.lblTelefProv.text = proveedor.telefono
        binding.lblCorreoProv.text = proveedor.correo
        binding.lblDireccion.text = proveedor.direccion
        binding.lblRepresentante.text = proveedor.nomRepresentante

        binding.lyListaProveedor.setOnClickListener{onItemSelected(proveedor.idProveedor)}
    }
}