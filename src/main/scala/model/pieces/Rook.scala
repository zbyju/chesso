package model.pieces

case class Rook(isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'R'
}
