package test_kotlin.backend.service.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import test_kotlin.backend.dto.user.CreateUserDto
import test_kotlin.backend.dto.user.UserResponse
import test_kotlin.backend.entity.RoleType
import test_kotlin.backend.entity.User
import test_kotlin.backend.repository.RoleRepository
import test_kotlin.backend.repository.UserRepository

@Service
class UserServiceImpl(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val roleRepository: RoleRepository
) : UserService {
    override fun createUser(newUser: CreateUserDto) {
        require(!userRepository.existsByEmail(newUser.email)) {
            "Email '${newUser.email}' already in use"
        }

        val defaultRole = requireNotNull(roleRepository.findByRoleType(RoleType.USER)) {
            "Role 'USER' not found"
        }

        val passwordEncoder = BCryptPasswordEncoder()
        val hashedPassword = passwordEncoder.encode(newUser.password)

        val user = User(
            username = newUser.username,
            password = hashedPassword, // Password is validated before reaching here
            email = newUser.email,
            role = defaultRole,
        )

        userRepository.save(user)
    }

    override fun getUserByUserName(username: String): UserResponse? {
        val user = requireNotNull(userRepository.findByUsername(username)) {
            "User not found"
        }
        return toUserResponse(user)
    }

    override fun getAllUsers(): List<UserResponse> {
        val users = userRepository.findAll()
        return users.map { toUserResponse(it) }
    }

    fun toUserResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id,
            username = user.username,
            email = user.email,
            createdAt = user.createdAt,
            roleName = user.role.roleType // Assuming Role has a 'name' property
        )
    }
}