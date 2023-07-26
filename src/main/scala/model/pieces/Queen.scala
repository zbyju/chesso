package model.pieces

case class Queen(isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'Q'
}
