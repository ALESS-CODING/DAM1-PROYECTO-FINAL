package pe.com.marbella.adaptadores.usuario

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.data.model.Usuario
import pe.com.marbella.databinding.ListUsuarioBinding

class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding: ListUsuarioBinding = ListUsuarioBinding.bind(view)
    fun render(usuario: Usuario) {
        binding.lblIdUsu.text = usuario.codigo.toString()
        binding.lblNombreUsu.text = usuario.nombre
        binding.lblCorreoUsu.text = usuario.correo
    }
}