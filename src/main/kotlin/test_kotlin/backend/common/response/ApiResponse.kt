package test_kotlin.backend.common.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse<T>(
    val status: String,
    val message: String? = null,
    val data: T? = null,
    val error: String? = null
)
