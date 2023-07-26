package model.pieces

case class King(override val isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'K'
}
