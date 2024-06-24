package pe.com.marbella.data.providers

import pe.com.marbella.data.model.Marca
import javax.inject.Inject

class MarcaProvider @Inject constructor(){
    fun getAllMarca (): List<Marca> {
        return listOf(
            Marca(1L, "Inka Cola"),
            Marca(2, "Gloria")
        )
    }
}