package pe.com.marbella.adaptadores.reporte.salida

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.Salida

class ReporteSalidaAdapter (private var listaSalida : List<Salida> = emptyList()) : RecyclerView.Adapter<ReporteSalidaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteSalidaViewHolder {
        val reporteSalidaViewHolder = ReporteSalidaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_reporte_salida, parent, false))
        return reporteSalidaViewHolder
    }

    override fun getItemCount(): Int {
        return listaSalida.size
    }

    override fun onBindViewHolder(holder: ReporteSalidaViewHolder, position: Int) {
        holder.render(listaSalida[position])
    }

    fun actualizarListaSalida(lista: List<Salida>) {
        listaSalida = lista
        notifyDataSetChanged()
    }
}