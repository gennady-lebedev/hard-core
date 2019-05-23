package dev.rudiments.sample

import dev.rudiments.hardcore.dsl._
import dev.rudiments.hardcore.repo.memory.MemoryRepo
import CirceSupport._
import akka.actor.ActorSystem
import akka.stream.Materializer
import com.typesafe.config.Config
import dev.rudiments.hardcore.http.{CrudRouter, IDPath, RootRouter}
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratedValues, GeneratorConfig}
import dev.rudiments.sample.skill.GenerateConfig
import dev.rudiments.sample.skill.Generator._

object AppContext {
  implicit val itemMeta: Meta[Item] = Meta[Item](item => ID(item.id))
  val itemRepo = new MemoryRepo[Item]

  def root(config: Config)(implicit as: ActorSystem, mat: Materializer) =
    new RootRouter(
      config,
      new HealthRouter,
      new CrudRouter[Item]("item", itemRepo, IDPath[Item, Long]),
      new CrudRouter[GeneratorConfig](
        "config",
        teach,
        IDPath[GeneratorConfig, Long],
        Map("generate" -> { id => GenerateConfig(id) })
      ),
      new CrudRouter[GeneratedConfig]("generated", generatedConfigs, IDPath[GeneratedConfig, Long]),
      new CrudRouter[GeneratedValues]("values", generatedValues, IDPath[GeneratedValues, Long])
    )
}

case class Item(
  id: Long,
  name: String
)
