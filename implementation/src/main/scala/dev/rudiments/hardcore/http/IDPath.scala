package dev.rudiments.hardcore.http

import java.sql.Date

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives.{path, _}
import dev.rudiments.hardcore.dsl.ID

import scala.reflect.runtime.universe._

object IDPath {
  def apply[A, K : TypeTag]: Directive1[ID[A]] = {
         if(typeOf[K] =:= typeOf[Long])   pathPrefix(LongNumber).map(l => ID[A, Long](l))
    else if(typeOf[K] =:= typeOf[Int])    pathPrefix(IntNumber).map(i => ID[A, Int](i))
    else if(typeOf[K] =:= typeOf[String]) pathPrefix(Segment).map(s => ID[A, String](s))
    else if(typeOf[K] =:= typeOf[Date])   pathPrefix(Segment).map(s => ID[A, Date](Date.valueOf(s)))
    else ???
  }
}
