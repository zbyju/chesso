package view.CLI

package presenter

import model.board.ChessBoard
import scala.io.StdIn.readLine
import view.ChessPresenter
import model.board.Coords
import Console.{GREEN, BLUE, RESET, BOLD}

class CLIChessPresenter extends ChessPresenter {

  override def start(board: ChessBoard): Unit = {
    println("Welcome to Chess CLI!")

    var continue = true
    while (continue) {
      render(board)

      println(
        "Enter a coordinate to highlight possible moves (e.g., 'b2') or 'q' to quit:"
      )
      val input = readLine().trim.toLowerCase

      input match {
        case "q" =>
          continue = false
          println("Goodbye!")
        case coord if coord.matches("^[a-h][1-8]$") =>
          val bitboardPossible =
            board.theoreticalMoves(Coords.fromString(coord).get)
          val possibleMoves =
            bitboardPossible.board.positionsOfSetBits().map(_.toCoords())
          highlightTiles(board, possibleMoves)
        case coord if coord.matches("^[a-h][1-8] [a-h][1-8]$") =>
          println("Making a move")
        case _ =>
          println(
            "Invalid command. Please enter a valid coordinate (e.g., 'b2') or 'q' to quit."
          )
      }
    }
  }

  // Method to render the chessboard state to the console
  private def render(board: ChessBoard): Unit = {
    println("Current Board State:")
    printBoard(board, Seq.empty)
  }

  // Method to highlight specific tiles (e.g., possible moves)
  private def highlightTiles(
      board: ChessBoard,
      highlightedTiles: Seq[Coords]
  ): Unit = {
    printBoard(board, highlightedTiles)
  }

  // Helper method to print the board, highlighting specific tiles
  private def printBoard(
      board: ChessBoard,
      highlightedTiles: Seq[Coords]
  ): Unit = {
    val sb = new StringBuilder
    for (rank <- 7 to 0 by -1) {
      sb.append(s"${rank + 1}|")
      for (file <- 0 to 7) {
        val pos = Coords(rank.toByte, file.toByte).get
        val piece = board.board.getPieceAndColorAt(pos) match {
          case Some((pieceType, color)) =>
            pieceType.toChar(color)
          case None => '.'
        }
        val pieceStr =
          if (highlightedTiles.contains(pos)) then highlighted(piece.toString)
          else piece

        sb.append(s"$pieceStr ")
      }
      sb.append("\n")
    }
    sb.append("------------------\n")
    sb.append(" |a b c d e f g h\n")
    print(sb.toString)
  }

  // Helper method to format a tile for highlighting (in bold text)
  private def highlighted(str: String): String = {
    s"${BOLD}${BLUE}${str}${RESET}"
  }
}
