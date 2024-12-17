package test_kotlin.backend.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import test_kotlin.backend.entity.RoleType
import java.time.LocalDateTime
import java.util.*

data class UserResponse(
    val id: UUID?,
    val username: String,
    @JsonProperty("email") val email: String,
    val createdAt: LocalDateTime,
    val roleName: RoleType
)
