package dev.rudiments.hardcore.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directive1, Route, StandardRoute}
import io.circe.{Decoder, Encoder}
import dev.rudiments.hardcore.dsl._


class CrudRouter[A : Meta : Encoder : Decoder](
  prefix: String,
  handler: PF1,
  idDirective: Directive1[ID[A]],
  extraIdCommands: Map[String, ID[A] => Command] = Map.empty[String, ID[A] => Command]
) extends Router {
  import dev.rudiments.hardcore.http.CirceSupport._
  import dev.rudiments.hardcore.dsl.ID._

  override lazy val routes: Route = pathPrefix(prefix) {
    pathRoute ~ idDirective { id =>
      idRoute(id)
    }
  }

  def pathRoute: Route = pathEndOrSingleSlash {
    get {
      responseWith(handler(Query.all))
    } ~ post {
      entity(as[A]) { value =>
        responseWith(handler(Create(value.identify, value)))
      }
    } ~ put {
      entity(as[List[A]]) { batch =>
        responseWith(handler(CreateAll(batch.groupBy(_.identify).mapValues(_.head))))
      }
    } ~ delete {
      responseWith(handler(DeleteAll()))
    }
  }

  def idRoute(id: ID[A]): Route = pathEndOrSingleSlash {
    get {
      responseWith(handler(Read(id)))
    } ~ put {
      entity(as[A]) { newValue =>
        responseWith(handler(Update(newValue.identify, newValue)))
      }
    } ~ delete {
      responseWith(handler(Delete[ID[A], A](id)))
    }
  } ~ extraIdCommands.map { case (suffix, f) =>
    path(suffix) {
      post {
        responseWith(handler(f(id)))
      }
    }
  }.foldRight(reject(): Route)(_ ~ _)

  def responseWith(event: Event): StandardRoute = event match {
    case c: QueryResult[A] =>  complete(StatusCodes.OK, c.values)

    case c: Created[_, A] =>  complete(StatusCodes.Created, c.value)
    case c: Result[_, A] =>   complete(StatusCodes.OK, c.value)
    case c: Updated[_, A] =>  complete(StatusCodes.OK, c.newValue)
    case _: Deleted[_, A] =>  complete(StatusCodes.NoContent)

    case _: AllCreated[_, A] => complete(StatusCodes.Created)
    case _: AllDeleted[_, A] => complete(StatusCodes.NoContent)

    case _: NotFound[_, A] =>      complete(StatusCodes.NotFound)
    case _: AlreadyExists[_, A] => complete(StatusCodes.Conflict)

    case _: Error => complete(StatusCodes.InternalServerError)
  }
}
