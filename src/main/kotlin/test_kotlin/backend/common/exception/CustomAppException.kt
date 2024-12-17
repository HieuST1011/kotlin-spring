package test_kotlin.backend.common.exception

import org.springframework.http.HttpStatus

class CustomAppException(
    val status: HttpStatus,
    message: String,
    val details: String? = null
) : RuntimeException(message)
