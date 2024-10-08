package model.board

import scala.Nothing
import java.security.InvalidParameterException
import scala.annotation.switch
import model.Color
import model.PieceType

/** The `Board` class represents a chess board in the context of a chess game.
  *
  * It is responsible for maintaining the current state of the board, including
  * the positions of all pieces on the board and fascilitating some utilities
  * for fetching the pieces for further manipulation.
  *
  * The class provides an abstraction of a real-world chess board, facilitating
  * interactions with other components of the chess game system such as players,
  * pieces, game rules etc.
  *
  * The `Board` class should remain agnostic to specific game rules or
  * strategies and should focus solely on managing the state of the board. It
  * should not manage individual games, players, clock, etc.
  *
  * Note: The board is indexed board(rank)(file); the 0th index is the white
  * side's first rank; black's first rank is index at index 7. The files are
  * indexed left to right.
  */
case class Board(white: BoardOfColor, black: BoardOfColor)
    extends BitBoardLike {

  /** Get all occupied squares (by either color) */
  def allPieces: BitBoard =
    white.allPieces.combine(black.allPieces)

  /** Get BoardOfColor for specified color */
  def boardByColor(color: Color): BoardOfColor = color match {
    case Color.White => white
    case Color.Black => black
  }

  /** Get color of piece at position (if any) */
  def colorAt(pos: Coords): Option[Color] = {
    if (white.isOccupied(pos)) Some(Color.White)
    else if (black.isOccupied(pos)) Some(Color.Black)
    else None
  }

  // ************************************** SET **************************************

  /** Set piece of specific color at position */
  def setPiece(piece: PieceType, color: Color, pos: Coords): Board = {
    // First remove any piece of either color at this position
    val clearedBoard = reset(pos)
    // Then set the new piece
    color match {
      case Color.White =>
        clearedBoard.copy(white = clearedBoard.white.setPiece(piece, pos))
      case Color.Black =>
        clearedBoard.copy(black = clearedBoard.black.setPiece(piece, pos))
    }
  }

  // ************************************* RESET *************************************

  /** Reset (remove) any piece at position */
  def reset(pos: Coords): Board = {
    // Remove pieces of both colors at this position
    val newWhite = PieceType.all.foldLeft(white)((b, p) => b.resetPiece(p, pos))
    val newBlack = PieceType.all.foldLeft(black)((b, p) => b.resetPiece(p, pos))
    Board(newWhite, newBlack)
  }

  /** Reset piece of specific color at position */
  def resetPiece(piece: PieceType, color: Color, pos: Coords): Board = {
    color match {
      case Color.White =>
        this.copy(white = this.white.resetPiece(piece, pos))
      case Color.Black =>
        this.copy(black = this.black.setPiece(piece, pos))
    }
  }

  // ************************************** GET **************************************

  /** Get piece type at position (if any) */

  override def getPositions: Seq[Coords] =
    allPieces.board.positionsOfSetBits().map(_.toCoords())

  def getPieceAt(pos: Coords): PieceType = {
    PieceType.allNonEmpty
      .find { pieceType =>
        white.getPiece(pieceType, pos) || black.getPiece(pieceType, pos)
      }
      .getOrElse(PieceType.Empty)
  }

  /** Get all pieces of a specific color */
  def getPiecesByColor(color: Color): Seq[(PieceType, Coords)] = {
    val board = boardByColor(color)
    for {
      pieceType <- PieceType.all
      pos <- board.getPositionsByPieceType(pieceType)
    } yield (pieceType, pos)
  }

  /** Get both color and piece type at position (if any) */
  def getPieceAndColorAt(pos: Coords): Option[(PieceType, Color)] = {
    val piece = getPieceAt(pos)
    if (piece.isEmpty) return Option.empty
    val color = colorAt(pos)

    return Some(piece, color.get)
  }

  // ************************************* CHECK *************************************

  override def isOccupied(position: BitPosition): Boolean =
    allPieces.get(position)

  // ************************************* COUNT *************************************

  /** Get total number of pieces */
  override def countPieces: Int = white.countPieces + black.countPieces

  /** Get total number of pieces */
  def countPiecesByPieceType(pieceType: PieceType): Int =
    white.countPiecesByPieceType(pieceType) + black.countPiecesByPieceType(
      pieceType
    )

  // ************************************* REST **************************************

  /** Convert board to string representation */
  override def toString: String = {
    val sb = new StringBuilder
    for (rank <- 7 to 0 by -1) {
      sb.append(s"${rank + 1}|")
      for (file <- 0 to 7) {
        val pos = Coords(rank.toByte, file.toByte).get
        val piece = getPieceAndColorAt(pos) match {
          case Some((pieceType, color)) =>
            pieceType.toChar(color)
          case None => '.'
        }
        sb.append(s"$piece ")
      }
      sb.append("\n")
    }
    sb.append("------------------\n")
    sb.append(" |a b c d e f g h\n")
    sb.toString
  }

  def toSeq: Seq[BitBoard] = white.toSeq ++ black.toSeq
}

object Board {

  /** Create empty board */
  def empty: Board = Board(BoardOfColor.empty, BoardOfColor.empty)

  /** Create board with initial chess setup */
  def initial: Board =
    Board(BoardOfColor.initialWhite, BoardOfColor.initialBlack)

  /** Create board from FEN string */
  def fromFen(fen: String): Option[Board] = {
    // TODO: Implement FEN parsing
    None
  }
}
