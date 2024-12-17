package test_kotlin.backend.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Column(unique = true, nullable = false)
    var username: String,

    @Column(nullable = false)
    @JsonProperty("password")
    var password: String,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false) // The foreign key column to `role`
    var role: Role,

    ) : BaseEntity() {
    override fun toString(): String {
        return "User(id=$id, username='$username', email='$email', createdAt=$createdAt, role='$role')"
    }
}