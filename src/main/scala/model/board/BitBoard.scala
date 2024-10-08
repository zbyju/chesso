package model.board

final case class BitBoard private (board: BitArray) extends BitBoardLike {

  // ***************************** SET ************************************

  def set(pos: Coords): BitBoard = this.set(BitPosition.fromCoords(pos))
  def set(pos: BitPosition): BitBoard = BitBoard(this.board.set(pos))

  // **************************** RESET ***********************************

  def reset(pos: Coords): BitBoard = this.reset(BitPosition.fromCoords(pos))
  def reset(pos: BitPosition): BitBoard = BitBoard(this.board.reset(pos))

  // ***************************** GET ************************************

  def get(pos: Coords): Boolean = this.get(BitPosition.fromCoords(pos))
  def get(pos: BitPosition): Boolean = this.board.get(pos)

  override def getPositions: Seq[Coords] =
    this.board.positionsOfSetBits().map(_.toCoords())

  // ***************************** CHECK **********************************

  override def isOccupied(position: BitPosition): Boolean = this.get(position)

  // **************************** COUNT ***********************************

  override def countPieces: Int = board.countSetBits()

  // ****************************** REST **********************************

  def combine(that: BitBoard): BitBoard =
    BitBoard.fromLong(this.board.toLong | that.board.toLong)

  def invert: BitBoard = BitBoard(this.board.invert())

  override def toString(): String = {
    val sb = new StringBuilder
    for (rank <- 7 to 0 by -1) {
      sb.append(s"${rank + 1}|")
      for (file <- 0 to 7) {
        val pos = BitPosition.fromCoords(Coords(rank.toByte, file.toByte).get)
        val bit = if (board.get(pos)) "1" else "0"
        sb.append(s"$bit ")
      }
      sb.append("\n")
    }
    sb.append("-----------------\n")
    sb.append(" |a b c d e f g h\n")
    sb.toString
  }
}

object BitBoard {
  def empty = BitBoard.fromLong(0)

  def apply(positions: Coords*): BitBoard = {
    val ps = positions.map(p => BitPosition.fromCoords(p))
    BitBoard(BitArray(ps*))
  }

  def fromLong(value: Long): BitBoard = {
    val array = BitArray.fromLong(value)
    BitBoard(array)
  }

  def combine(b1: BitBoard, b2: BitBoard): BitBoard =
    BitBoard.fromLong(b1.board.toLong | b2.board.toLong)

  def combineBoards(boards: Seq[BitBoard]): BitBoard =
    boards.reduceLeft((acc, board) => acc.combine(board))
}
