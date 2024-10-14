<p><a target="_blank" href="https://app.eraser.io/workspace/xr6rfrnaWzvhcZfpPcUK" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

# Chesso - A scala chess library
## Usage
This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL, `sbt test` to run tests. You can also make sbt watch file changes and rerun each time using `~` (`sbt ~run`, `sbt ~test`).

For more information on the sbt-dotty plugin, see the
[﻿scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

## Features

This project implements the game of chess with a CLI interface to play the game.

### ChessBoard
Tracks the state of the board, including:
- Piece placement on the board
- Metadata about the board state
  - Whose turn it is
  - Castling rights
  - En passant availability
- Analysis methods for:
  - Is the player in check
  - Is the player checkmated
  - What squares are under attack by either player

### [﻿Board](https://app.eraser.io/workspace/xr6rfrnaWzvhcZfpPcUK?elements=ndkt6UswRRi3-X46qAdHvA) 
**Methods:**

```scala
lazy val isInCheck: Boolean
lazy val isCheckmated: Boolean

def sq(coords: Coords): PieceType
def sq2(coords: Coords): (PieceType, Color)
def positions: Seq[Coords]
def positions(color: Color): Seq[Coords]

def move(from: Coords, to: Coords): Option[ChessBoard]
def isLegal(from: Coords, to: Coords): Boolean

def attacked: BitBoard // Attacked by the playing player
def attacked(color: Color): BitBoard

def isAttacked(coords: Coords): Boolean
def isOccupied(coords: Coords): Boolean
def isOccupiedBy(color: Color, coords: Coords): Boolean

def theoreticalMoves(coords: Coords): BitBoard
def legalMoves(coords: Coords): BitBoard
```

### Game
**Methods:**

```scala
lazy val isFinished: Boolean
lazy val result: Result
def move(move: Move): Either[String, Game]
```

## Dependecies
- Scalatest - for Unit Testing
## Documentation





<!--- Eraser file: https://app.eraser.io/workspace/xr6rfrnaWzvhcZfpPcUK --->
