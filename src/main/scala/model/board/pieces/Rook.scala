package model.board.pieces

import model.board.Move
import model.board.Coords
import model.board.Board
import model.board.BitBoard
import model.Color
import model.board.ChessBoard

object Rook extends Piece {
  override def theoreticalMoves(coords: Coords, color: Color): BitBoard = {
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

    val possibleCoords = horizontalCoords ++ verticalCoords

    BitBoard(possibleCoords.filter(c => c != coords)*)
  }

  override def legalMoves(
      board: ChessBoard,
      coords: Coords
  ): Seq[ChessBoard] = {
    return Seq(board)
  }
}
