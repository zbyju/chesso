package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BoardTest extends AnyFlatSpec with Matchers {

  // Helper methods for tests
  private def coord(s: String) = Coords.fromString(s).get
  private def pos(s: String) = BitPosition.fromCoords(coord(s))

  "Board" should "create an empty board" in {
    val board = Board.empty
    board.countPieces shouldBe 0
    board.allPieces.board.toLong shouldBe 0L
  }

  it should "create initial setup correctly" in {
    val board = Board.initial

    // Check piece counts
    board.countPieces shouldBe 32
    board.countPiecesByPieceType(PieceType.Pawn) shouldBe 16
    board.countPiecesByPieceType(PieceType.Rook) shouldBe 4
    board.countPiecesByPieceType(PieceType.Knight) shouldBe 4
    board.countPiecesByPieceType(PieceType.Bishop) shouldBe 4
    board.countPiecesByPieceType(PieceType.Queen) shouldBe 2
    board.countPiecesByPieceType(PieceType.King) shouldBe 2

    // Check specific positions
    board.getPieceAt(coord("e2")) shouldBe PieceType.Pawn
    board.getPieceAt(coord("a1")) shouldBe PieceType.Rook
    board.getPieceAt(coord("b1")) shouldBe PieceType.Knight
    board.getPieceAt(coord("c1")) shouldBe PieceType.Bishop
    board.getPieceAt(coord("d1")) shouldBe PieceType.Queen
    board.getPieceAt(coord("e1")) shouldBe PieceType.King

    board.getPieceAt(coord("e7")) shouldBe PieceType.Pawn
    board.getPieceAt(coord("a8")) shouldBe PieceType.Rook
    board.getPieceAt(coord("b8")) shouldBe PieceType.Knight
    board.getPieceAt(coord("c8")) shouldBe PieceType.Bishop
    board.getPieceAt(coord("d8")) shouldBe PieceType.Queen
    board.getPieceAt(coord("e8")) shouldBe PieceType.King
  }

  it should "set and reset pieces correctly" in {
    val board = Board.empty
    board.countPieces shouldBe 0

    // Set a piece
    val withPawn = board.setPiece(PieceType.Pawn, Color.White, coord("e4"))
    withPawn.white.getPiece(PieceType.Pawn, coord("e4")) shouldBe true
    withPawn.countPieces shouldBe 1

    // Reset the piece
    val empty = withPawn.resetPiece(PieceType.Pawn, Color.White, coord("e4"))
    empty.white.getPiece(PieceType.Pawn, coord("e4")) shouldBe false
    empty.countPieces shouldBe 0
  }

  it should "set and reset pieces correctly with general reset" in {
    val board = Board.empty
    board.countPieces shouldBe 0

    // Set a piece
    val withPawn = board.setPiece(PieceType.Pawn, Color.White, coord("e4"))
    withPawn.white.getPiece(PieceType.Pawn, coord("e4")) shouldBe true
    withPawn.countPieces shouldBe 1

    // Reset the piece
    val empty = withPawn.reset(coord("e4"))
    empty.white.getPiece(PieceType.Pawn, coord("e4")) shouldBe false
    empty.countPieces shouldBe 0
  }

  it should "handle multiple pieces correctly with different colors" in {
    val board = Board.empty
      .setPiece(PieceType.Queen, Color.White, coord("d1"))
      .setPiece(PieceType.Pawn, Color.White, coord("e2"))
      .setPiece(PieceType.Pawn, Color.White, coord("h7"))
      .setPiece(PieceType.Rook, Color.Black, coord("d2"))

    board.countPieces shouldBe 4
    board.countPiecesByPieceType(PieceType.Queen) shouldBe 1
    board.countPiecesByPieceType(PieceType.Pawn) shouldBe 2
    board.countPiecesByPieceType(PieceType.Rook) shouldBe 1
    board.white.countPieces shouldBe 3
    board.black.countPieces shouldBe 1
  }

  it should "check occupied positions correctly" in {
    val board = Board.empty
      .setPiece(PieceType.Queen, Color.White, coord("d4"))

    board.isOccupied(coord("d4")) shouldBe true
    board.isOccupied(coord("e4")) shouldBe false
    board.isEmpty(coord("e4")) shouldBe true
    board.isEmpty(coord("d4")) shouldBe false
  }

  it should "get positions of pieces correctly" in {
    val board = Board.empty
      .setPiece(PieceType.Pawn, Color.White, coord("e2"))
      .setPiece(PieceType.Pawn, Color.White, coord("d2"))

    val pawnPositions = board.getPositions.toSet
    pawnPositions should contain(coord("e2"))
    pawnPositions should contain(coord("d2"))
    pawnPositions.size shouldBe 2
  }

  it should "check empty positions of specific piece type correctly" in {
    val board = BoardOfColor.empty
      .setPiece(PieceType.Queen, coord("d1"))

    board.isEmptyOfType(PieceType.Queen, coord("e1")) shouldBe true
    board.isEmptyOfType(PieceType.Queen, coord("d1")) shouldBe false
    board.isEmptyOfType(PieceType.Pawn, coord("d1")) shouldBe true
  }

  it should "combine all pieces into one BitBoard correctly" in {
    val board = Board.empty
      .setPiece(PieceType.Queen, Color.White, coord("d1"))
      .setPiece(PieceType.Pawn, Color.Black, coord("e2"))

    val combined = board.allPieces
    combined.get(pos("d1")) shouldBe true
    combined.get(pos("e2")) shouldBe true
    combined.get(pos("e1")) shouldBe false
    combined.board.countSetBits() shouldBe 2
  }

  it should "convert to sequence of BitBoards correctly" in {
    val board = Board.initial
    val boards = board.toSeq

    boards.length shouldBe 12 // one for each piece type
  }
}
