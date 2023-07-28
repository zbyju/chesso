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

}
