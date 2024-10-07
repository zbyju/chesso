import model.Board
import model.Coords
import model.PieceType
import model.Color

@main def hello: Unit =
  // val board = Board.fromFENBoard("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR")
  val board = Board.initial
  println(board)

  val c1 = Coords.fromString("a2").get
  val c2 = Coords.fromString("a1").get
  val c3 = Coords.fromString("a8").get
  val c4 = Coords.fromString("a7").get
  val c5 = Coords.fromString("d4").get

  println(board.isEmpty(c1))
  println(board.isEmpty(c2))
  println(board.isEmpty(c3))
  println(board.isEmpty(c4))
  println(board.isEmpty(c5))
  println(board.getPieceAndColorAt(c1))
  println(board.getPieceAndColorAt(c2))
  println(board.getPieceAndColorAt(c3))
  println(board.getPieceAndColorAt(c4))
  println(board.getPieceAndColorAt(c5))

  println(
    board.setPiece(PieceType.Queen, Color.White, Coords.fromString("d7").get)
  )
