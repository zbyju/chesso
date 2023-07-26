package model.pieces

import model.Coords
import model.Board

abstract class Piece(val isWhite: Boolean) extends Tile {
  def allMoves(board: Board): Seq[Coords] = ???
  def possibleMoves(board: Board): Seq[Coords] = ???
  override def toString(): String =
    if (isWhite) this.tileRepresentation.toUpper.toString
    else this.tileRepresentation.toLower.toString
}
