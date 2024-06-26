package pe.com.marbella.data.model

sealed class MarcaState {
    object Loading: MarcaState()
    data class Success(var marca : Marca): MarcaState()
    data class Error (var error: String): MarcaState()

}