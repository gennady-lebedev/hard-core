package dev.rudiments.sample

import dev.rudiments.hardcore.dsl._
import dev.rudiments.hardcore.http.{CrudRouter, IDPath}
import dev.rudiments.hardcore.repo.memory.MemoryRepo
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratedValues, GeneratorConfig}
import dev.rudiments.sample.CirceSupport._

object AppContext {
  implicit val itemMeta: Meta[Item] = Meta[Item](item => ID(item.id))
  val itemRepo = new MemoryRepo[Item]
  val itemRouter = new CrudRouter[Item]("item", itemRepo, IDPath[Item, Long])

  import dev.rudiments.sample.skill.Generator._
  val configRouter = new CrudRouter[GeneratorConfig]("config", configs, IDPath[GeneratorConfig, Long])
  val generatedRouter = new CrudRouter[GeneratedConfig]("generated", generatedConfigs, IDPath[GeneratedConfig, Long])
  val valuesRouter = new CrudRouter[GeneratedValues]("values", generatedValues, IDPath[GeneratedValues, Long])
}

case class Item(
  id: Long,
  name: String
)
