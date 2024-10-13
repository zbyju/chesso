import model.board.ChessBoard
import view.ChessPresenter
import view.CLI.presenter.CLIChessPresenter

object Main {
  def main(args: Array[String]): Unit = {
    // Initialize the chessboard state (assuming you have a model ready)
    val board = ChessBoard.initial

    val presenter: ChessPresenter = new CLIChessPresenter()

    presenter.start(board)
  }
}
