import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import model.Coords
import scala.util.Failure

class CoordsTest extends AnyFlatSpec with Matchers {

  "Coords" should "be able to be created" in {
    val c1 = Coords(0, 0).get
    val c2 = Coords(0, 7).get
    val c3 = Coords(7, 7).get
    val c4 = Coords(7, 0).get
    val c5 = Coords(3, 4).get
    assert(c1.rank == 0 && c1.file == 0)
    assert(c2.rank == 0 && c2.file == 7)
    assert(c3.rank == 7 && c3.file == 7)
    assert(c4.rank == 7 && c4.file == 0)
    assert(c5.rank == 3 && c5.file == 4)
  }

  it should "return None when rank or file is out of board (8x8)" in {
    assert(Coords(8, 0) == None)
    assert(Coords(0, 8) == None)
    assert(Coords(8, 8) == None)
    assert(Coords(-1, 0) == None)
    assert(Coords(0, -1) == None)
    assert(Coords(Byte.MinValue, 0) == None)
    assert(Coords(Byte.MaxValue, 0) == None)
    assert(Coords(0, Byte.MinValue) == None)
    assert(Coords(0, Byte.MinValue) == None)
  }

  "toString" should "return the string corresponding to the square in the algebraic notation" in {
    assert(Coords(0, 0).get.toString == "a1")
    assert(Coords(0, 1).get.toString == "b1")
    assert(Coords(0, 2).get.toString == "c1")
    assert(Coords(0, 3).get.toString == "d1")
    assert(Coords(0, 4).get.toString == "e1")
    assert(Coords(0, 5).get.toString == "f1")
    assert(Coords(0, 6).get.toString == "g1")
    assert(Coords(0, 7).get.toString == "h1")

    assert(Coords(0, 0).get.toString == "a1")
    assert(Coords(1, 0).get.toString == "a2")
    assert(Coords(2, 0).get.toString == "a3")
    assert(Coords(3, 0).get.toString == "a4")
    assert(Coords(4, 0).get.toString == "a5")
    assert(Coords(5, 0).get.toString == "a6")
    assert(Coords(6, 0).get.toString == "a7")
    assert(Coords(7, 0).get.toString == "a8")

    assert(Coords(1, 1).get.toString == "b2")
    assert(Coords(3, 4).get.toString == "e4")
    assert(Coords(7, 7).get.toString == "h8")
  }

  it should "compare 2 Coords based on the rank and file" in {
    assert(Coords(0, 0) == Coords(0, 0))
    assert(Coords(1, 0) == Coords(1, 0))
    assert(Coords(3, 4) == Coords(3, 4))
    assert(Coords(7, 7) == Coords(7, 7))
  }

  "fromString" should "create Coords if provided algebraic notation coords" in {
    assert(Coords.fromString("a1").get == Coords(0, 0).get)
    assert(Coords.fromString("a2").get == Coords(1, 0).get)
    assert(Coords.fromString("b1").get == Coords(0, 1).get)
    assert(Coords.fromString("d4").get == Coords(3, 3).get)
    assert(Coords.fromString("e4").get == Coords(3, 4).get)
    assert(Coords.fromString("h8").get == Coords(7, 7).get)
  }

  "fromString" should "return None if the string is not valid algebraic notation coords" in {
    assert(Coords.fromString("") == None)
    assert(Coords.fromString("a") == None)
    assert(Coords.fromString("1") == None)
    assert(Coords.fromString("a9") == None)
    assert(Coords.fromString("a0") == None)
    assert(Coords.fromString("`1") == None)
    assert(Coords.fromString("i1") == None)
  }

  "moveVertical" should "return the tile within the board with positive and negative offset" in {
    val a1 = Coords.fromString("a1").get
    val a8 = Coords.fromString("a8").get
    val d4 = Coords.fromString("d4").get
    val d5 = Coords.fromString("d5").get
    assert(d4.moveVertical(1).get == d5)
    assert(d5.moveVertical(-1).get == d4)
    assert(a1.moveVertical(7).get == a8)
    assert(a8.moveVertical(-7).get == a1)
  }

  "moveVertical" should "return None if the coords fall outside the board" in {
    val a1 = Coords.fromString("a1").get
    val a8 = Coords.fromString("a8").get
    val d4 = Coords.fromString("d4").get
    assert(a1.moveVertical(-1) == None)
    assert(a8.moveVertical(1) == None)
    assert(d4.moveVertical(-4) == None)
    assert(d4.moveVertical(5) == None)
    assert(d4.moveVertical(15) == None)
    assert(d4.moveVertical(-15) == None)
    assert(d4.moveVertical(Byte.MaxValue) == None)
    assert(d4.moveVertical(Byte.MinValue) == None)
  }

  "moveHorizontal" should "return the tile within the board with positive and negative offset" in {
    val a1 = Coords.fromString("a1").get
    val b1 = Coords.fromString("b1").get
    val h1 = Coords.fromString("h1").get
    val g8 = Coords.fromString("g8").get
    val h8 = Coords.fromString("h8").get
    val d4 = Coords.fromString("d4").get
    val e4 = Coords.fromString("e4").get
    assert(a1.moveHorizontal(1).get == b1)
    assert(b1.moveHorizontal(-1).get == a1)
    assert(a1.moveHorizontal(7).get == h1)
    assert(h1.moveHorizontal(-7).get == a1)
    assert(g8.moveHorizontal(1).get == h8)
    assert(h8.moveHorizontal(-1).get == g8)
    assert(d4.moveHorizontal(1).get == e4)
    assert(e4.moveHorizontal(-1).get == d4)
  }

  "moveHorizontal" should "return None if the coords fall outside the board" in {
    val a1 = Coords.fromString("a1").get
    val d4 = Coords.fromString("d4").get
    val h8 = Coords.fromString("h8").get
    assert(a1.moveHorizontal(-1) == None)
    assert(h8.moveHorizontal(1) == None)
    assert(d4.moveHorizontal(-4) == None)
    assert(d4.moveHorizontal(5) == None)
    assert(d4.moveHorizontal(-40) == None)
    assert(d4.moveHorizontal(50) == None)
    assert(d4.moveHorizontal(Byte.MaxValue) == None)
    assert(d4.moveHorizontal(Byte.MinValue) == None)
  }
}
