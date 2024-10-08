package model.board

import model.PieceType

case class BoardOfColor(
    pawns: BitBoard,
    rooks: BitBoard,
    knights: BitBoard,
    bishops: BitBoard,
    queens: BitBoard,
    kings: BitBoard
) extends BitBoardLike {

  // Get combined BitBoard of all pieces of this color
  lazy val allPieces: BitBoard =
    BitBoard.combineBoards(toSeq)

  // Helper method to get BitBoard for a specific piece type
  private def getBitBoardByPiece(pieceType: PieceType): BitBoard =
    pieceType match {
      case PieceType.Pawn   => pawns
      case PieceType.Rook   => rooks
      case PieceType.Knight => knights
      case PieceType.Bishop => bishops
      case PieceType.Queen  => queens
      case PieceType.King   => kings
      case PieceType.Empty  => allPieces.invert
    }

  // Helper method to update a specific BitBoard based on piece type
  private def updateBitBoard(
      pieceType: PieceType,
      f: BitBoard => BitBoard
  ): BoardOfColor =
    pieceType match {
      case PieceType.Pawn   => copy(pawns = f(pawns))
      case PieceType.Rook   => copy(rooks = f(rooks))
      case PieceType.Knight => copy(knights = f(knights))
      case PieceType.Bishop => copy(bishops = f(bishops))
      case PieceType.Queen  => copy(queens = f(queens))
      case PieceType.King   => copy(kings = f(kings))
      case PieceType.Empty  => this
    }

  // ********************************** SET ************************************

  // Set piece methods
  def setPiece(pieceType: PieceType, pos: Coords): BoardOfColor =
    setPiece(pieceType, BitPosition.fromCoords(pos))
  def setPiece(pieceType: PieceType, pos: BitPosition): BoardOfColor =
    updateBitBoard(pieceType, _.set(pos))

  // ********************************* RESET ***********************************

  // Reset piece methods
  def resetPiece(pieceType: PieceType, pos: Coords): BoardOfColor =
    resetPiece(pieceType, BitPosition.fromCoords(pos))
  def resetPiece(pieceType: PieceType, pos: BitPosition): BoardOfColor =
    updateBitBoard(pieceType, _.reset(pos))

  // ********************************** GET ************************************

  // Get piece methods
  def getPiece(pieceType: PieceType, pos: Coords): Boolean =
    getPiece(pieceType, BitPosition.fromCoords(pos))
  def getPiece(pieceType: PieceType, pos: BitPosition): Boolean = {
    val board = getBitBoardByPiece(pieceType)
    board.get(pos)
  }

  override def getPositions: Seq[Coords] =
    allPieces.board.positionsOfSetBits().map(_.toCoords())

  // Get all positions occupied by a specific piece type
  def getPositionsByPieceType(pieceType: PieceType): Seq[Coords] =
    getBitBoardByPiece(pieceType).board.positionsOfSetBits().map(_.toCoords())

  // ********************************** CHECK *********************************

  override def isOccupied(pos: BitPosition): Boolean =
    allPieces.get(pos)

  // Check if board has any piece of a specific color and type
  def isEmptyOfType(pieceType: PieceType, pos: BitPosition): Boolean =
    getBitBoardByPiece(pieceType).get(pos) == false
  def isEmptyOfType(pieceType: PieceType, coords: Coords): Boolean =
    isEmptyOfType(pieceType, BitPosition.fromCoords(coords))

  // ********************************** COUNT **********************************

  // Count total number of pieces
  override def countPieces: Int =
    allPieces.board.countSetBits()

  // Count pieces of specific type
  def countPiecesByPieceType(pieceType: PieceType): Int =
    getBitBoardByPiece(pieceType).board.countSetBits()

  // ********************************** REST ***********************************

  // Convert to sequence of BitBoards (useful for operations on all pieces)
  def toSeq: Seq[BitBoard] =
    Seq(pawns, rooks, knights, bishops, queens, kings)
}

object BoardOfColor {
  // Create an empty board
  def empty: BoardOfColor = BoardOfColor(
    BitBoard.empty,
    BitBoard.empty,
    BitBoard.empty,
    BitBoard.empty,
    BitBoard.empty,
    BitBoard.empty
  )

  // Create initial setup for white pieces
  def initialWhite: BoardOfColor = {
    val pawns = BitBoard(
      Coords.fromString("a2").get,
      Coords.fromString("b2").get,
      Coords.fromString("c2").get,
      Coords.fromString("d2").get,
      Coords.fromString("e2").get,
      Coords.fromString("f2").get,
      Coords.fromString("g2").get,
      Coords.fromString("h2").get
    )
    val rooks =
      BitBoard(Coords.fromString("a1").get, Coords.fromString("h1").get)
    val knights =
      BitBoard(Coords.fromString("b1").get, Coords.fromString("g1").get)
    val bishops =
      BitBoard(Coords.fromString("c1").get, Coords.fromString("f1").get)
    val queens = BitBoard(Coords.fromString("d1").get)
    val kings = BitBoard(Coords.fromString("e1").get)

    BoardOfColor(pawns, rooks, knights, bishops, queens, kings)
  }

  // Create initial setup for black pieces
  def initialBlack: BoardOfColor = {
    val pawns = BitBoard(
      Coords.fromString("a7").get,
      Coords.fromString("b7").get,
      Coords.fromString("c7").get,
      Coords.fromString("d7").get,
      Coords.fromString("e7").get,
      Coords.fromString("f7").get,
      Coords.fromString("g7").get,
      Coords.fromString("h7").get
    )
    val rooks =
      BitBoard(Coords.fromString("a8").get, Coords.fromString("h8").get)
    val knights =
      BitBoard(Coords.fromString("b8").get, Coords.fromString("g8").get)
    val bishops =
      BitBoard(Coords.fromString("c8").get, Coords.fromString("f8").get)
    val queens = BitBoard(Coords.fromString("d8").get)
    val kings = BitBoard(Coords.fromString("e8").get)

    BoardOfColor(pawns, rooks, knights, bishops, queens, kings)
  }
}
