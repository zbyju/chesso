package model.pieces

case class Bishop(isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'B'
}
