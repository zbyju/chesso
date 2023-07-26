package model.pieces

abstract class Tile() {
  val tileRepresentation: Char = '?'
  override def toString(): String = this.tileRepresentation.toString
}

object Tile {
  def fromChar(c: Char): Tile = {
    require(" prnbqk".contains(c.toLower))

    c.toLower match {
      case ' ' => Empty()
      case 'p' => Pawn(c.isUpper)
      case 'r' => Rook(c.isUpper)
      case 'n' => Knight(c.isUpper)
      case 'b' => Bishop(c.isUpper)
      case 'q' => Queen(c.isUpper)
      case 'k' => King(c.isUpper)
    }
  }
}
