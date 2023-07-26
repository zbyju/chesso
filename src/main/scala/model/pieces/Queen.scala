package model.pieces

case class Queen(override val isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'Q'
}
