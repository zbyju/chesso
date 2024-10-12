package model.board.pieces

import model.board.Move
import model.board.Coords
import model.board.Board
import model.board.BitBoard
import model.board.ChessBoard
import model.Color

object Queen extends Piece {
  override def theoreticalMoves(coords: Coords, color: Color): BitBoard = {
    // Bishop-like moves
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

    // Rook-like moves
    val maxHorizontalSteps = 7 - coords.file
    val minHorizontalSteps = -coords.file

    val maxVerticalSteps = 7 - coords.rank
    val minVerticalSteps = coords.file

    val horizontalCoords =
      (minHorizontalSteps
        .to(maxHorizontalSteps))
        .map(step => coords.moveHorizontal(step.toByte))
        .flatten

    val verticalCoords =
      (minVerticalSteps.toInt
        .to(maxVerticalSteps))
        .map(step => coords.moveVertical(step.toByte))
        .flatten

    val possibleCoords =
      horizontalCoords ++ verticalCoords ++ positiveCoords ++ negativeCoords

    BitBoard(possibleCoords*)
  }

  override def legalMoves(
      board: ChessBoard,
      coords: Coords
  ): Seq[ChessBoard] = {
    return Seq(board)
  }
}
