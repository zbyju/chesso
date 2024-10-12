package model.board

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import model.board.pieces.Bishop
import model.board.Coords

class BishopTest extends AnyFlatSpec with Matchers {
  "Bishop" should "should generate positive diagonal moves" in {
    val moves = Bishop.theoreticalMoves(Coords.unsafeApply(0, 0))
    val coords = moves.getPositions.toSet

    coords shouldBe Set(
      Coords.unsafeApply(1, 1),
      Coords.unsafeApply(2, 2),
      Coords.unsafeApply(3, 3),
      Coords.unsafeApply(4, 4),
      Coords.unsafeApply(5, 5),
      Coords.unsafeApply(6, 6),
      Coords.unsafeApply(7, 7)
    )
  }

  it should "should generate positive diagonal moves backwards" in {
    val moves = Bishop.theoreticalMoves(Coords.unsafeApply(7, 7))
    val coords = moves.getPositions.toSet

    coords shouldBe Set(
      Coords.unsafeApply(0, 0),
      Coords.unsafeApply(1, 1),
      Coords.unsafeApply(2, 2),
      Coords.unsafeApply(3, 3),
      Coords.unsafeApply(4, 4),
      Coords.unsafeApply(5, 5),
      Coords.unsafeApply(6, 6)
    )
  }

  it should "should generate negative diagonal moves" in {
    val moves = Bishop.theoreticalMoves(Coords.unsafeApply(0, 7))
    val coords = moves.getPositions.toSet

    coords shouldBe Set(
      Coords.unsafeApply(1, 6),
      Coords.unsafeApply(2, 5),
      Coords.unsafeApply(3, 4),
      Coords.unsafeApply(4, 3),
      Coords.unsafeApply(5, 2),
      Coords.unsafeApply(6, 1),
      Coords.unsafeApply(7, 0)
    )
  }

  it should "should generate negative diagonal moves backwards" in {
    val moves = Bishop.theoreticalMoves(Coords.unsafeApply(7, 0))
    val coords = moves.getPositions.toSet

    coords shouldBe Set(
      Coords.unsafeApply(0, 7),
      Coords.unsafeApply(1, 6),
      Coords.unsafeApply(2, 5),
      Coords.unsafeApply(3, 4),
      Coords.unsafeApply(4, 3),
      Coords.unsafeApply(5, 2),
      Coords.unsafeApply(6, 1)
    )
  }

  it should "should generate in all directions" in {
    val moves = Bishop.theoreticalMoves(Coords.unsafeApply(3, 3))
    val coords = moves.getPositions.toSet

    coords shouldBe Set(
      // Positive diagonal
      Coords.unsafeApply(0, 0),
      Coords.unsafeApply(1, 1),
      Coords.unsafeApply(2, 2),
      Coords.unsafeApply(4, 4),
      Coords.unsafeApply(5, 5),
      Coords.unsafeApply(6, 6),
      Coords.unsafeApply(7, 7),
      // Negative diagonal
      Coords.unsafeApply(6, 0),
      Coords.unsafeApply(5, 1),
      Coords.unsafeApply(4, 2),
      Coords.unsafeApply(2, 4),
      Coords.unsafeApply(1, 5),
      Coords.unsafeApply(0, 6)
    )
  }

  it should "should generate in all directions on non-primary diagonal" in {
    val moves = Bishop.theoreticalMoves(Coords.unsafeApply(6, 5))
    val coords = moves.getPositions.toSet

    coords shouldBe Set(
      // Positive diagonal
      Coords.unsafeApply(1, 0),
      Coords.unsafeApply(2, 1),
      Coords.unsafeApply(3, 2),
      Coords.unsafeApply(4, 3),
      Coords.unsafeApply(5, 4),
      Coords.unsafeApply(7, 6),
      // Negative diagonal
      Coords.unsafeApply(7, 4),
      Coords.unsafeApply(5, 6),
      Coords.unsafeApply(4, 7)
    )
  }
}
