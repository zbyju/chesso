package model.pieces

import model.Coords
import model.Board

case class Empty() extends Tile() {
  override val tileRepresentation: Char = ' '
}
