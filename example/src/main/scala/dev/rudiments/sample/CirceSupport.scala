package dev.rudiments.sample

import java.sql.{Date, Timestamp}

import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import dev.rudiments.hardcore.dsl.SortOrder.{Asc, Desc}
import dev.rudiments.hardcore.dsl._
import dev.rudiments.sample.domain.DayOfWeek
import enumeratum._
import io.circe.Decoder.Result
import io.circe._
import io.circe.generic.extras.{AutoDerivation, Configuration}
import io.circe.syntax._

object CirceSupport extends AutoDerivation with FailFastCirceSupport {
  implicit val configuration: Configuration = Configuration.default.withDefaults
  implicit val printer: Printer = Printer.noSpaces.copy(dropNullValues = true)

  implicit val timestampFormat: Encoder[Timestamp] with Decoder[Timestamp] = new Encoder[Timestamp] with Decoder[Timestamp] {
    override def apply(a: Timestamp): Json = Encoder.encodeString.apply(a.toString)
    override def apply(c: HCursor): Result[Timestamp] = Decoder.decodeString.map(Timestamp.valueOf).apply(c)
  }

  implicit val dateFormat: Encoder[Date] with Decoder[Date] = new Encoder[Date] with Decoder[Date] {
    override def apply(a: Date): Json = Encoder.encodeString.apply(a.toString)
    override def apply(c: HCursor): Result[Date] = Decoder.decodeString.map(Date.valueOf).apply(c)
  }

  implicit def filterEncoder[A]: Encoder[Filter[A]] = new Encoder[Filter[A]] {
    override def apply(a: Filter[A]): Json = Encoder.encodeString.apply(a.toString)
  }

  implicit def sortEncoder[A]: Encoder[Sort[A]] = new Encoder[Sort[A]] {
    override def apply(a: Sort[A]): Json = Encoder.encodeString.apply(a.order match {
      case Desc => "!" + a.field
      case Asc => a.field
    })
  }

  implicit def queryEncoder[A: Encoder]: Encoder[QueryResult[A]] = new Encoder[QueryResult[A]] {
    override def apply(a: QueryResult[A]): Json = a.values.asJson
  }

  implicit def instanceEncoder[A: Encoder]: Encoder[Instance[A]] =
    implicitly[Encoder[A]].contramap(_.value)

  implicit def refFormat[A: Encoder]: Encoder[Ref[A]] with Decoder[Ref[A]] = new Encoder[Ref[A]] with Decoder[Ref[A]] {
    override def apply(a: Ref[A]): Json = a match {
      case i: Instance[A] => implicitly[Encoder[Instance[A]]].apply(i)
      case ID0() => Json.arr()
      case ID1(id) => id.toString.asJson
      case ID2(id1, id2) => Json.arr(id1.toString.asJson, id2.toString.asJson)
      case AutoID() => Json.fromString("auto")
      case other => throw new IllegalArgumentException(s"$other not supported")
    }

    override def apply(c: HCursor): Result[Ref[A]] = Decoder.decodeString(c).map(s => ID(s.toLong))
  }

  implicit def enumFormat[E <: EnumEntry](enum: Enum[E]): Encoder[E] with Decoder[E] = new Encoder[E] with Decoder[E] {
    override def apply(a: E): Json = a.entryName.asJson

    override def apply(c: HCursor): Result[E] = implicitly[Decoder[String]].apply(c).flatMap { s =>
      val maybeMember = enum.withNameOption(s)
      maybeMember match {
        case Some(member) => Right(member)
        case _ =>
          Left(DecodingFailure(s"'$s' is not a member of enum $enum", c.history))
      }
    }
  }

  implicit def daysMapFormat[V: Encoder](implicit mapDecoder: Decoder[Map[String, V]]): Encoder[Map[DayOfWeek, V]] with Decoder[Map[DayOfWeek, V]] =
    new Encoder[Map[DayOfWeek, V]] with Decoder[Map[DayOfWeek, V]]{
      override def apply(a: Map[DayOfWeek, V]): Json = Json.obj(a.map { case (day, v) =>
          day.shortName -> v.asJson
      }.toSeq: _*)

      override def apply(c: HCursor): Result[Map[DayOfWeek, V]] = implicitly[Decoder[Map[String, V]]].map { m =>
        m.map { case (d, v) =>
          DayOfWeek.withShortName(d) -> v
        }
      }.apply(c)
    }
}
