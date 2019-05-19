package dev.rudiments.sample.domain.spec

import dev.rudiments.hardcore.dsl._
import dev.rudiments.hardcore.repo.memory.MemoryRepo
import dev.rudiments.sample.domain.DayOfWeek._
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratorConfig}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.Matchers._

@RunWith(classOf[JUnitRunner])
class GeneratorSpec extends WordSpec with Matchers {
  implicit val configsMeta: Meta[GeneratorConfig] = Meta(value => ID(value.id))
  val configs: MemoryRepo[GeneratorConfig] = new MemoryRepo[GeneratorConfig]

  implicit val generatedMeta: Meta[GeneratedConfig] = Meta { value =>
    value.config match {
      case ID1(v) => ID(v)
      case Instance(GeneratorConfig(id, _)) => ID(id)
      case other => ???
    }
  }
  val generated: MemoryRepo[GeneratedConfig] = new MemoryRepo[GeneratedConfig]


  "config generating config" in {
    val config = GeneratorConfig(1, "first")
    val generated = config.generate
    generated.config should be (ID(1))
    generated.data.keys should be(Set(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday))

    all (generated.data.values.map(_.sum)) should be <= 100
    all (generated.data.values.map(_.sum)) should be >= 0
  }

  "config generating values" in {
    val config = GeneratorConfig(1, "first")
    val generated = config.generate
    val values = generated.generate
    values.size should be (16384)
  }

}
