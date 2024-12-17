package test_kotlin.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import test_kotlin.backend.entity.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByUsername(username: String): User?
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
}