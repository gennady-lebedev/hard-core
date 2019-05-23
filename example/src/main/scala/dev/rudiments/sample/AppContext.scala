package dev.rudiments.sample

import CirceSupport._
import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import com.typesafe.config.Config
import dev.rudiments.hardcore.http.{CrudRouter, IDPath, RootRouter}
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratedValues, GeneratorConfig}
import dev.rudiments.sample.skill.{ConfigGenerated, GenerateConfig}
import dev.rudiments.sample.skill.Generator._

object AppContext {
  def root(config: Config)(implicit as: ActorSystem, mat: Materializer) =
    new RootRouter(
      config,
      new HealthRouter,
      new CrudRouter[GeneratorConfig](
        "config",
        teach,
        IDPath[GeneratorConfig, Long],
        Map("generate" -> (
          { id => GenerateConfig(id) },
          { case ConfigGenerated(_, generated) => complete(StatusCodes.Created, generated)}
        ))
      ),
      new CrudRouter[GeneratedConfig]("generated", generatedConfigs, IDPath[GeneratedConfig, Long]),
      new CrudRouter[GeneratedValues]("values", generatedValues, IDPath[GeneratedValues, Long])
    )
}
