package test_kotlin.backend.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import test_kotlin.backend.service.role.RolePermissionService

@Component
class RolePermissionInitializationRunner(
    @Autowired private val rolePermissionService: RolePermissionService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        rolePermissionService.initializeRolesAndPermissions()
    }
}