package model.pieces

case class Pawn(isWhite: Boolean) extends Piece(isWhite) {
  override val tileRepresentation: Char = 'P'

}
