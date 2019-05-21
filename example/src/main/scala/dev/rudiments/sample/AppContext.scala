package dev.rudiments.sample

import dev.rudiments.hardcore.dsl._
import dev.rudiments.hardcore.repo.memory.MemoryRepo

object AppContext {
  implicit val itemMeta: Meta[Item] = Meta[Item](item => ID(item.id))
  val itemRepo = new MemoryRepo[Item]
}

case class Item(
  id: Long,
  name: String
)
