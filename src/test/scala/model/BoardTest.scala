import model.Board
import model.pieces.*

class BoardTest extends munit.FunSuite {
  test("The initial board has correct dimensions") {
    val board = new Board()
    val b = board.board
    assert(b.length == 8 && b.forall(r => r.length == 8))
  }

  test("The initial position is correct") {
    val board = new Board()
    val b = board.board

    // First rank is correct (white side)
    assert(
      b(0).forall(p =>
        p.isInstanceOf[Piece] && p.asInstanceOf[Piece].isWhite
      ) &&
        b(1).forall(p => p.isInstanceOf[Pawn] && p.asInstanceOf[Piece].isWhite)
    )
    assert(b(0)(0).isInstanceOf[Rook])
    assert(b(0)(1).isInstanceOf[Knight])
    assert(b(0)(2).isInstanceOf[Bishop])
    assert(b(0)(3).isInstanceOf[Queen])
    assert(b(0)(4).isInstanceOf[King])
    assert(b(0)(5).isInstanceOf[Bishop])
    assert(b(0)(6).isInstanceOf[Knight])
    assert(b(0)(7).isInstanceOf[Rook])

    // First rank is correct (white side)
    assert(
      b(7).forall(p =>
        p.isInstanceOf[Piece] && !p.asInstanceOf[Piece].isWhite
      ) &&
        b(6).forall(p => p.isInstanceOf[Pawn] && !p.asInstanceOf[Piece].isWhite)
    )
    assert(b(7)(0).isInstanceOf[Rook])
    assert(b(7)(1).isInstanceOf[Knight])
    assert(b(7)(2).isInstanceOf[Bishop])
    assert(b(7)(3).isInstanceOf[Queen])
    assert(b(7)(4).isInstanceOf[King])
    assert(b(7)(5).isInstanceOf[Bishop])
    assert(b(7)(6).isInstanceOf[Knight])
    assert(b(7)(7).isInstanceOf[Rook])
  }
}
