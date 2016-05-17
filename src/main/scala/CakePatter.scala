class User(val username: String, val password: String)

trait UserRepositoryComponent {
  val userRepository: UserRepository

  class UserRepository {
    def authenticate(user: User): User = {
      println("authenticating user: " + user)
      user
    }
    def create(user: User) = println("creating user: " + user)
    def delete(user: User) = println("deleting user: " + user)
  }
}

trait UserServiceComponent { this: UserRepositoryComponent =>
  val userService: UserService

  class UserService {
    def authenticate(username: String, password: String): User =
      userRepository.authenticate(new User(username, password))
    def create(username: String, password: String) =
      userRepository.create(new User(username, password))
    def delete(user: User) = userRepository.delete(user)
  }
}

object ComponentRegistry extends UserServiceComponent with UserRepositoryComponent {
  val userRepository = new UserRepository
  val userService = new UserService
}

object Main extends App {
  val userService = ComponentRegistry.userService
  val user = userService.authenticate("x", "y")
  userService.create("x", "y")
  userService.delete(user)
}
