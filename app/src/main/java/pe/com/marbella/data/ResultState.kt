package pe.com.marbella.data
sealed class ResultState <out T: Any> {

     data class Loading <out T: Any> (val data: T): ResultState<T> ()
     data class Delete< T: Any> (val data: T): ResultState<T> ()
     data class Update<out T: Any> (val data: T): ResultState<T> ()
     data class Save<out T: Any> (val data: T): ResultState<T> ()
     data class FindById<out T: Any> (val data: T): ResultState<T> ()
    data class Error(val error: String) : ResultState<Nothing>()
}