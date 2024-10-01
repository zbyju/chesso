package model

import scala.math.ScalaNumericConversions

case class BitPosition private (value: Int) extends Ordered[BitPosition]:
  override def compare(that: BitPosition): Int = this.value.compare(that.value)

  def toCoords(): Coords = {
    val rank = value / 8
    val file = value % 8
    Coords(file.toByte, rank.toByte).get
  }

object BitPosition:
  def apply(value: Int): Option[BitPosition] =
    if (value >= 0 && value <= 63) Some(new BitPosition(value)) else None

  def unsafeApply(value: Int): BitPosition =
    require(
      value >= 0 && value <= 63,
      "BitPosition value must be between 0 and 63"
    )
    new BitPosition(value)

  given Ordering[BitPosition] = Ordering.by(_.value)

  def unapply(BitPosition: BitPosition): Option[Int] = Some(BitPosition.value)

  extension (start: BitPosition)
    def to(end: BitPosition): Seq[BitPosition] =
      (start.value to end.value).map(BitPosition.unsafeApply)

    def until(end: BitPosition): Seq[BitPosition] =
      (start.value until end.value).map(BitPosition.unsafeApply)

  implicit def bitPositionToInt(pos: BitPosition): Int = pos.value

  def fromCoords(coords: Coords): BitPosition = new BitPosition(
    coords.file + coords.rank * 8
  )
