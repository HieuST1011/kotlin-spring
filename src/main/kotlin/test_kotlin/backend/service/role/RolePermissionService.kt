package test_kotlin.backend.service.role

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test_kotlin.backend.entity.Permission
import test_kotlin.backend.entity.PermissionType
import test_kotlin.backend.entity.Role
import test_kotlin.backend.entity.RoleType
import test_kotlin.backend.repository.PermissionRepository
import test_kotlin.backend.repository.RoleRepository

@Service
class RolePermissionService(
    @Autowired private val permissionRepository: PermissionRepository,
    @Autowired private val roleRepository: RoleRepository
) {
    @Transactional
    fun initializeRolesAndPermissions() {
        // Define permissions
        val permissionNames = PermissionType.entries.toTypedArray()

        // Fetch or create each permission to ensure uniqueness
        val permissionsMap = mutableMapOf<PermissionType, Permission>()
        for (permName in permissionNames) {
            var permission = permissionRepository.findPermissionByPermissionType(permName)
            if (permission == null) {
                permission = permissionRepository.save(Permission(permissionType = permName))
                permissionRepository.save((permission))
            }
            permissionsMap[permName] = permission
        }

        // Define roles with specific permissions
        val rolesData = listOf(
            RoleType.ADMIN to listOf(
                permissionsMap[PermissionType.CREATE]!!,
                permissionsMap[PermissionType.READ]!!,
                permissionsMap[PermissionType.UPDATE]!!,
                permissionsMap[PermissionType.DELETE]!!
            ),
            RoleType.EDITOR to listOf(
                permissionsMap[PermissionType.READ]!!,
                permissionsMap[PermissionType.UPDATE]!!,
                permissionsMap[PermissionType.DELETE]!!
            ),
            RoleType.USER to listOf(
                permissionsMap[PermissionType.READ]!!,
                permissionsMap[PermissionType.UPDATE]!!
            )
        )

        // Save each role with its permissions
        for (roleData in rolesData) {
            var role = roleRepository.findByRoleType(roleData.first)
            if (role == null) {
                role = Role(roleType = roleData.first)
                role.permissions = roleData.second.toMutableList() // Set of permissions
                roleRepository.save(role)
            }
        }
    }
}
