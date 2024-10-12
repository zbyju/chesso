package model.board

import model.Color

case class BoardState(
    val turn: Color, // Whose turn it is now
    val canWhiteCastleShort: Boolean, // Can white castle short?
    val canWhiteCastleLong: Boolean, // Can white castle long?
    val canBlackCastleShort: Boolean, // Can black castle short?
    val canBlackCastleLong: Boolean, // Can black castle long?
    val enPassant: BitBoard, // En passant options (bit board has 1 if en passant is possible)
    val halfMoveClock: Int // How many moves since the last capture or pawn move
) {
  def canEnPassantOnSquare(coords: Coords): Boolean = enPassant.get(coords)

  def move(
      enPassantSquares: Seq[Coords] = Seq.empty,
      halfMoveClock: Int = this.halfMoveClock + 1,
      canWhiteCastleShort: Boolean = this.canWhiteCastleShort,
      canWhiteCastleLong: Boolean = this.canWhiteCastleLong,
      canBlackCastleShort: Boolean = this.canBlackCastleShort,
      canBlackCastleLong: Boolean = this.canBlackCastleLong
  ): BoardState = BoardState(
    turn = turn.opposite,
    canWhiteCastleShort,
    canWhiteCastleLong,
    canBlackCastleShort,
    canBlackCastleLong,
    BitBoard(enPassantSquares*),
    halfMoveClock
  )
}

object BoardState {
  def empty: BoardState = BoardState(
    turn = Color.White,
    canWhiteCastleShort = false,
    canWhiteCastleLong = false,
    canBlackCastleShort = false,
    canBlackCastleLong = false,
    enPassant = BitBoard.empty,
    halfMoveClock = 0
  )
}
