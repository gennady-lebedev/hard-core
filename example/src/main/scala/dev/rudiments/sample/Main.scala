package dev.rudiments.sample

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import dev.rudiments.hardcore.http.{CrudRouter, IDPath, RootRouter, Router}
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratedValues, GeneratorConfig}

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object Main extends App with LazyLogging {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = actorSystem.dispatcher
  implicit val mat: ActorMaterializer = ActorMaterializer()

  import CirceSupport._
  import AppContext._
  import dev.rudiments.sample.skill.Generator._

  try {
    val config = ConfigFactory.load()
    new RootRouter(config,
      new HealthRouter,
      new CrudRouter[Item]("item", itemRepo, IDPath[Item, Long]),
      new CrudRouter[GeneratorConfig]("config", configs, IDPath[GeneratorConfig, Long]),
      new CrudRouter[GeneratedConfig]("generated", generatedConfigs, IDPath[GeneratedConfig, Long]),
      new CrudRouter[GeneratedValues]("values", generatedValues, IDPath[GeneratedValues, Long])
    ).bind()
  } catch {
    case e: Throwable =>
      logger.error("Error while initializing app, shutdown", e)
      actorSystem.terminate().onComplete {
        case Success(t) => logger.info("Terminated {}", t)
        case Failure(err) =>
          logger.error("Termination failed with error", err)
          sys.exit(-1)
      }
  }
}

import akka.http.scaladsl.server.Directives._
class HealthRouter extends Router {
  override val routes: Route = path("health") {
    get {
      complete(StatusCodes.OK)
    }
  }
}