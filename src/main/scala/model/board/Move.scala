package model.board

final case class Move(
    val from: Coords,
    val to: Coords,
    val nextBoardState: BoardState = BoardState.empty
)
