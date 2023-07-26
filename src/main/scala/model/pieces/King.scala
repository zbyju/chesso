package model.pieces

case class King(isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'K'
}
