package model

import model.pieces.CharPiece

final case class BitBoard private (board: BitArray) {
  def set(pos: Coords): BitBoard = this.set(BitPosition.fromCoords(pos))
  def set(pos: BitPosition): BitBoard = BitBoard(this.board.set(pos))

  def reset(pos: Coords): BitBoard = this.reset(BitPosition.fromCoords(pos))
  def reset(pos: BitPosition): BitBoard = BitBoard(this.board.reset(pos))

  def get(pos: Coords): Boolean = this.get(BitPosition.fromCoords(pos))
  def get(pos: BitPosition): Boolean = this.board.get(pos)
}

object BitBoard {
  def apply(positions: Coords*): BitBoard = {
    val ps = positions.map(p => BitPosition.fromCoords(p))
    BitBoard(BitArray(ps*))
  }
}
