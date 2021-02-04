package m.n.dailyforecast.data
import m.n.dailyforecast.data.ResultViewState.Success
import java.io.IOException

sealed class ResultViewState<out R> {

    data class Success<out T>(val data: T) : ResultViewState<T>()
    data class Error(val exception: AppError) : ResultViewState<Nothing>()
    object Loading : ResultViewState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [ResultViewState] is of type [Success] & holds non-null [Success.data].
 */
val ResultViewState<*>.succeeded
    get() = this is Success && data != null

class AppError(cause: Throwable?) : Exception(cause) {
    constructor(message: String) : this(Exception(message))

    val messageError: String
        get() {
            if (cause is IOException) {
                return "No network available, please check your WiFi or Data connection"
            }
            return cause?.message ?: "Ops! There is something wrong"
        }
}