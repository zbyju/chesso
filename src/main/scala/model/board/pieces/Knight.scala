package model.board.pieces

import model.board.Move
import model.board.Coords
import model.board.Board
import model.board.BitBoard
import model.Color
import model.board.ChessBoard

object Knight extends Piece {
  override def theoreticalMoves(coords: Coords, color: Color): BitBoard = {
    val moves =
      (0.to(7)).map(i => coords.moveKnight(i.toByte))

    BitBoard(moves.flatten*)
  }

  override def legalMoves(
      board: ChessBoard,
      coords: Coords
  ): Seq[ChessBoard] = {
    Seq(board)
  }
}
