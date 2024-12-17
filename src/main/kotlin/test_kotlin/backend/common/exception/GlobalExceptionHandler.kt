package test_kotlin.backend.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import test_kotlin.backend.common.response.ApiResponse
import test_kotlin.backend.common.response.ApiResponseFactory


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomAppException::class)
    fun handleCustomException(ex: CustomAppException): ResponseEntity<ApiResponse<Nothing>> {
        val response = ApiResponseFactory.error<Nothing>(
            status = ex.status,
            message = ex.message,
            errorDetails = ex.details
        )
        return ResponseEntity.status(ex.status).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ApiResponse<Nothing>> {
        val response = ApiResponseFactory.error<Nothing>(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            errorDetails = ex.localizedMessage
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}
