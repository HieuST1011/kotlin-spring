package test_kotlin.backend.service.user

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import test_kotlin.backend.repository.UserRepository

typealias ApplicationUser = test_kotlin.backend.entity.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found!")

    private fun ApplicationUser.mapToUserDetails(): UserDetails {
        val authorities = this.role.permissions
            .map { permission -> "PERMISSION_${permission.permissionType}" }
            .toMutableList()

        // Include the role as an authority as well
        authorities.add("ROLE_${this.role.roleType}")

        return User.builder()
            .username(this.email) // or use this.username if preferred
            .password(this.password) // Ensure password is hashed when stored in the database
            .authorities(*authorities.toTypedArray()) // Convert list to array for authorities
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .build()
    }
}