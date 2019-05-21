package dev.rudiments.sample.domain

import dev.rudiments.hardcore.dsl.{Command, ID, Ref}
import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable.IndexedSeq
import scala.util.Random

case class GeneratorConfig(
  id: Long,
  name: String
) extends Command {
  def generate = GeneratedConfig(
    config = ID(id),
    data = DayOfWeek.values.map { day =>
      day -> (-1 to 2)
    }.toMap
  )
}

case class GeneratedConfig(
  config: Ref[GeneratorConfig],
  data: Map[DayOfWeek, Seq[Int]]
) {
  def generate: Seq[GeneratedValues] = {
    val size = data
      .values
      .map(_.size)
      .product

    (1 to size).map { i =>
      GeneratedValues(
        config = config,
        id = i,
        data = DayOfWeek.values.map(day => day -> Random.nextDouble()).toMap
      )
    }
  }
}

case class GeneratedValues(
  config: Ref[GeneratorConfig],
  id: Int,
  data: Map[DayOfWeek, Double]
)

sealed class DayOfWeek(val shortName: String, val num: Int) extends EnumEntry

object DayOfWeek extends Enum[DayOfWeek] {
  override def values: IndexedSeq[DayOfWeek] = findValues

  def withShortName(string: String): DayOfWeek = string match {
    case Monday.shortName => Monday
    case Tuesday.shortName => Tuesday
    case Wednesday.shortName => Wednesday
    case Thursday.shortName => Thursday
    case Friday.shortName => Friday
    case Saturday.shortName => Saturday
    case Sunday.shortName => Sunday
  }

  case object Monday    extends DayOfWeek("mon", 1)
  case object Tuesday   extends DayOfWeek("tue", 2)
  case object Wednesday extends DayOfWeek("wed", 3)
  case object Thursday  extends DayOfWeek("thu", 4)
  case object Friday    extends DayOfWeek("fri", 5)
  case object Saturday  extends DayOfWeek("sat", 6)
  case object Sunday    extends DayOfWeek("sun", 7)
}
