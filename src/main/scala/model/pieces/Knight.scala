package model.pieces

case class Knight(override val isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'N'
}
