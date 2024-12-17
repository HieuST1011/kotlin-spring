package test_kotlin.backend.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "permission")
class Permission(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val permissionType: PermissionType,
) : BaseEntity() {
    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    var roles: MutableList<Role> = mutableListOf()

    override fun toString(): String {
        return "Permission(id=$id, permissionType=$permissionType)"
    }
}

enum class PermissionType {
    CREATE, READ, UPDATE, DELETE
}