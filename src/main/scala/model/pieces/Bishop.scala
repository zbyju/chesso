package model.pieces

case class Bishop(override val isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'B'
}
