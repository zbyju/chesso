package model.board.pieces

import model.board.Move
import model.board.Coords
import model.board.Board
import model.board.BitBoard
import model.board.ChessBoard
import model.Color

object Pawn extends Piece {
  def isOnSecondRank(coords: Coords, color: Color): Boolean = color match {
    case Color.White => coords.rank == 1
    case Color.Black => coords.rank == 6
  }

  override def theoreticalMoves(coords: Coords, color: Color): BitBoard = {
    val direction = if color.isWhite then 1 else -1
    val jumps =
      if isOnSecondRank(coords, color) then
        Seq(
          coords.moveVertical(direction.toByte),
          coords.moveVertical((direction.toByte * 2).toByte)
        )
      else Seq(coords.moveVertical(direction.toByte))

    BitBoard(jumps.flatten*)
  }

  override def legalMoves(
      board: ChessBoard,
      coords: Coords
  ): Seq[ChessBoard] = {
    return Seq(board)
  }
}
