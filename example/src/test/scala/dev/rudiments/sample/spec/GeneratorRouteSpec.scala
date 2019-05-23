package dev.rudiments.sample.spec

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.typesafe.config.ConfigFactory
import dev.rudiments.hardcore.dsl._
import dev.rudiments.sample.AppContext
import dev.rudiments.sample.CirceSupport._
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratorConfig}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class GeneratorRouteSpec extends WordSpec with Matchers with ScalatestRouteTest {

  private val router = AppContext.root(ConfigFactory.load())
  private val routes = Route.seal(router.routes)

  "health check" in {
    Get("/api/health") ~> routes ~> check {
      response.status should be (StatusCodes.OK)
    }
  }

  "create config" in {
    Post("/api/config", GeneratorConfig(1, "first")) ~> routes ~> check {
      response.status should be (StatusCodes.Created)
      responseAs[GeneratorConfig] should be (GeneratorConfig(1, "first"))
    }
  }

  "check config created" in {
    Get("/api/config") ~> routes ~> check {
      response.status should be (StatusCodes.OK)
      responseAs[Seq[GeneratorConfig]] should be (Seq(GeneratorConfig(1, "first")))
    }
  }

  "generate config" in {
    Post("/api/config/1/generate") ~> routes ~> check {
      response.status should be (StatusCodes.Created)
      responseAs[GeneratedConfig] should be (
        GeneratedConfig(ID1(1), GeneratorConfig(1, "first").generate.data)
      )
    }
  }

  "check generated configs" in {
    Get("/api/generated") ~> routes ~> check {
      response.status should be (StatusCodes.OK)
      responseAs[Seq[GeneratedConfig]].size should be (1)
    }
  }

}
