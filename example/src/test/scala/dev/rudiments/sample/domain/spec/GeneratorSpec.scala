package dev.rudiments.sample.domain.spec

import dev.rudiments.hardcore.dsl._
import dev.rudiments.hardcore.dsl.ID._
import dev.rudiments.hardcore.repo.memory.MemoryRepo
import dev.rudiments.sample.domain.DayOfWeek._
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratedValues, GeneratorConfig}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class GeneratorSpec extends WordSpec with Matchers {

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

  implicit val configsMeta: Meta[GeneratorConfig] = Meta(value => ID(value.id))
  val configs: MemoryRepo[GeneratorConfig] = new MemoryRepo[GeneratorConfig]

  implicit val generatedMeta: Meta[GeneratedConfig] = Meta { value =>
    value.config match {
      case ID1(v) => ID(v)
      case Instance(GeneratorConfig(id, _)) => ID(id)
      case other => ???
    }
  }
  val generatedConfigs: MemoryRepo[GeneratedConfig] = new MemoryRepo[GeneratedConfig]

  implicit val valuesMeta: Meta[GeneratedValues] = Meta { value =>
    value.config match {
      case ID1(v) => ID(v, value.id)
      case Instance(GeneratorConfig(id, _)) => ID(id, value.id)
      case other => ???
    }
  }
  val generatedValues: MemoryRepo[GeneratedValues] = new MemoryRepo[GeneratedValues]

  "save all to repos" in {
    val config = GeneratorConfig(1, "first")
    val generated = config.generate
    val values = generated.generate

    configs.create(config).attempt.unsafeRunSync() should be (Right(Created(ID(1), config)))
    generatedConfigs.create(generated).attempt.unsafeRunSync() should be (Right(Created(ID(1), generated)))
    generatedValues.createAll(values).attempt.unsafeRunSync()/* should be (
      Right(AllCreated(values.groupBy(v => (v.config, v.id)).mapValues(_.head)))
    )*/

    configs(Read(ID(1))) should be (Result(ID(1), config))
    generatedConfigs(Read(ID(1))) should be (Result(ID(1), generated))
    generatedValues(Read(ID(1, 1))) should be (Result(ID(1, 1), values(1 - 1)))
    generatedValues(Read(ID(1, 16384))) should be (Result(ID(1, 16384), values(16384 - 1)))
  }

  "endure 100 configs" in {
    (2 to 100)
      .map(i => GeneratorConfig(i, s"$i'th config"))
      .map { config =>
        configs(Create(config.identify, config))
        config.generate
      }.map { generated =>
        generatedConfigs(Create(generated.identify, generated))
        generated.generate
      }.map { values =>
        generatedValues(CreateAll(values.groupBy(v => (v.config, v.id)).mapValues(_.head)))
    }
    configs.count().unsafeRunSync() should be (100)
    generatedConfigs.count().unsafeRunSync() should be (100)
    generatedValues.count().unsafeRunSync() should be (16384 * 100) // 1.6 M+, 16 sec, 100K+/sec on my mac
  }

}
