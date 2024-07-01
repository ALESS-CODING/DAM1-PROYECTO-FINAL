package pe.com.marbella.adaptadores.entrada

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.DetalleEntrada

class DetalleEntradaAdapter( private var listaDetalleEntrada: List<DetalleEntrada> = emptyList()): RecyclerView.Adapter<DetalleEntradaViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleEntradaViewHolder {
        val detalleEntradaViewHolder = DetalleEntradaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_detalle_entrada, parent, false))
        return detalleEntradaViewHolder
    }

    override fun getItemCount(): Int {
        return listaDetalleEntrada.size
    }

    override fun onBindViewHolder(holder: DetalleEntradaViewHolder, position: Int) {
        holder.render(listaDetalleEntrada[position])
    }

    fun actualizarListaDetalleEntrada(lista: MutableList<DetalleEntrada>) {
        listaDetalleEntrada = lista
        notifyDataSetChanged()
    }
}