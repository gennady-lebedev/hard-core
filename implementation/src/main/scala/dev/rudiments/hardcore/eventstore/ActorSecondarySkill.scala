package dev.rudiments.hardcore.eventstore

import akka.actor.{Actor, Props}
import com.typesafe.scalalogging.StrictLogging
import dev.rudiments.hardcore.dsl._

import scala.concurrent.{Future, Promise}

class ActorSecondarySkill(val f: PF2, val r: Resolver)(implicit val mind: ActorMind)
  extends SecondarySkill {

  override def async(command: Command): Future[Event] = {
    val promise = Promise[Event]
    mind.system.actorOf(SecondaryActionActor.props(f, command, r(command), promise)(mind))
    promise.future
  }
}


class SecondaryActionActor(
  val f: PF2,
  val command: Command,
  val dependency: Command,
  val promise: Promise[Event]
)(implicit mind: ActorMind)
  extends Actor with StrictLogging with DependentAction {

  import MemoryActor._
  import ActorAction._

  private var canFire = false
  mind.ref ! ReadyToDo(command)
  mind.system.eventStream.subscribe(self, classOf[Done])
  override def receive: Receive = {
    case GoOn(c) if command == c =>
      canFire = true
      mind.ref ! Requires(dependency)

    case InProgress(c) if command == c =>
      logger.debug("Command {} execution in progress", c)

    case InProgress(d) if dependency == d =>
      logger.debug("Dependency {} execution in progress", d)

    case Done(c, event) if command == c =>
      promise.success(event)

    case Done(d, event) if dependency == d && canFire =>
      val result = f((command, event))
      context.system.eventStream.unsubscribe(self)
      mind.ref ! Complete(command, result)
      promise.success(result)

  }
}

object SecondaryActionActor {
  def props(
    f: PF2,
    command: Command,
    dependency: Command,
    promise: Promise[Event]
  )(implicit mind: ActorMind): Props =
    Props(new SecondaryActionActor(f, command, dependency, promise))
}