import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import model.{BitBoard, BitArray, Coords, BitPosition}

class BitBoardTest extends AnyFlatSpec with Matchers {

  "BitBoard" should "be created with specific positions" in {
    val board = BitBoard(Coords(0, 0).get, Coords(7, 7).get)
    board.get(Coords(0, 0).get) shouldBe true
    board.get(Coords(7, 7).get) shouldBe true
    board.get(Coords(3, 3).get) shouldBe false
  }

  it should "set a position using Coords" in {
    val board = BitBoard()
    board.get(Coords(2, 3).get) shouldBe false
    val updatedBoard = board.set(Coords(2, 3).get)
    updatedBoard.get(Coords(2, 3).get) shouldBe true
  }

  it should "set a position using BitPosition" in {
    val board = BitBoard()
    val bitPosition = BitPosition.fromCoords(Coords(4, 5).get)
    board.get(bitPosition) shouldBe false
    val updatedBoard = board.set(bitPosition)
    updatedBoard.get(bitPosition) shouldBe true
  }

  it should "reset a position using Coords" in {
    val board = BitBoard(Coords(1, 1).get, Coords(2, 2).get)
    board.get(Coords(1, 1).get) shouldBe true
    val updatedBoard = board.reset(Coords(1, 1).get)
    updatedBoard.get(Coords(1, 1).get) shouldBe false
    updatedBoard.get(Coords(2, 2).get) shouldBe true
  }

  it should "reset a position using BitPosition" in {
    val board = BitBoard(Coords(3, 3).get, Coords(4, 4).get)
    val bitPosition = BitPosition.fromCoords(Coords(3, 3).get)
    board.get(bitPosition) shouldBe true
    val updatedBoard = board.reset(bitPosition)
    updatedBoard.get(bitPosition) shouldBe false
    updatedBoard.get(Coords(4, 4).get) shouldBe true
  }

  it should "get the correct value for a position using Coords" in {
    val board = BitBoard(Coords(0, 0).get, Coords(7, 7).get)
    board.get(Coords(0, 0).get) shouldBe true
    board.get(Coords(7, 7).get) shouldBe true
    board.get(Coords(3, 3).get) shouldBe false
  }

  it should "get the correct value for a position using BitPosition" in {
    val board = BitBoard(Coords(1, 1).get, Coords(6, 6).get)
    val bitPosition1 = BitPosition.fromCoords(Coords(1, 1).get)
    val bitPosition2 = BitPosition.fromCoords(Coords(6, 6).get)
    val bitPosition3 = BitPosition.fromCoords(Coords(4, 4).get)
    board.get(bitPosition1) shouldBe true
    board.get(bitPosition2) shouldBe true
    board.get(bitPosition3) shouldBe false
  }

  it should "handle edge cases" in {
    val board = BitBoard(Coords(0, 0).get, Coords(7, 7).get)

    // Test corners
    board.get(Coords(0, 0).get) shouldBe true
    board.get(Coords(0, 7).get) shouldBe false
    board.get(Coords(7, 0).get) shouldBe false
    board.get(Coords(7, 7).get) shouldBe true

    // Test setting and resetting the same position
    val updatedBoard = board.set(Coords(3, 3).get).reset(Coords(3, 3).get)
    updatedBoard.get(Coords(3, 3).get) shouldBe false

    // Test setting an already set position
    val doubleSetBoard = board.set(Coords(0, 0).get)
    doubleSetBoard.get(Coords(0, 0).get) shouldBe true
  }

  it should "maintain immutability" in {
    val originalBoard = BitBoard(Coords(1, 1).get)
    val updatedBoard = originalBoard.set(Coords(2, 2).get)

    originalBoard.get(Coords(1, 1).get) shouldBe true
    originalBoard.get(Coords(2, 2).get) shouldBe false

    updatedBoard.get(Coords(1, 1).get) shouldBe true
    updatedBoard.get(Coords(2, 2).get) shouldBe true
  }

  it should "work with all valid chess positions" in {
    val allPositions = for {
      rank <- 0 to 7
      file <- 0 to 7
    } yield Coords(rank.toByte, file.toByte).get

    val fullBoard = allPositions.foldLeft(BitBoard()) { (board, coords) =>
      board.set(coords)
    }

    allPositions.forall(fullBoard.get) shouldBe true

    val emptyBoard = allPositions.foldLeft(fullBoard) { (board, coords) =>
      board.reset(coords)
    }

    allPositions.forall(emptyBoard.get) shouldBe false
  }
}
