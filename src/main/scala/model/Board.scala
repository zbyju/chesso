package model

import model.pieces.Tile

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
  * Example:
  *
  * 7 r n b q k b n r 6 p p p p p p p p 5 4 3 2 1 P P P P P P P P 0 R N B Q K B
  * N R 0 1 2 3 4 5 6 7
  *
  * Note: The board is indexed board(rank)(file); the 0th index is the white
  * side's first rank; black's first rank is index at index 7. The files are
  * indexed left to right.
  */
class Board(val board: Seq[Seq[Tile]] = initialBoard) {

  /** Note: the board needs to be printed in reverse so that black is on top and
    * white on the bottom
    */
  override def toString(): String = board.reverse.map(_.mkString).mkString("\n")
}

/** The companion object `Board` provides factory methods for creating instances
  * of the `Board` class. These factory methods may include variations for
  * creating a board in the initial state, creating a board from a particular
  * game notation (like FEN), or creating a copy of an existing board.
  *
  * This companion object helps in providing a flexible and intuitive interface
  * for creating `Board` instances, while hiding internal implementation details
  * of the `Board` class.
  */
object Board {
  def initialBoard: Seq[Seq[Tile]] = fromString("""rnbqkbnr
                                   |pppppppp
                                   |        
                                   |        
                                   |        
                                   |        
                                   |PPPPPPPP
                                   |RNBQKBNR""".stripMargin)

  /** Builds the board from a string. Expects black pieces to be marked with
    * lower case, white pieces with upper case.
    *
    * Pieces:
    *
    * Rook = R Knight = N Bishop = B Queen = Q King = K Pawn = P No piece = ' '
    *
    * The first line should be black's first rank (8th rank) and last line
    * should be white's first rank (1st rank); ranks are separated using '\n'.
    */
  def fromString(str: String): Seq[Seq[Tile]] = {
    require(
      str.length == 71 && str.forall(c => "\n prnbqk".contains(c.toLower))
    )
    str.split('\n').map(row => row.map(c => Tile.fromChar(c))).reverse
  }

  def fromFEN(fen: String): Seq[Seq[Tile]] = fromFENBoard(fen.split(" ")(0))

  def fromFENBoard(fen: String) = fromString(
    fen.flatMap(c =>
      if (c == '/') "\n"
      else if (c.isLetter) c.toString
      else if (c.isDigit) " " * (c.toInt - '0'.toInt)
      else ""
    )
  )

  // TODO: fromPGN
  def fromPGNOnMove(fen: String, move: Int): Seq[Seq[Tile]] = ???
}
