package model.board.pieces

import model.board.Move
import model.board.Coords
import model.board.Board
import model.board.BitBoard
import model.Color
import model.board.ChessBoard

object King extends Piece {
  override def theoreticalMoves(coords: Coords, color: Color): BitBoard = {
    val moves = Seq(
      coords.moveHorizontal(1.toByte),
      coords.moveHorizontal(-1.toByte),
      coords.moveVertical(1.toByte),
      coords.moveVertical(-1.toByte),
      coords.movePositiveDiagonal(1.toByte),
      coords.movePositiveDiagonal(-1.toByte),
      coords.moveNegativeDiagonal(1.toByte),
      coords.moveNegativeDiagonal(-1.toByte)
    )
    BitBoard(moves.flatten*)
  }

  override def legalMoves(
      board: ChessBoard,
      coords: Coords
  ): Seq[ChessBoard] = {
    Seq(board)
  }
}
