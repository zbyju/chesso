import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import model.Coords
import model.BitPosition

class BitPositionTest extends AnyFlatSpec with Matchers {

  "BitPosition" should "be created with valid long" in {
    BitPosition(0) shouldBe defined
    BitPosition(63) shouldBe defined
  }

  it should "not be created with invalid long" in {
    BitPosition(80) shouldBe None
    BitPosition(-1) shouldBe None
    BitPosition(64) shouldBe None
  }

  it should "be created from valid coords" in {
    val a1 = Coords.fromString("a1").get
    val h8 = Coords.fromString("h8").get

    BitPosition.fromCoords(a1) shouldBe BitPosition(0).get
    BitPosition.fromCoords(h8) shouldBe BitPosition(63).get
  }

  it should "not be created from invalid coords" in {
    val c1 = Coords.fromString("i1")
    val c2 = Coords.fromString("a9")
    val c3 = Coords.fromString("a")
    val c4 = Coords.fromString("abc")

    c1.isEmpty shouldBe true
    c2.isEmpty shouldBe true
    c3.isEmpty shouldBe true
    c4.isEmpty shouldBe true
  }

  it should "convert to coords and back" in {
    val positions = Seq(0, 63, 1, 60, 32, 13)
    positions.foreach { pos =>
      val position = BitPosition.unsafeApply(pos)
      val coords = position.toCoords()
      position shouldBe BitPosition.fromCoords(coords)
    }
  }
}
