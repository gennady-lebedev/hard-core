package dev.rudiments.sample.skill

import com.typesafe.scalalogging.StrictLogging
import dev.rudiments.hardcore.dsl.{ID, _}
import dev.rudiments.hardcore.dsl.ID._
import dev.rudiments.hardcore.repo.memory.MemoryRepo
import dev.rudiments.sample.domain.{GeneratedConfig, GeneratedValues, GeneratorConfig}


object Generator extends StrictLogging {
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

  val generateResolver: Resolver = {
    case GenerateConfig(ID1(id)) => Read[ID[GeneratorConfig], GeneratorConfig](ID(id))
    case GenerateValues(ID1(id)) => GenerateConfig(ID1(id))
  }

  val generateConfig: PF2 = {
    case (GenerateConfig(id), Result(idR, config: GeneratorConfig)) if id == idR =>
      val generated = config.generate
      generatedConfigs.create(generated.identify, generated).map {
        case Created(_, _) => ConfigGenerated(config, generated)
      }.unsafeRunSync()
  }

  val generateValues: PF2 = {
    case (GenerateValues(id), ConfigGenerated(config, generated)) if id == generated.identify =>
      val values = generated.generate
      generatedValues.createAll(values).map {
        case AllCreated(_) => ValuesGenerated(generated, values)
      }.unsafeRunSync()
  }
}

case class GenerateConfig(id: ID[GeneratorConfig]) extends Command
case class ConfigGenerated(config: GeneratorConfig, generated: GeneratedConfig) extends Event
case class FailedToGenerateConfig(id: ID[GeneratorConfig]) extends Error

case class GenerateValues(id: ID[GeneratedConfig]) extends Command
case class ValuesGenerated(config: GeneratedConfig, values: Seq[GeneratedValues]) extends Event
case class FailedToGenerateValues(id: ID[GeneratorConfig]) extends Error