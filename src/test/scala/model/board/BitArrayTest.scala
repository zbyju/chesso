package model.board

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import model.misc.BitArray

def binaryStringToLong(binary: String): Option[Long] = {
  if (binary.length == 64 && binary.forall(c => c == '0' || c == '1')) {
    Some(java.lang.Long.parseUnsignedLong(binary, 2))
  } else {
    None // Invalid input
  }
}

class BitArrayTest extends AnyFlatSpec with Matchers {

  "BitArray" should "be created empty" in {
    val empty = BitArray.empty
    empty.toLong shouldBe 0L
    empty.toString shouldBe "0" * 64
  }

  it should "be created with specific positions" in {
    val array =
      BitArray(BitPosition.unsafeApply(0), BitPosition.unsafeApply(63))

    (BitPosition.unsafeApply(1).to(BitPosition.unsafeApply(62))).foreach { i =>
      array.get(i) shouldBe false
    }
    array.get(BitPosition.unsafeApply(0)) shouldBe true
    array.get(BitPosition.unsafeApply(63)) shouldBe true
    array.toLong == binaryStringToLong("1" + ("0" * 62) + "1").get
  }

  it should "be created from Long" in {
    val array = BitArray.fromLong(5L) // 101 in binary
    array.get(BitPosition.unsafeApply(0)) shouldBe true
    array.get(BitPosition.unsafeApply(1)) shouldBe false
    array.get(BitPosition.unsafeApply(2)) shouldBe true
  }

  it should "set a bit" in {
    val array = BitArray.empty.set(BitPosition.unsafeApply(1))
    array.get(BitPosition.unsafeApply(1)) shouldBe true
    array.toLong shouldBe binaryStringToLong("0" * 62 + "1" + "0").get
  }

  it should "set multiple bits" in {
    val array = BitArray.empty.setMultiple(
      BitPosition.unsafeApply(1),
      BitPosition.unsafeApply(3),
      BitPosition.unsafeApply(5)
    )
    array.get(BitPosition.unsafeApply(1)) shouldBe true
    array.get(BitPosition.unsafeApply(3)) shouldBe true
    array.get(BitPosition.unsafeApply(5)) shouldBe true
    array.toLong shouldBe binaryStringToLong("0" * 58 + "101010").get
  }

  it should "reset a bit" in {
    val array = BitArray
      .fromLong(-1L)
      .reset(BitPosition.unsafeApply(1)) // -1L is all 1s
    array.get(BitPosition.unsafeApply(1)) shouldBe false
    array.toLong shouldBe binaryStringToLong("1" * 62 + "01").get
  }

  it should "reset multiple bits" in {
    val array = BitArray
      .fromLong(-1L)
      .resetMultiple(
        BitPosition.unsafeApply(1),
        BitPosition.unsafeApply(3),
        BitPosition.unsafeApply(5)
      )
    array.get(BitPosition.unsafeApply(1)) shouldBe false
    array.get(BitPosition.unsafeApply(3)) shouldBe false
    array.get(BitPosition.unsafeApply(5)) shouldBe false
    array.toLong shouldBe binaryStringToLong("1" * 58 + "010101").get
  }

  it should "set or reset a bit based on boolean value" in {
    val array = BitArray.empty
      .setOrReset(BitPosition.unsafeApply(1), true)
      .setOrReset(BitPosition.unsafeApply(2), false)
    array.get(BitPosition.unsafeApply(1)) shouldBe true
    array.get(BitPosition.unsafeApply(2)) shouldBe false
  }

  it should "set or reset multiple bits based on boolean values" in {
    val array = BitArray.empty.setOrResetMultiple(
      Seq(
        (BitPosition.unsafeApply(1), true),
        (BitPosition.unsafeApply(2), false),
        (BitPosition.unsafeApply(3), true)
      )
    )
    array.get(BitPosition.unsafeApply(1)) shouldBe true
    array.get(BitPosition.unsafeApply(2)) shouldBe false
    array.get(BitPosition.unsafeApply(3)) shouldBe true
  }

  it should "count set bits" in {
    BitArray.empty.countSetBits() shouldBe 0
    BitArray.fromLong(-1L).countSetBits() shouldBe 64
    BitArray.fromLong(5L).countSetBits() shouldBe 2 // 5 = 101
  }

  it should "check if all specified positions are set" in {
    val array = BitArray.fromLong(7L) // 111 in binary
    array.allSet(
      BitPosition.unsafeApply(0),
      BitPosition.unsafeApply(1),
      BitPosition.unsafeApply(2)
    ) shouldBe true
    array.allSet(
      BitPosition.unsafeApply(0),
      BitPosition.unsafeApply(1),
      BitPosition.unsafeApply(3)
    ) shouldBe false
  }

  it should "check if all specified positions are reset" in {
    val array = BitArray.fromLong(8L) // 1000 in binary
    array.allReset(
      BitPosition.unsafeApply(0),
      BitPosition.unsafeApply(1),
      BitPosition.unsafeApply(2)
    ) shouldBe true
    array.allReset(
      BitPosition.unsafeApply(0),
      BitPosition.unsafeApply(3)
    ) shouldBe false
  }

  it should "get positions of set bits" in {
    val array = BitArray.fromLong(11L) // 1011 in binary
    array.positionsOfSetBits() shouldBe Seq(
      BitPosition.unsafeApply(0),
      BitPosition.unsafeApply(1),
      BitPosition.unsafeApply(3)
    )
  }

  it should "get positions of reset bits" in {
    val array = BitArray.fromLong(11L) // 1011 in binary
    array.positionsOfResetBits().take(3) shouldBe Seq(
      BitPosition.unsafeApply(2),
      BitPosition.unsafeApply(4),
      BitPosition.unsafeApply(5)
    )
    array.positionsOfResetBits().length shouldBe 61
  }

  it should "invert all bits" in {
    val array = BitArray.fromLong(5L) // 101 in binary
    val inverted = array.invert()
    inverted.toLong shouldBe binaryStringToLong(
      ("1" * 61) + "0" + "1" + "0"
    ).get
    inverted.get(BitPosition.unsafeApply(0)) shouldBe false
    inverted.get(BitPosition.unsafeApply(1)) shouldBe true
    inverted.get(BitPosition.unsafeApply(2)) shouldBe false
  }

  it should "handle edge cases" in {
    val array = BitArray.empty.set(BitPosition.unsafeApply(63))
    array.get(BitPosition.unsafeApply(63)) shouldBe true
    array.toLong shouldBe (1L << 63)

    val fullArray = BitArray.fromLong(-1L)
    fullArray.countSetBits() shouldBe 64
    fullArray.positionsOfResetBits() shouldBe empty
  }
}
