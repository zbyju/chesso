package model.board

case class Coords private (rank: Byte, file: Byte) {
  require(rank >= 0 && rank <= 7 && file >= 0 && file <= 7)

  /** Get new coords moved from this coords up (positive) or down (negative) in
    * a vertical direction (|)
    */
  def moveVertical(by: Byte = 0): Option[Coords] =
    Coords((rank + by).toByte, file)

  /** Get new coords moved from this coords right (positive) or left (negative)
    * in a vertical direction (-)
    */
  def moveHorizontal(by: Byte = 0): Option[Coords] =
    Coords(rank, (file + by).toByte)

  /** Get new coords moved from this coords up+right (positive) or left+down
    * (negative) in a vertical direction (/)
    */
  def movePositiveDiagonal(by: Byte = 0): Option[Coords] =
    Coords((rank + by).toByte, (file + by).toByte)

  /** Get new coords moved from this coords up+left (positive) or down+right
    * (negative) in a vertical direction (\)
    */
  def moveNegativeDiagonal(by: Byte = 0): Option[Coords] =
    Coords((rank + by).toByte, (file - by).toByte)

  /** Get new coords moved from this coords to new coords according to the
    * knight jump. There are 8 possibilites, select the new coords using dir
    * clockwise, starting from top right (2 up 1 right and going clockwise).
    */
  def moveKnight(dir: Byte): Option[Coords] = {
    require(dir >= 0 && dir <= 7)
    val ys = Seq(2, 1, -1, -2, -2, -1, 1, 2)
    val xs = Seq(1, 2, 2, 1, -1, -2, -2, -1)
    Coords((rank + ys(dir)).toByte, (file + xs(dir)).toByte)
  }

  override def toString(): String = {
    val rankStr = (rank + 1).toString
    val fileStr = "abcdefgh".apply(file)
    fileStr + rankStr
  }
}

object Coords {
  def apply(rank: Byte, file: Byte): Option[Coords] = {
    if (rank < 0 || rank > 7 || file < 0 || file > 7) None
    else Some(new Coords(rank, file))
  }

  def unsafeApply(rank: Byte, file: Byte): Coords = {
    require(rank >= 0 && rank <= 7 && file >= 0 && file <= 7)
    new Coords(rank, file)
  }

  implicit def fromString(str: String): Option[Coords] =
    if (str.length != 2) None
    else
      Coords(
        (str(1).toInt - '1'.toInt).toByte,
        (str(0).toLower.toInt - 'a'.toInt).toByte
      )
}
