package model

final case class BitArray(private val bits: Long = 0L) {

  // Set a bit at a specific position (0-63)
  def set(pos: BitPosition): BitArray = BitArray(bits | (1L << pos))

  // Set multiple bits at positions
  def setMultiple(positions: BitPosition*): BitArray = {
    val newBits = positions.foldLeft(bits)((acc, pos) => {
      acc | (1L << pos)
    })
    BitArray(newBits)
  }

  // Reset (clear) a bit at a specific position (0-63)
  def reset(pos: BitPosition): BitArray = BitArray(bits & ~(1L << pos))

  // Reset multiple bits at specified positions
  def resetMultiple(positions: BitPosition*): BitArray = {
    val newBits = positions.foldLeft(bits)((acc, pos) => {
      acc & ~(1L << pos)
    })
    BitArray(newBits)
  }

  // Set or reset a bit at a specific position based on the boolean argument
  def setOrReset(pos: BitPosition, value: Boolean): BitArray = {
    if (value) set(pos) else reset(pos)
  }

  // Set or reset multiple bits based on their corresponding boolean values
  def setOrResetMultiple(positions: Seq[(BitPosition, Boolean)]): BitArray = {
    val newBits = positions.foldLeft(bits) { case (acc, (pos, value)) =>
      if (value) acc | (1L << pos) else acc & ~(1L << pos)
    }
    BitArray(newBits)
  }

  // Get the value of a bit at a specific position (0-63)
  def get(pos: BitPosition): Boolean = {
    (bits & (1L << pos)) != 0
  }

  // Count the number of bits set to 1 (Hamming weight / population count)
  def countSetBits(): Int = java.lang.Long.bitCount(bits)

  // Check if for all specified positions the bits are 1
  def allSet(positions: BitPosition*): Boolean = positions.forall { pos =>
    get(pos)
  }

  // Check if for all specified positions the bits are 0
  def allReset(positions: BitPosition*): Boolean = positions.forall { pos =>
    !get(pos)
  }

  // Get positions of all 1 bits
  def positionsOfSetBits(): Seq[BitPosition] =
    (BitPosition.unsafeApply(0).to(BitPosition.unsafeApply(63))).filter(get)

  // Get positions of all 0 bits
  def positionsOfResetBits(): Seq[BitPosition] =
    (BitPosition.unsafeApply(0).to(BitPosition.unsafeApply(63))).filterNot(get)

  // Invert all bits (flip 0s to 1s and vice versa)
  def invert(): BitArray = BitArray(~bits)

  // Get the internal bit representation as a Long
  val toLong: Long = bits

  // Get a binary string representation of the bit array
  override def toString: String = f"$bits%64s".replace(' ', '0')
}

object BitArray {
  // Constructor that takes several positions and sets the corresponding bits to 1
  def apply(positions: BitPosition*) = {
    val bitArray = positions.foldLeft(0L)((acc, pos) => {
      require(pos >= 0 && pos < 64, s"Position $pos must be between 0 and 63")
      acc | (1L << pos)
    })
    BitArray.fromLong(bitArray)
  }

  // Create a BitArray from a Long value
  def fromLong(value: Long): BitArray = BitArray(value)

  // Create an empty BitArray (all bits set to 0)
  def empty: BitArray = BitArray(0L)
}
