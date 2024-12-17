package test_kotlin.backend.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "role")
class Role(
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    val roleType: RoleType,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    var permissions: MutableList<Permission> = mutableListOf(),

    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    open var users: MutableList<User> = mutableListOf()  // Specify concrete type here
) : BaseEntity() {
    override fun toString(): String {
        return "Role(id=$id, roleType=$roleType)"
    }
}

enum class RoleType {
    USER, EDITOR, ADMIN
}
