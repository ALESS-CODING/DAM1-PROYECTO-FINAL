package pe.com.marbella.adaptadores.usuario

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.Usuario

class UsuarioAdapter(
    private var usuarioList: List<Usuario> = emptyList(), private var onItemSelected: (codigo: Long) -> Unit
    ) :
    RecyclerView.Adapter<UsuarioViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        var usuarioViewHolder = UsuarioViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_usuario, parent, false)
        )
        return  usuarioViewHolder
    }

    override fun getItemCount(): Int {
        return usuarioList.size
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.render(usuarioList[position], onItemSelected)
    }

    fun actualizarUsuarioList(lista: List<Usuario>){
        usuarioList = lista
    }
}