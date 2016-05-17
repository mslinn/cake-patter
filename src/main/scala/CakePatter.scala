object ComponentRegistry extends UserServiceComponent with UserRepositoryComponent{
  val userRepository = new UserRepositoryAltImpl
  val userService = new UserService
}

trait UserRepositoryComponent{
  val userRepository: UserRepository
}

trait UserRepository{
  def authenticate(username: String, password:String): User

  def create(user: User)

  def delete(user: User)
}

class UserRepositoryImpl extends UserRepository {
  def authenticate(username: String, password:String): User = {
    println("authenticating user: " + username + " " + password)
    User(username, password)
   }

  def create(user: User) = println("creating user: " + user)

  def delete(user: User) = println("deleting user: " + user)
}

class UserRepositoryAltImpl extends UserRepository {
  def authenticate(username: String, password:String): User = {
    println("ALTERNATE: authenticating user: " + username + " " + password)
    User(username, password)
   }

  def create(user: User) = println("ALTERNATE: creating user: " + user)

  def delete(user: User) = println("ALTERNATE: deleting user: " + user)
}


trait UserServiceComponent{ this: UserRepositoryComponent =>
  val userService: UserService

  class UserService {
    def authenticate(username: String, password: String): User =
      userRepository.authenticate(username, password)

    def create(username: String, password: String) =
      userRepository.create(new User(username, password))

    def delete(user: User) =
      userRepository.delete(user)
  }
}

case class User(fname: String, lname: String)


object Main extends App {
  val userService = ComponentRegistry.userService
  userService.create("steve", "password")
}
