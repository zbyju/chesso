package model

enum Color(white: Boolean) {
  case White extends Color(true)
  case Black extends Color(false)

  def isWhite: Boolean = this.white

  /** Get opposite color */
  def opposite: Color = this match {
    case White => Black
    case Black => White
  }

  /** Convert to string representation */
  override def toString: String = this match {
    case White => "white"
    case Black => "black"
  }

  /** Get starting rank for pawns */
  def pawnRank: Int = this match {
    case White => 1 // second rank (indexed from 0)
    case Black => 6 // seventh rank
  }

  /** Get promotion rank for pawns */
  def promotionRank: Int = this match {
    case White => 7 // eighth rank
    case Black => 0 // first rank
  }

  /** Get direction of forward movement */
  def direction: Int = this match {
    case White => 1 // moving up the board
    case Black => -1 // moving down the board
  }

  /** Check if a rank is on home half of board */
  def isHomeHalf(rank: Int): Boolean = this match {
    case White => rank < 4
    case Black => rank >= 4
  }
}

object Color {

  /** Create Color from character representation */
  def fromChar(c: Char): Option[Color] = c.toLower match {
    case 'w' => Some(White)
    case 'b' => Some(Black)
    case _   => None
  }

  def promotionRank(color: Color): Int = color match {
    case White => 7
    case Black => 0
  }

  /** Get both colors */
  def all: Seq[Color] = Color.values.toSeq
}
