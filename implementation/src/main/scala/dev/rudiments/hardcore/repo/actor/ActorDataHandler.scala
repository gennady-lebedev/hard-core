package dev.rudiments.hardcore.repo.actor

import dev.rudiments.hardcore.dsl._
import dev.rudiments.hardcore.eventstore.{ActorMind, ActorHardSkill}
import dev.rudiments.hardcore.repo.memory.SyncMemoryRepo

import scala.language.postfixOps


class ActorDataHandler[A](implicit meta: Meta[A], es: ActorMind)
  extends ActorHardSkill(new SyncMemoryRepo[A]()) {
}