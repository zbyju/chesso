package view

import model.board.ChessBoard

trait ChessPresenter {
  def start(board: ChessBoard): Unit
}
