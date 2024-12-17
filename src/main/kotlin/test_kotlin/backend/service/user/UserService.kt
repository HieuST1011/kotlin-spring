package test_kotlin.backend.service.user

import test_kotlin.backend.dto.user.CreateUserDto
import test_kotlin.backend.dto.user.UserResponse

interface UserService {
    fun getUserByUserName(username: String): UserResponse?
    fun createUser(newUser: CreateUserDto)
    fun getAllUsers(): List<UserResponse>
}