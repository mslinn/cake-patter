import org.scalatest.WordSpec

class TestyMcTestFace extends WordSpec {
  import ComponentRegistry._

  // create a fresh and clean (non-mock) UserService
  // (who's userRepository is still a mock)
  val userService = new UserService

  "Test" should {
    "work" in {
      val user = new User("test", "test")
//      userRepository.authenticate(user.username, user.password) === user
    }
  }
}
