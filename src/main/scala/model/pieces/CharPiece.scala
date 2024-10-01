package model.pieces

enum CharPiece(val char: Char) {
  case Empty extends CharPiece(' ')
  case Pawn extends CharPiece('p')
  case Bishop extends CharPiece('b')
  case Knight extends CharPiece('n')
  case Rook extends CharPiece('r')
  case Queen extends CharPiece('q')
  case King extends CharPiece('k')
}
