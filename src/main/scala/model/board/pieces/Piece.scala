package model.board.pieces

import model.board.Coords
import model.board.Board
import model.board.Move
import model.board.BitBoard
import model.PieceType
import model.Color
import model.board.ChessBoard

trait Piece {
  // Moves the piece could theoretically make from the position if there were no pieces, checks, pins, etc.
  def theoreticalMoves(coords: Coords, color: Color): BitBoard

  // Moves the piece can make from the position considering all limitations
  def legalMoves(board: ChessBoard, coords: Coords): Seq[ChessBoard]

  def getPiece(pieceType: PieceType): Piece = pieceType match {
    case PieceType.Pawn   => Pawn
    case PieceType.Knight => Knight
    case PieceType.Bishop => Bishop
    case PieceType.Rook   => Rook
    case PieceType.Queen  => Queen
    case PieceType.King   => King
    case PieceType.Empty  => Empty
  }
}
