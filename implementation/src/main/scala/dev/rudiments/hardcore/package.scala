package dev.rudiments

package object hardcore {
  trait Cause {}
  trait Effect {}

  trait Break extends Throwable with Effect {
    override def toString: String = this match {
      case p: Product => scala.runtime.ScalaRunTime._toString(p)
      case _ => super.toString
    }
  }

  trait LifeCycle {}

  case object InProgress extends LifeCycle
  case object NoHandler extends LifeCycle
  case object NotImplemented extends LifeCycle

  case class Internal(message: String, cause: Throwable)
    extends Throwable(message, cause) with Break


  trait Memory {
    def log: Seq[(Cause, Effect)]
    //TODO def stream(): ???
    def context: Map[Cause, Effect]
  }


  trait Port {
    def produce: Cause
    def respond(effect: Effect): Unit
  }

  trait Service {
    def apply(cause: Cause): Effect
  }

  trait Adapter extends Service {}

  trait Pipeline extends Pipe {
    val pipes: Seq[Pipe]

    override def apply(cause: Cause): Either[Effect, Cause] = {
      import cats.implicits._
      pipes
        .scanLeft(Either.right[Effect, Cause](cause)) {
          case (Right(c), p) => p(c)
          case (e@Left(_), _) => e
        }
        .reduce {
          case (e@Left(_), _) => e
          case (_, e@Left(_)) => e
          case (r@Right(_), Right(_)) => r
        }
    }

    override def apply(cause: Cause, effect: Effect): Effect = {
      pipes
        .scanLeft(effect) { (e, p) => p(cause, e) }
        .reduce { (_, e) => e }
    }
  }

  trait Pipe {
    def apply(cause: Cause): Either[Effect, Cause]

    def apply(cause: Cause, effect: Effect): Effect = effect
  }

  trait Drainage {}
  trait Drain {}

  trait ID {}
  trait TypeID {}
}
