package model

enum PieceType(val char: Char) {
  case Empty extends PieceType(' ')
  case Pawn extends PieceType('p')
  case Knight extends PieceType('n')
  case Bishop extends PieceType('b')
  case Rook extends PieceType('r')
  case Queen extends PieceType('q')
  case King extends PieceType('k')

  def isPiece: Boolean = this != Empty
  def isEmpty: Boolean = this == Empty

  /** Get lowercase character representation */
  def toLower: Char = char.toLower

  /** Get uppercase character representation */
  def toUpper: Char = char.toUpper

  /** Check if piece is sliding piece (bishop, rook, queen) */
  def isSliding: Boolean = this match {
    case Bishop | Rook | Queen => true
    case _                     => false
  }

  /** Check if piece can jump (only knight) */
  def canJump: Boolean = this == Knight

  def toChar(color: Color): Char = color.isWhite match {
    case true  => this.char.toLower
    case false => this.char.toUpper
  }
}

object PieceType {

  /** Create PieceType from character representation */
  def fromChar(c: Char): Option[PieceType] = c.toLower match {
    case 'p' => Some(Pawn)
    case 'n' => Some(Knight)
    case 'b' => Some(Bishop)
    case 'r' => Some(Rook)
    case 'q' => Some(Queen)
    case 'k' => Some(King)
    case ' ' => Some(Empty)
    case _   => None
  }

  /** Create PieceType from character, throwing exception if invalid */
  def fromCharUnsafe(c: Char): PieceType =
    fromChar(c).getOrElse(
      throw new IllegalArgumentException(s"Invalid piece character: $c")
    )

  /** Get all piece types */
  def all: Seq[PieceType] = PieceType.values.toSeq

  /** Get all piece types */
  def allNonEmpty: Seq[PieceType] = PieceType.values.toSeq.filter(_.isPiece)

  /** Check if character represents valid piece */
  def isValidPieceChar(c: Char): Boolean =
    fromChar(c).isDefined

}
