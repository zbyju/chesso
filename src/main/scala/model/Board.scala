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
  */
class Board(board: Seq[Seq[Tile]] = initialBoard) {
  override def toString(): String = board.map(_.mkString).mkString("\n")
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

  def fromString(str: String): Seq[Seq[Tile]] = {
    require(
      str.length == 71 && str.forall(c => "\n prnbqk".contains(c.toLower))
    )
    str.split('\n').map(row => row.map(c => Tile.fromChar(c)))
  }

  // TODO: fromFEN
  def fromFEN(fen: String): Seq[Seq[Tile]] = ???
  // TODO: fromPGN
  def fromPGNOnMove(fen: String, move: Int): Seq[Seq[Tile]] = ???
}
