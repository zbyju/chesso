package model.board

import model.Color
import model.board.pieces.Pawn
import model.board.pieces.Knight
import model.board.pieces.Bishop
import model.board.pieces.Rook
import model.board.pieces.Queen
import model.board.pieces.King

class ChessBoard(val board: Board, val state: BoardState) {
  // Board that is currently playing a move
  def playingBoard = state.turn match {
    case Color.White => board.white
    case Color.Black => board.black
  }

  // Board that is waiting for the opponent to move
  def waitingBoard = state.turn match {
    case Color.White => board.white
    case Color.Black => board.black
  }

  def theoreticalMoves(coords: Coords): BitBoard = {
    val piece = playingBoard.getPieceTypeAt(coords)

    piece match {
      case model.PieceType.Pawn   => Pawn.theoreticalMoves(coords, state.turn)
      case model.PieceType.Knight => Knight.theoreticalMoves(coords, state.turn)
      case model.PieceType.Bishop => Bishop.theoreticalMoves(coords, state.turn)
      case model.PieceType.Rook   => Rook.theoreticalMoves(coords, state.turn)
      case model.PieceType.Queen  => Queen.theoreticalMoves(coords, state.turn)
      case model.PieceType.King   => King.theoreticalMoves(coords, state.turn)
      case _                      => BitBoard.empty
    }
  }
}

object ChessBoard {
  def empty = new ChessBoard(
    Board.empty,
    BoardState.empty
  )

  def initial = new ChessBoard(
    Board.initial,
    BoardState.empty
  )
}
