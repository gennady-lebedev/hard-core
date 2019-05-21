package dev.rudiments.hardcore.http

import java.sql.{Date, Timestamp}

import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.Decoder.Result
import io.circe.generic.extras.{AutoDerivation, Configuration}
import io.circe.syntax._
import io.circe._
import dev.rudiments.hardcore.dsl.SortOrder.{Asc, Desc}
import dev.rudiments.hardcore.dsl._
import enumeratum.EnumEntry

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

  implicit def instanceEncoder[A: Encoder]: Encoder[Instance[A]] =
    implicitly[Encoder[A]].contramap(_.value)

  implicit def refFormat[A: Encoder]: Encoder[Ref[A]] with Decoder[Ref[A]] = new Encoder[Ref[A]] with Decoder[Ref[A]] {
    override def apply(a: Ref[A]): Json = a match {
      case i: Instance[A] => implicitly[Encoder[Instance[A]]].apply(i)
      case ID0() => Json.obj()
      case ID1(id) => id.toString.asJson
      case ID2(id1, id2) => Json.arr(id1.toString.asJson, id2.toString.asJson)
      case AutoID() => Json.fromString("auto")
      case other => throw new IllegalArgumentException(s"$other not supported")
    }

    override def apply(c: HCursor): Result[Ref[A]] = ???
  }

  implicit def enumFormat: Encoder[EnumEntry] with Decoder[EnumEntry] = new Encoder[EnumEntry] with Decoder[EnumEntry] {
    override def apply(a: EnumEntry): Json = a.entryName.asJson

    override def apply(c: HCursor): Result[EnumEntry] = ???
  }
}
