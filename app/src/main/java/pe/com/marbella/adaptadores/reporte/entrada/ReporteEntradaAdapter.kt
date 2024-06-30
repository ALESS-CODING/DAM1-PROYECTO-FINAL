package pe.com.marbella.adaptadores.reporte.entrada

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.marbella.R
import pe.com.marbella.data.model.Entrada

class ReporteEntradaAdapter (private var listaEntrada : List<Entrada> = emptyList()) : RecyclerView.Adapter<ReporteEntradaViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteEntradaViewHolder {
        val reporteEntradaViewHolder = ReporteEntradaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_reporte_entrada, parent, false))
        return reporteEntradaViewHolder
    }

    override fun getItemCount(): Int {
        return listaEntrada.size
    }

    override fun onBindViewHolder(holder: ReporteEntradaViewHolder, position: Int) {
        holder.render(listaEntrada[position])
    }

    fun actualizarReporteSalidadAdapter(lista: List<Entrada>) {
        listaEntrada = lista
        notifyDataSetChanged()
    }
}