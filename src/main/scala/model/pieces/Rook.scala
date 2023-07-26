package model.pieces

case class Rook(override val isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'R'
}
