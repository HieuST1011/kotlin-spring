package test_kotlin.backend.common.response

import org.springframework.http.HttpStatus

object ApiResponseFactory {

    fun <T> success(
        data: T? = null,
        message: String = "Operation successful",
        status: HttpStatus = HttpStatus.OK
    ): ApiResponse<T> {
        return ApiResponse(
            status = status.value().toString(), // Use the status code as a string
            message = message,
            data = data
        )
    }

    fun <T> error(
        status: HttpStatus,
        message: String? = null,
        errorDetails: String? = null
    ): ApiResponse<T> {
        return ApiResponse(
            status = status.value().toString(), // Use the status code as a string
            message = message ?: getDefaultErrorMessage(status),
            error = errorDetails
        )
    }

    private fun getDefaultErrorMessage(status: HttpStatus): String {
        return when (status) {
            HttpStatus.BAD_REQUEST -> "Bad request"
            HttpStatus.UNAUTHORIZED -> "Unauthorized access"
            HttpStatus.FORBIDDEN -> "Forbidden"
            HttpStatus.NOT_FOUND -> "Resource not found"
            HttpStatus.INTERNAL_SERVER_ERROR -> "An unexpected error occurred"
            HttpStatus.CONFLICT -> "Conflict occurred"
            else -> "Error occurred"
        }
    }
}