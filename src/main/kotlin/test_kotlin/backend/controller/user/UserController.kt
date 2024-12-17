package test_kotlin.backend.controller.user

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import test_kotlin.backend.common.response.ApiResponse
import test_kotlin.backend.common.response.ApiResponseFactory
import test_kotlin.backend.dto.user.CreateUserDto
import test_kotlin.backend.dto.user.UserResponse
import test_kotlin.backend.service.user.UserService

@RestController
@RequestMapping("/api/users")
@Validated
class UserController @Autowired constructor(
    private val userService: UserService // Spring will inject UserServiceImpl here
) {
    @PostMapping("/create")
    fun createUser(@RequestBody @Valid createUserDto: CreateUserDto): ApiResponse<Nothing> {
        userService.createUser(createUserDto)
        return ApiResponseFactory.success(message = "User completed successfully", status = HttpStatus.CREATED)
    }

    @GetMapping("/{username}")
    fun getUser(@PathVariable username: String): ApiResponse<UserResponse> {
        return ApiResponseFactory.success(userService.getUserByUserName(username))
    }

    @GetMapping
    fun getAllUsers(): ApiResponse<List<UserResponse>> {
        return ApiResponseFactory.success(userService.getAllUsers())
    }
}