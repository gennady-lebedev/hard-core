package dev.rudiments.hardcore.http.spec

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import dev.rudiments.hardcore.dsl.ID._
import dev.rudiments.hardcore.dsl._
import dev.rudiments.hardcore.http.{CrudRouter, IDPath}
import dev.rudiments.hardcore.repo.memory.MemoryRepo
import dev.rudiments.hardcore.http.CirceSupport._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class CrudRouterSpec extends WordSpec with Matchers with ScalatestRouteTest {
  case class Example(
    id: Long,
    name: String
  )

  implicit val meta: Meta[Example] = Meta(value => ID(value.id))
  val repo: MemoryRepo[Example] = new MemoryRepo[Example]
  val router: CrudRouter[Example] = new CrudRouter[Example]("example", repo.handle, IDPath[Example, Long])
  val routes = Route.seal(router.routes)
  val sample = Example(42, "sample")
  val id = sample.identify

  "no element by ID" in {
    Get("/example/42") ~> routes ~> check {
      response.status should be (StatusCodes.NotFound)
    }
  }

  "put item into repository" in {
    Post("/example", sample) ~> routes ~> check {
      response.status should be (StatusCodes.Created)
      responseAs[Example] should be (sample)
    }
  }

  "update item in repository" in {
    Put("/example/42", Example(42, "test")) ~> routes ~> check {
      response.status should be (StatusCodes.OK)
      responseAs[Example] should be (Example(42, "test"))
    }
  }

  "multiple inserts with same ID causes exception" in {
    Post("/example", sample) ~> routes ~> check {
      response.status should be (StatusCodes.Conflict)
    }
  }

  "delete item from repository" in {
    Delete("/example/42") ~> routes ~> check {
      response.status should be (StatusCodes.NoContent)
    }
  }

  "endure 10.000 records" in {
    (1 to 10000).foreach { i =>
      Post("/example", Example(i, s"$i'th element")) ~> routes ~> check {
        response.status should be (StatusCodes.Created)
      }
    }
    repo.count().unsafeRunSync() should be (10000)
  }

  "endure 190.000 batch" in {
    Put("/example", (10001 to 200000).map(i => Example(i, s"$i'th element"))) ~> routes ~> check {
      response.status should be (StatusCodes.Created)
      repo.count().unsafeRunSync() should be (200000)
    }
  }

  "clear repository" in {
    Delete("/example") ~> routes ~> check {
      response.status should be (StatusCodes.NoContent)
      repo.count().unsafeRunSync() should be (0)
    }
  }

}
