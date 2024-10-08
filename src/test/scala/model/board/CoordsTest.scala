import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import model.board.Coords

class CoordsTest extends AnyFlatSpec with Matchers {

  "Coords" should "be created with valid rank and file" in {
    Coords(0, 0) shouldBe defined
    Coords(7, 7) shouldBe defined
  }

  it should "not be created with invalid rank or file" in {
    Coords(-1, 0) shouldBe None
    Coords(0, -1) shouldBe None
    Coords(8, 0) shouldBe None
    Coords(0, 8) shouldBe None
  }

  it should "be created from valid string" in {
    Coords.fromString("a1") shouldBe defined
    Coords.fromString("a1") shouldBe Coords(0, 0)
    Coords.fromString("h8") shouldBe defined
    Coords.fromString("h8") shouldBe Coords(7, 7)
    Coords.fromString("b3") shouldBe defined
    Coords.fromString("b3") shouldBe Coords(2, 1)
  }

  it should "not be created from invalid string" in {
    Coords.fromString("i1") shouldBe None
    Coords.fromString("a9") shouldBe None
    Coords.fromString("a") shouldBe None
    Coords.fromString("abc") shouldBe None
  }

  it should "move vertically" in {
    val c = Coords(3, 3).get
    c.moveVertical(1) shouldBe Coords(4, 3)
    c.moveVertical(-1) shouldBe Coords(2, 3)
    c.moveVertical(4) shouldBe Coords(7, 3)
    c.moveVertical(-3) shouldBe Coords(0, 3)
    c.moveVertical(5) shouldBe None
    c.moveVertical(-4) shouldBe None
  }

  it should "move horizontally" in {
    val c = Coords(3, 3).get
    c.moveHorizontal(1) shouldBe Coords(3, 4)
    c.moveHorizontal(-1) shouldBe Coords(3, 2)
    c.moveHorizontal(4) shouldBe Coords(3, 7)
    c.moveHorizontal(-3) shouldBe Coords(3, 0)
    c.moveHorizontal(5) shouldBe None
    c.moveHorizontal(-4) shouldBe None
  }

  it should "move positive diagonally" in {
    val c = Coords(3, 3).get
    c.movePositiveDiagonal(1) shouldBe Coords(4, 4)
    c.movePositiveDiagonal(-1) shouldBe Coords(2, 2)
    c.movePositiveDiagonal(4) shouldBe Coords(7, 7)
    c.movePositiveDiagonal(-3) shouldBe Coords(0, 0)
    c.movePositiveDiagonal(5) shouldBe None
    c.movePositiveDiagonal(-4) shouldBe None
  }

  it should "move negative diagonally" in {
    val c = Coords(3, 3).get
    c.moveNegativeDiagonal(1) shouldBe Coords(4, 2)
    c.moveNegativeDiagonal(-1) shouldBe Coords(2, 4)
    c.moveNegativeDiagonal(3) shouldBe Coords(6, 0)
    c.moveNegativeDiagonal(-3) shouldBe Coords(0, 6)
    c.moveNegativeDiagonal(4) shouldBe None
    c.moveNegativeDiagonal(-4) shouldBe None
  }

  it should "move as a knight" in {
    val c = Coords(3, 3).get
    c.moveKnight(0) shouldBe Coords(5, 4)
    c.moveKnight(1) shouldBe Coords(4, 5)
    c.moveKnight(2) shouldBe Coords(2, 5)
    c.moveKnight(3) shouldBe Coords(1, 4)
    c.moveKnight(4) shouldBe Coords(1, 2)
    c.moveKnight(5) shouldBe Coords(2, 1)
    c.moveKnight(6) shouldBe Coords(4, 1)
    c.moveKnight(7) shouldBe Coords(5, 2)

    an[IllegalArgumentException] should be thrownBy c.moveKnight(-1)
    an[IllegalArgumentException] should be thrownBy c.moveKnight(8)
  }

  it should "convert to string correctly" in {
    Coords(0, 0).get.toString shouldBe "a1"
    Coords(7, 7).get.toString shouldBe "h8"
    Coords(2, 4).get.toString shouldBe "e3"
  }

  it should "handle edge cases for all move methods" in {
    val corner = Coords(0, 0).get
    corner.moveVertical(-1) shouldBe None
    corner.moveHorizontal(-1) shouldBe None
    corner.movePositiveDiagonal(-1) shouldBe None
    corner.moveNegativeDiagonal(-1) shouldBe None

    (0 until 8)
      .map(i => corner.moveKnight(i.toByte))
      .count(_.isEmpty) shouldBe 6

    val topRight = Coords(7, 7).get
    topRight.moveVertical(1) shouldBe None
    topRight.moveHorizontal(1) shouldBe None
    topRight.movePositiveDiagonal(1) shouldBe None
    topRight.moveNegativeDiagonal(1) shouldBe None

    (0 until 8)
      .map(i => topRight.moveKnight(i.toByte))
      .count(_.isEmpty) shouldBe 6
  }
}
