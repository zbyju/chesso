<p><a target="_blank" href="https://app.eraser.io/workspace/xr6rfrnaWzvhcZfpPcUK" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

# Chesso - A scala chess library
## Usage
This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL, `sbt test` to run tests. You can also make sbt watch file changes and rerun each time using `~` (`sbt ~run`, `sbt ~test`).

For more information on the sbt-dotty plugin, see the
[﻿scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

## Features
This project implements the **board** and **game** interfaces to simulate a chess game.

### Board
Tracks the state of the board - mainly the piece placement.

## Dependecies
- Scalatest - for Unit Testing
## Documentation
### Game
**Methods:**

```
lazy val isFinished: Boolean
lazy val result: Result
def move(move: Move): Either[String, Game]
```
### [﻿Board](https://app.eraser.io/workspace/xr6rfrnaWzvhcZfpPcUK?elements=ndkt6UswRRi3-X46qAdHvA) 
**Methods:**

```
lazy val whitePieces: BoardPieces
lazy val blackPieces: BoardPieces
lazy val allPieces: BoardPieces

def sq(coords: Coords): Tile
def sq(coords: String): Option[Tile]
def move(move: Move): Board
```





<!--- Eraser file: https://app.eraser.io/workspace/xr6rfrnaWzvhcZfpPcUK --->