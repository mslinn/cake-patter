import org.scalatest.WordSpec

class TestyMcTestFace extends WordSpec {
  import ComponentRegistry._

  val userService = new UserService

  "Test" should {
    "work" in {
      val user = new User("test", "test")
      userRepository.authenticate(user) === user
    }
  }
}
