package model.board.pieces

import model.board.Move
import model.board.Coords
import model.board.Board
import model.board.BitBoard
import model.Color
import model.board.ChessBoard

object Bishop extends Piece {
  override def theoreticalMoves(coords: Coords, color: Color): BitBoard = {
    val maxPositiveSteps = Math.min(7 - coords.rank, 7 - coords.file)
    val minPositiveSteps = -Math.min(coords.rank, coords.file)

    val maxNegativeSteps = Math.min(7 - coords.rank, coords.file)
    val minNegativeSteps = -Math.min(coords.rank, 7 - coords.file)

    val positiveCoords =
      (minPositiveSteps
        .to(maxPositiveSteps))
        .map(step => coords.movePositiveDiagonal(step.toByte))
        .flatten

    val negativeCoords =
      (minNegativeSteps
        .to(maxNegativeSteps))
        .map(step => coords.moveNegativeDiagonal(step.toByte))
        .flatten

    val possibleCoords = positiveCoords ++ negativeCoords

    BitBoard(possibleCoords*)
  }

  override def legalMoves(
      board: ChessBoard,
      coords: Coords
  ): Seq[ChessBoard] = {
    Seq(board)
  }
}
