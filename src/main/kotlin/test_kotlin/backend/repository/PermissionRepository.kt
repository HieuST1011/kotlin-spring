package test_kotlin.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import test_kotlin.backend.entity.Permission
import test_kotlin.backend.entity.PermissionType

@Repository
interface PermissionRepository : JpaRepository<Permission, Long> {
    fun findPermissionByPermissionType(name: PermissionType): Permission?
}