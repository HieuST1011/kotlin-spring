package test_kotlin.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import test_kotlin.backend.entity.Role
import test_kotlin.backend.entity.RoleType

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByRoleType(name: RoleType): Role?
}