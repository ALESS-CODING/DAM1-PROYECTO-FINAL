package pe.com.marbella.adaptadores.salida

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.DetalleSalida

class DetalleSalidaAdapter (private var listaDetalleSalida: List<DetalleSalida> = emptyList()): RecyclerView.Adapter<DetalleSalidaViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleSalidaViewHolder {
        val detalleSalidaViewHolder = DetalleSalidaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_detalle_salida, parent, false) )
        return detalleSalidaViewHolder
    }

    override fun getItemCount(): Int {
        return listaDetalleSalida.size
    }

    override fun onBindViewHolder(holder: DetalleSalidaViewHolder, position: Int) {
        holder.render(listaDetalleSalida[position])
    }

    fun actualizarListaDetalleSalida(lista: List<DetalleSalida>) {
        listaDetalleSalida = lista
        notifyDataSetChanged()
    }
}