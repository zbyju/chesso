package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BoardOfColorTest extends AnyFlatSpec with Matchers {

  // Helper methods for tests
  private def coord(s: String) = Coords.fromString(s).get
  private def pos(s: String) = BitPosition.fromCoords(coord(s))

  "BoardOfColor" should "create an empty board" in {
    val board = BoardOfColor.empty
    board.countPieces shouldBe 0
    board.allPieces.board.toLong shouldBe 0L
  }

  it should "create initial white setup correctly" in {
    val board = BoardOfColor.initialWhite

    // Check piece counts
    board.countPieces shouldBe 16
    board.countPiecesByPieceType(PieceType.Pawn) shouldBe 8
    board.countPiecesByPieceType(PieceType.Rook) shouldBe 2
    board.countPiecesByPieceType(PieceType.Knight) shouldBe 2
    board.countPiecesByPieceType(PieceType.Bishop) shouldBe 2
    board.countPiecesByPieceType(PieceType.Queen) shouldBe 1
    board.countPiecesByPieceType(PieceType.King) shouldBe 1

    // Check specific positions
    board.getPiece(PieceType.Pawn, coord("e2")) shouldBe true
    board.getPiece(PieceType.Rook, coord("a1")) shouldBe true
    board.getPiece(PieceType.Knight, coord("b1")) shouldBe true
    board.getPiece(PieceType.Bishop, coord("c1")) shouldBe true
    board.getPiece(PieceType.Queen, coord("d1")) shouldBe true
    board.getPiece(PieceType.King, coord("e1")) shouldBe true
  }

  it should "create initial black setup correctly" in {
    val board = BoardOfColor.initialBlack

    // Check piece counts
    board.countPieces shouldBe 16
    board.countPiecesByPieceType(PieceType.Pawn) shouldBe 8

    // Check specific positions
    board.getPiece(PieceType.Pawn, coord("e7")) shouldBe true
    board.getPiece(PieceType.Rook, coord("h8")) shouldBe true
    board.getPiece(PieceType.Knight, coord("g8")) shouldBe true
    board.getPiece(PieceType.Bishop, coord("f8")) shouldBe true
    board.getPiece(PieceType.Queen, coord("d8")) shouldBe true
    board.getPiece(PieceType.King, coord("e8")) shouldBe true
  }

  it should "set and reset pieces correctly" in {
    val board = BoardOfColor.empty

    // Set a piece
    val withPawn = board.setPiece(PieceType.Pawn, coord("e4"))
    withPawn.getPiece(PieceType.Pawn, coord("e4")) shouldBe true
    withPawn.countPieces shouldBe 1

    // Reset the piece
    val empty = withPawn.resetPiece(PieceType.Pawn, coord("e4"))
    empty.getPiece(PieceType.Pawn, coord("e4")) shouldBe false
    empty.countPieces shouldBe 0
  }

  it should "handle multiple pieces correctly" in {
    val board = BoardOfColor.empty
      .setPiece(PieceType.Queen, coord("d1"))
      .setPiece(PieceType.Pawn, coord("e2"))
      .setPiece(PieceType.Pawn, coord("d2"))

    board.countPieces shouldBe 3
    board.countPiecesByPieceType(PieceType.Queen) shouldBe 1
    board.countPiecesByPieceType(PieceType.Pawn) shouldBe 2
  }

  it should "check occupied positions correctly" in {
    val board = BoardOfColor.empty
      .setPiece(PieceType.Queen, coord("d4"))

    board.isOccupied(coord("d4")) shouldBe true
    board.isOccupied(coord("e4")) shouldBe false
    board.isEmpty(coord("e4")) shouldBe true
    board.isEmpty(coord("d4")) shouldBe false
  }

  it should "get positions of pieces correctly" in {
    val board = BoardOfColor.empty
      .setPiece(PieceType.Pawn, coord("e2"))
      .setPiece(PieceType.Pawn, coord("d2"))

    println(board)

    val pawnPositions = board.getPositionsByPieceType(PieceType.Pawn).toSet
    println(pawnPositions)
    pawnPositions should contain(coord("e2"))
    pawnPositions should contain(coord("d2"))
    pawnPositions.size shouldBe 2
  }

  it should "get all occupied positions correctly" in {
    val board = BoardOfColor.empty
      .setPiece(PieceType.Queen, coord("d1"))
      .setPiece(PieceType.Pawn, coord("e2"))

    val positions = board.getPositions.toSet
    positions should contain(coord("d1"))
    positions should contain(coord("e2"))
    positions.size shouldBe 2
  }

  it should "check empty positions of specific piece type correctly" in {
    val board = BoardOfColor.empty
      .setPiece(PieceType.Queen, coord("d1"))

    board.isEmptyOfType(PieceType.Queen, coord("e1")) shouldBe true
    board.isEmptyOfType(PieceType.Queen, coord("d1")) shouldBe false
    board.isEmptyOfType(PieceType.Pawn, coord("d1")) shouldBe true
  }

  it should "combine all pieces into one BitBoard correctly" in {
    val board = BoardOfColor.empty
      .setPiece(PieceType.Queen, coord("d1"))
      .setPiece(PieceType.Pawn, coord("e2"))

    val combined = board.allPieces
    combined.get(pos("d1")) shouldBe true
    combined.get(pos("e2")) shouldBe true
    combined.get(pos("e1")) shouldBe false
    combined.board.countSetBits() shouldBe 2
  }

  it should "convert to sequence of BitBoards correctly" in {
    val board = BoardOfColor.initialWhite
    val boards = board.toSeq

    boards.length shouldBe 6 // one for each piece type
    boards(0).board.countSetBits() shouldBe 8 // pawns
    boards(1).board.countSetBits() shouldBe 2 // rooks
    boards(4).board.countSetBits() shouldBe 1 // queen
  }
}
