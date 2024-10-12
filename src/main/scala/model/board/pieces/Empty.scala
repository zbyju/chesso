package model.board.pieces

import model.board.ChessBoard
import model.board.Coords
import model.board.BitBoard
import model.Color

object Empty extends Piece {

  override def theoreticalMoves(coords: Coords, color: Color): BitBoard =
    BitBoard.empty

  override def legalMoves(board: ChessBoard, coords: Coords): Seq[ChessBoard] =
    Seq()

}
