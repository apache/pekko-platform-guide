package com.lightbend.telemetry.sample

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }

object Protocol {
  sealed trait Operation
  final case class Add(a: Int, b: Int, replyTo: ActorRef[Result])
      extends Operation
  final case class Result(result: Int)
}

import Protocol._

object Printer {
  def apply(): Behavior[Result] = Behaviors.receiveMessage { result =>
    println(s"The result was: ${result.result}")
    Behaviors.same
  }
}

object Calculator {
  def apply(): Behavior[Operation] = Behaviors.receiveMessage {
    case Add(a, b, replyTo) =>
      println(s"Calculating $a + $b")
      replyTo ! Result(a + b)
      Behaviors.same
  }
}

object Main {

  final case class Start()

  def main(args: Array[String]): Unit = {
    val system = ActorSystem[Start](
      Behaviors.setup { context =>
        val printer = context.spawn(Printer(), "Printer")
        val calculator = context.spawn(Calculator(), "Calculator")

        Behaviors.receiveMessage { _ =>
          (0 to 10).foreach(n => calculator ! Add(n, n, printer))
          Behaviors.same
        }

      },
      "TelemetryMain")

    system ! Start()
  }
}
