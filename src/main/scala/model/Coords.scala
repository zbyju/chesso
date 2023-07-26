package model

case class Coords(rank: Byte, file: Byte) {
  require(rank >= 0 && rank <= 7 && file >= 0 && file <= 7)

  def moveVertical(by: Byte = 0): Coords = Coords((rank + by).toByte, file)
  def moveHorizontal(by: Byte = 0): Coords = Coords(rank, (file + by).toByte)
  def movePositiveDiagonal(by: Byte = 0): Coords =
    Coords((rank + by).toByte, (file + by).toByte)
  def moveNegativeDiagonal(by: Byte = 0): Coords =
    Coords((rank + by).toByte, (file - by).toByte)

  override def toString(): String = {
    val rankStr = (rank + 1).toString
    val fileStr = "abcdefgh".apply(file)
    fileStr + rankStr
  }

}

object Coords {
  def safeCreate(rank: Byte, file: Byte): Option[Coords] = {
    if (rank < 0 || rank > 7 || file < 0 || file > 7) None
    Some(Coords(rank, file))
  }
}
