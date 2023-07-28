import model.Board

@main def hello: Unit =
  val board =
    Board.fromFENBoard("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR")
  println(board)
