package model.pieces

case class Knight(isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'N'
}
