package model.board

import model.Color

class ChessBoard(val board: Board, val state: BoardState) {
  def theoreticalMoves: Seq[Move] = ???
}

object ChessBoard {
  def empty = new ChessBoard(
    Board.empty,
    BoardState.empty
  )
}
