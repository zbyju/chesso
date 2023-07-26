package model.pieces

case class Pawn(override val isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'P'

}
