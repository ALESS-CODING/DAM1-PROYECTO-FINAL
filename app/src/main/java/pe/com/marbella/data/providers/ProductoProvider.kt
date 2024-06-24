package pe.com.marbella.data.providers

import pe.com.marbella.data.model.Categoria
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.UnidadMedida
import java.math.BigDecimal
import javax.inject.Inject

class ProductoProvider @Inject constructor() {

    fun getAllsProducts (): List<Producto> {
        return listOf<Producto>(Producto(1, "alex", "desripion", 3, 6, BigDecimal.valueOf(16), UnidadMedida(1, ""), Categoria(1, ""), Marca(1, "")))
    }
}