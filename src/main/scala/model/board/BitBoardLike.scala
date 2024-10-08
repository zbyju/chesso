package model.board

trait BitBoardLike {

  // Check if a given position is occupied (bit set to 1)
  def isOccupied(position: BitPosition): Boolean
  def isOccupied(coords: Coords): Boolean = isOccupied(
    BitPosition.fromCoords(coords)
  )

  // Check if mulitple positions are occupied (bits set to 1)
  def allOccupied(positions: Seq[Coords]): Boolean =
    positions.forall(isOccupied)

  // Check if any position is occupied
  def anyOccupied(positions: Seq[Coords]): Boolean =
    positions.exists(isOccupied)

  // Check if all positions are empty
  def checkEmpty(positions: Seq[Coords]): Boolean =
    positions.forall(isEmpty)

  // Get coordinates of all positions where the bit is set (1s in the bitboard)
  def getPositions: Seq[Coords]

  // Check if the bitboard is completely empty (no bits set to 1)
  def isEmpty(position: BitPosition): Boolean = !isOccupied(position)
  def isEmpty(coords: Coords): Boolean = !isOccupied(coords)

  // Get the count of pieces (number of 1s in the bitboard)
  def countPieces: Int

}
